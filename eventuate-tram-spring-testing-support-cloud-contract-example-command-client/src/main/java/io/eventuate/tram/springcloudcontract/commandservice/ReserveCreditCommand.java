package io.eventuate.tram.springcloudcontract.commandservice;

import io.eventuate.tram.commands.common.Command;

public record ReserveCreditCommand(long customerId, long orderId, long orderTotal) implements Command {
    public ReserveCreditCommand {
    }
}
