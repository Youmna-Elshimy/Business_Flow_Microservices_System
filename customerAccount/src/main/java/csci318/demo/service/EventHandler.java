package csci318.demo.service;

import csci318.demo.model.CustomerEvent.ContactEvent;
import csci318.demo.model.CustomerEvent.OrderEvent;
import csci318.demo.repository.EventSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(EventSource.class)
public class EventHandler {
    EventSource eventSource;

    public EventHandler(EventSource eventSource){
        this.eventSource = eventSource;
    }

    @EventListener
    public void handleReturnEvent(ContactEvent contactEvent) {
        eventSource.sortedCustomers().send(MessageBuilder.withPayload(contactEvent).build());
    }

    @StreamListener(EventSource.ORDER_INPUT)
    public void receiveEvent(OrderEvent orderEvent) {
        System.out.println("Subscribed Kafka Topic: orderplaced \n - Order ID: "+
                orderEvent.getOrderId()+", Status: " +
                orderEvent.getStatus());
    }
}
