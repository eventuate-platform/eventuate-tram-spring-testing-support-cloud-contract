package io.eventuate.tram.commandclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandClientController {

    private final CommandSender commandSender;

    @Autowired
    public CommandClientController(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @PostMapping("/send")
    public void publishEvent() {
        commandSender.send();
    }

}
