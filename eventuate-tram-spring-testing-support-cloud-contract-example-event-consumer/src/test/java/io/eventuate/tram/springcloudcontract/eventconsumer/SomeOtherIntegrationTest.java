package io.eventuate.tram.springcloudcontract.eventconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SomeOtherIntegrationTest {


    @Configuration
    @EnableAutoConfiguration
    public static class TestConfiguration {


    }

    @Test
    public void testSomething() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }
}
