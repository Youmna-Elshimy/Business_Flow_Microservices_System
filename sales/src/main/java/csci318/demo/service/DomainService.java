package csci318.demo.service;

import csci318.demo.model.Order;
import org.springframework.stereotype.Service;

@Service
public class DomainService {

    public DomainService() {

    }

    public boolean checkStatus(long orderId) {
        Order order =  OrderService.findOrderById(orderId);
        if (order.getStatus() == "paid") {
            return true;
        }
        return false;
    }
}
