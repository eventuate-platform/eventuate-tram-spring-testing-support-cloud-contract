package io.eventuate.tram.spring.testing.cloudcontract;

import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import io.eventuate.tram.messaging.producer.MessageProducer;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;

import java.util.Map;

public class EventuateTramMessageVerifierSender implements MessageVerifierSender<Message> {

    private final MessageProducer messageProducer;

    public EventuateTramMessageVerifierSender(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Override
    public void send(Message message, String destination, YamlContract contract) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> void send(T payload, Map<String, Object> headers, String destination, YamlContract contract) {
        Map<String, String> mh = headers.entrySet().stream().collect(
                java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().toString()
                )
        );
        messageProducer.send(destination, MessageBuilder.withPayload((String) payload).withExtraHeaders("", mh).build());
    }
}
