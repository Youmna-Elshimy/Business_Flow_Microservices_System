package csci318.demo.service;

import csci318.demo.model.event.OrderEvent;
import csci318.demo.model.event.ProductEvent;
import csci318.demo.repository.EventSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@EnableBinding(EventSource.class)
public class EventHandler {
    EventSource eventSource;

    public EventHandler(EventSource eventSource){
        this.eventSource = eventSource;
    }

    @TransactionalEventListener
    public void handlePlacingOrder(OrderEvent orderEvent){
        eventSource.orderPlaced().send(MessageBuilder.withPayload(orderEvent).build());
    }

    @StreamListener(EventSource.STOCK_INPUT)
    public void receiveEvent(ProductEvent productEvent) {
        System.out.println("Kafka Topic: stock \n - Product ID: "+
                productEvent.getProductId()+", Stock: " +
                productEvent.getStock());
    }
}
