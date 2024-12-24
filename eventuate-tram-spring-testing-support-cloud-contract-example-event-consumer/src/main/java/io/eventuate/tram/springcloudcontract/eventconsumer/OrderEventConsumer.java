package io.eventuate.tram.springcloudcontract.eventconsumer;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import io.eventuate.tram.springcloudcontract.eventpublisher.OrderCreatedEvent;

public class OrderEventConsumer {

    private final CustomerService customerService;

    public OrderEventConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    public DomainEventHandlers domainEventHandlers() {
        return DomainEventHandlersBuilder
                .forAggregateType("Order")
                .onEvent(OrderCreatedEvent.class, this::handleOrderCreatedEvent)
                .build();
    }

    public void handleOrderCreatedEvent(DomainEventEnvelope<OrderCreatedEvent> domainEventEnvelope) {
        OrderCreatedEvent event = domainEventEnvelope.getEvent();
        customerService.reserveCredit(event.orderId());
    }

}
