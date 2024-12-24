package io.eventuate.tram.springcloudcontract.commandservice;


import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandHandlersBuilder;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class CustomerCommandHandler {

    private CustomerService customerService;

    public CustomerCommandHandler(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CommandHandlers commandHandlerDefinitions() {
        return CommandHandlersBuilder
                .fromChannel("customerService")
                .onMessage(ReserveCreditCommand.class, this::reserveCredit)
                .build();
    }

    public Message reserveCredit(CommandMessage<ReserveCreditCommand> cm) {
        ReserveCreditCommand cmd = cm.getCommand();
        customerService.reserveCredit(cmd.customerId(), cmd.orderId(), cmd.orderTotal());
        return withSuccess(new CustomerCreditReserved());
    }

}