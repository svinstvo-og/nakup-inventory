package nakup.inventory.service.event;

import nakup.inventory.model.event.ItemsReservedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemsReservedProducer {
    private static final String TOPIC = "items-reserved";
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public ItemsReservedProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishOrderCreatedEvent(ItemsReservedEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("Items reserved " + event);
    }
}
