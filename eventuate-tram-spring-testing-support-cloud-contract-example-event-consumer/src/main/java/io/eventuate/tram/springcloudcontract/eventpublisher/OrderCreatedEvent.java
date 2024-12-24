package io.eventuate.tram.springcloudcontract.eventpublisher;

import io.eventuate.tram.events.common.DomainEvent;

public record OrderCreatedEvent(long orderId) implements DomainEvent {
    public OrderCreatedEvent() {
       this(0L);
    }
}
