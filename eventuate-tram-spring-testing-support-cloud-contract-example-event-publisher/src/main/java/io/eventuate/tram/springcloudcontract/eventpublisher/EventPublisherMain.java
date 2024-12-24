package io.eventuate.tram.springcloudcontract.eventpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
public class EventPublisherMain {


    public static void main(String[] args) {
        SpringApplication.run(EventPublisherMain.class, args);
    }
}
