package io.eventuate.tram.springcloudcontract.commandservice;

import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.consumer.CommandDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerCommandHandlerConfiguration {

    @Bean
    public CustomerCommandHandler customerCommandHandler(CustomerService customerService) {
        return new CustomerCommandHandler(customerService);
    }


    @Bean
    public CommandDispatcher consumerCommandDispatcher(CustomerCommandHandler target,
                                                       CommandDispatcherFactory commandDispatcherFactory) {

        return commandDispatcherFactory.make("customerCommandDispatcher", target.commandHandlerDefinitions());
    }

    @Bean
    CustomerService customerService() {
        return new CustomerService();
    }

}
