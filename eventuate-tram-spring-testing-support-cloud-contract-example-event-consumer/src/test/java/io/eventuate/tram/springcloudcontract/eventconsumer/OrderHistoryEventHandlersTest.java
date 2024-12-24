package io.eventuate.tram.springcloudcontract.eventconsumer;

import io.eventuate.tram.spring.testing.cloudcontract.EnableEventuateTramContractVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static io.eventuate.util.test.async.Eventually.eventually;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = "io.eventuate.tram.testingsupport.springcloudcontract:eventuate-tram-spring-testing-support-cloud-contract-example-event-publisher",
        stubsMode = StubRunnerProperties.StubsMode.REMOTE )
@DirtiesContext
public class OrderHistoryEventHandlersTest {

    @Configuration
    @EnableAutoConfiguration
    @EnableEventuateTramContractVerifier
    @Import({EventConsumerConfiguration.class})
    public static class TestConfiguration {


    }

    @Autowired
    private StubFinder stubFinder;

    @MockitoBean
    private CustomerService customerService;

    @Test
    public void shouldHandleOrderCreatedEvent() {
        stubFinder.trigger("orderCreatedEvent");
        eventually(() -> {
            verify(customerService).reserveCredit(101L);
        });
    }

}
