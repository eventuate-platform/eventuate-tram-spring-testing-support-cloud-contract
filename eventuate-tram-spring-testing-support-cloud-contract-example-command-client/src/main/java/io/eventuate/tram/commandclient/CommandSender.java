package io.eventuate.tram.commandclient;

import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.springcloudcontract.commandservice.ReserveCreditCommand;

import static java.util.Collections.emptyMap;

public class CommandSender {

    private final CommandProducer commandProducer;

    public CommandSender(CommandProducer commandProducer) {
        this.commandProducer = commandProducer;
    }

    void send() {
        // Some change
        this.commandProducer.send("customerService",
                new ReserveCreditCommand(101L, 102L, 103L),
                "reserveCreditReply", emptyMap());
    }
}
