package nakup.inventory.service.event;

import nakup.inventory.model.event.OrderCreatedEvent;
import nakup.inventory.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCreatedListener {

    @Autowired
    ReservationService reservationService;

    private static final String TOPIC = "order-created";

    @KafkaListener(topics = TOPIC, groupId = "inventory-service", properties = {"spring.json.value.default.type=nakup.inventory.model.event.OrderCreatedEvent"})
    public void orderCreatedHandler(OrderCreatedEvent event) {
        System.out.println("Event found: " + event);

        reservationService.reserveOrder(event.orderId(), event.items());
        System.out.println("done/*asdasdasdQWEQWEQWE*/");
    }

}
