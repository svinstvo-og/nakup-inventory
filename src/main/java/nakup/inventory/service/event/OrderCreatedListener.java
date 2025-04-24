package nakup.inventory.service.event;

import nakup.inventory.model.event.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedListener {

    private static final String TOPIC = "order-created";

    @KafkaListener(topics = TOPIC, groupId = "inventory-service", properties = {"spring.json.value.default.type=nakup.inventory.model.event.OrderCreatedEvent"})
    public void orderCreatedHandler(OrderCreatedEvent event) {
        System.out.println("Event found: " + event);

    }

}
