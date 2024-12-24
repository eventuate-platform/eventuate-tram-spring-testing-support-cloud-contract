package io.eventuate.tram.commandclient;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import static org.mockito.Mockito.mock;

public abstract class BaseForSelfTest {

    private StandaloneMockMvcBuilder controllers(Object... controllers) {
        return MockMvcBuilders.standaloneSetup(controllers);
    }

    @BeforeEach
    public void setup() {
        CommandSender commandSender = mock(CommandSender.class);
        var commandClientController = new CommandClientController(commandSender);
        RestAssuredMockMvc.standaloneSetup(controllers(commandClientController));
    }

}
