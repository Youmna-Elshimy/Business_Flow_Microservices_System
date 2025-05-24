package csci318.demo.repository;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventSource {
    String ORDER_INPUT = "orderPlacedChannel";

    @Output("sortedCustomersChannel")
    MessageChannel sortedCustomers();

    @Input(ORDER_INPUT)
    SubscribableChannel ordersChannel();
}
