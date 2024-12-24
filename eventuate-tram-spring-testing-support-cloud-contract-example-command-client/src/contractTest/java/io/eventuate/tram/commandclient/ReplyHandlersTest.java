package io.eventuate.tram.commandclient;

import io.eventuate.tram.spring.testing.cloudcontract.EnableEventuateTramContractVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "io.eventuate.tram.testingsupport.springcloudcontract:eventuate-tram-spring-testing-support-cloud-contract-example-command-service",
        stubsMode = StubRunnerProperties.StubsMode.REMOTE
        )
@DirtiesContext
public class ReplyHandlersTest {

    @Configuration
    @EnableAutoConfiguration
    @EnableEventuateTramContractVerifier
    public static class TestConfiguration {


    }

    @Autowired
    private StubFinder stubFinder;


    @Test
    public void shouldHandleCreditReservedReply() {
        stubFinder.trigger("creditReserved");

//        eventually(() -> {
//            verify(customerService).reserveCredit(101L, 102L, 103L);
//        });
    }

}
