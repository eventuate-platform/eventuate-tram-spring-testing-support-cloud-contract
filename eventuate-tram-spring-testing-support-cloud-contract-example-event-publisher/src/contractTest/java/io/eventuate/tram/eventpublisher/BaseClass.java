package io.eventuate.tram.eventpublisher;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.spring.testing.cloudcontract.EnableEventuateTramContractVerifier;
import io.eventuate.tram.springcloudcontract.eventpublisher.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {BaseClass.TestConfig.class})
public abstract class BaseClass {

    @Autowired
    private DomainEventPublisher domainEventPublisher;

    public void orderCreated() {
        System.out.println("Order created");
        domainEventPublisher.publish("Order", "99", List.of(new OrderCreatedEvent(101)));
    }

    @Configuration
    @EnableAutoConfiguration
    @EnableEventuateTramContractVerifier
    public static class TestConfig {

    }


}
