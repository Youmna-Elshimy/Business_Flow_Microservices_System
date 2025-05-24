
package csci318.demo.service;

import csci318.demo.model.Customer;
import csci318.demo.model.Order;
import csci318.demo.model.Product;
import csci318.demo.model.event.ProductEvent;
import csci318.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    public static final String PRODUCT_URL = "http://localhost:8081/product/";
    public static final String CUSTOMER_URL = "http://localhost:8080/customer/";
    private static OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final EventHandler eventHandler;

    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate, EventHandler eventHandler) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
        this.eventHandler = eventHandler;
    }

    //finding an order
    public static Order findOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            return orderRepository.findById(id).get();
        } else {
            return null;
        }
    }

    //finding all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    //creating order
    public long createNewOrder(Long productId, String supplier, int quantity, String status, Long customerId) {
        Order order = new Order();
        order.setSupplier(supplier);
        order.setProductId(restTemplate.getForObject(PRODUCT_URL + productId, Product.class).getId());
        order.setCustomerId(restTemplate.getForObject(CUSTOMER_URL + customerId, Customer.class).getId());
        order.setQuantity(quantity);
        order.setStatus(status);
        orderRepository.save(order);
        return order.getId();
    }

    //updating order
    public void updateOrder(Long id, Long productId, String supplier, int quantity, String status, Long customerId) {
        Order currentOrder = orderRepository.findById(id).get();
        currentOrder.setProductId(restTemplate.getForObject(PRODUCT_URL + productId, Product.class).getId());
        currentOrder.setCustomerId(restTemplate.getForObject(CUSTOMER_URL + customerId, Customer.class).getId());
        currentOrder.setSupplier(supplier);
        currentOrder.setQuantity(quantity);
        currentOrder.setStatus(status);
        orderRepository.save(currentOrder);
    }

    //buying order
    public void buyOrder(long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(RuntimeException::new);
        order.buy(orderId);
        orderRepository.save(order);
    }
}