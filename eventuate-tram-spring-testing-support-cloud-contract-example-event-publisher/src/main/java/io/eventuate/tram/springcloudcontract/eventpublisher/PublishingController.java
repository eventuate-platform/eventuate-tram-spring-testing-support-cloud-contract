package io.eventuate.tram.springcloudcontract.eventpublisher;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublishingController {

    private final DomainEventPublisher domainEventPublisher;

    @Autowired
    public PublishingController(DomainEventPublisher domainEventPublisher) {
        this.domainEventPublisher = domainEventPublisher;
    }

    @PostMapping("/event")
    public void publishEvent() {
        domainEventPublisher.publish("Order", "99", List.of(new OrderCreatedEvent(System.currentTimeMillis())));
    }


}
