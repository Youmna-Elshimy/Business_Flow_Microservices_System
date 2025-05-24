package csci318.demo.service.EventListenerAndPublisher;
import csci318.demo.model.productEvent.ProductDetailEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailEventListener implements ApplicationListener<ProductDetailEvent> {
    @EventListener
    @Override
    public void onApplicationEvent(ProductDetailEvent event) {
        System.out.println("Received spring custom event - " + event.getMessage());
        System.out.println("Product Detail Event Listener");
    }
}