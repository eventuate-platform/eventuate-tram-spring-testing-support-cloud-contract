package io.eventuate.tram.springcloudcontract.eventconsumer;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConsumerConfiguration {

    @Bean
    public OrderEventConsumer orderEventConsumer(CustomerService customerService) {
        return new OrderEventConsumer(customerService);
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(OrderEventConsumer orderEventConsumer, DomainEventDispatcherFactory domainEventDispatcherFactory) {
        return domainEventDispatcherFactory.make("orderServiceEvents", orderEventConsumer.domainEventHandlers());
    }

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

}
