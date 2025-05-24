package csci318.demo.service;

import csci318.demo.model.productEvent.ProductEvent;
import csci318.demo.repository.EventSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(EventSource.class)
public class EventHandler {
    EventSource eventSource;

    EventHandler(EventSource eventSource) {
        this.eventSource = eventSource;
    }

    @TransactionalEventListener
    public void handleProductStock(ProductEvent productEvent){
        eventSource.productStock().send(MessageBuilder.withPayload(productEvent).build());
    }
}
