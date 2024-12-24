package io.eventuate.tram.commandclient;


import io.eventuate.tram.commands.producer.CommandProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandClientConfiguration {

    @Bean
    public CommandSender commandSender(CommandProducer commandProducer) {
        return new CommandSender(commandProducer);
    }
}
