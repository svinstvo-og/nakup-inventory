package nakup.inventory.service.event;

import nakup.inventory.model.event.ItemsReservedEvent;
import nakup.inventory.model.event.OrderCreatedEvent;
import nakup.inventory.service.InventoryService;
import nakup.inventory.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class OrderCreatedListener {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ItemsReservedProducer itemsReservedProducer;

    private static final String TOPIC = "order-created";

    @KafkaListener(topics = TOPIC, groupId = "inventory-service", properties = {"spring.json.value.default.type=nakup.inventory.model.event.OrderCreatedEvent"})
    public void orderCreatedHandler(OrderCreatedEvent event) {
        System.out.println("Event found: " + event);

        try {
            reservationService.reserveOrder(event.orderId(), event.items());
            System.out.println("Items reserved successfully");
            itemsReservedProducer.publishOrderCreatedEvent(new ItemsReservedEvent(event.userId(),
                    event.orderId(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    true));
        }
        catch (Exception e) {
            e.printStackTrace();
            itemsReservedProducer.publishOrderCreatedEvent(new ItemsReservedEvent(event.userId(),
                    event.orderId(),
                    Timestamp.valueOf(LocalDateTime.now()),
                    false));
        }
    }
}
