package io.eventuate.tram.spring.testing.cloudcontract;

import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.eventuate.tram.messaging.producer.MessageProducer;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class EventuateTramMessageVerifierSender implements MessageVerifierSender<Message> {

    private final MessageProducer messageProducer;

    public EventuateTramMessageVerifierSender(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Override
    public void send(Message message, String destination, YamlContract contract) {
        messageProducer.send(destination, message);
    }

    @Override
    public <T> void send(T payload, Map<String, Object> headers, String destination, YamlContract contract) {
        send(MessageBuilder
                        .withPayload(asString(payload))
                        .withExtraHeaders("", stringValued(headers))
                        .build(),
                destination,
                contract);
    }

    private static String asString(Object payload) {
        if (payload instanceof byte[])
            return new String((byte[]) payload, StandardCharsets.UTF_8);
        return payload == null ? null : payload.toString();
    }

    private static Map<String, String> stringValued(Map<String, Object> headers) {
        return headers.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
    }
}
