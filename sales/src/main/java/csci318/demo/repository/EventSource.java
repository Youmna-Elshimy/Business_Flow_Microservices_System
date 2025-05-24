package csci318.demo.repository;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EventSource {
    String STOCK_INPUT = "productStockChannel";

    @Output("orderPlacedChannel")
    MessageChannel orderPlaced();

    @Input(STOCK_INPUT)
    SubscribableChannel stockChannel();
}
