package csci318.demo.model.productEvent;

import csci318.demo.model.Product;
import csci318.demo.repository.ProductRepository;
import org.springframework.context.ApplicationEvent;

import java.util.*;

import static java.util.Arrays.stream;

public class ProductDetailEvent extends ApplicationEvent {
    private ProductRepository productRepository;
    private String eventMessage;


    public ProductDetailEvent(Object source, String eventMessage) {
        super(source);
        this.eventMessage = eventMessage;
        System.out.println("Product Detail Event");
    }

    public String getMessage() {
        return eventMessage;
    }

    public List<Product> eventSortByPriceAsc(List<Product> unsortedProductList) {
        unsortedProductList.sort(Comparator.comparing(Product::getPrice));
        List<Product> sortedList =  new ArrayList<Product>();
        for (Product product : unsortedProductList) {
            sortedList.add(product);
        }
       return sortedList;
    }

}
