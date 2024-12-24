package io.eventuate.tram.commandclient;

import io.eventuate.tram.spring.testing.cloudcontract.EnableEventuateTramContractVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {BaseForCommandServiceTest.TestConfig.class})
public abstract class BaseForCommandServiceTest {

    @Configuration
    @EnableAutoConfiguration
    @EnableEventuateTramContractVerifier
    @Import({CommandClientConfiguration.class})
    public static class TestConfig {

    }


    @Autowired
    private CommandSender commandSender;
    protected void reserveCredit() {
        commandSender.send();
    }

}