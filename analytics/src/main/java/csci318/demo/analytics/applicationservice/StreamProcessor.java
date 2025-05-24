package csci318.demo.analytics.applicationservice;

import csci318.demo.events.OrderEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamProcessor {
    @Bean
    public Consumer<KStream<String, OrderEvent>> process() {
        return inputStream -> {
            KStream<Long, Integer> aggregatedStream = inputStream.map((key, value) -> {
                        int quantity = value.getQuantity();
                        Long productId = value.getProductId().longValue();
                        return KeyValue.pair(productId, quantity);
                    }).
                    groupByKey(Grouped.with(Serdes.Long(), Serdes.Integer())).
                    reduce(Integer::sum).toStream();

            aggregatedStream.
                    print(Printed.<Long, Integer>toSysOut().withLabel("Total quantity for product"));
        };
    }
}
