package io.eventuate.tram.spring.testing.cloudcontract;

import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.springframework.cloud.contract.verifier.converter.YamlContract;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class EventuateTramMessageVerifierReceiver implements MessageVerifierReceiver<Message> {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EventuateTramMessageVerifierReceiver.class);

    private final Map<String, BlockingQueue<Message>> broker = new ConcurrentHashMap<>();

    private final MessageConsumer messageConsumer;

    public EventuateTramMessageVerifierReceiver(MessageConsumer messageConsumer) {
        this.messageConsumer = messageConsumer;
    }

    @PostConstruct
    public void init() {
        logger.info("{} initialized", getClass().getSimpleName());
        messageConsumer.subscribe("messageVerifier", Set.of("*"), m -> {
            String destination = m.getRequiredHeader(Message.DESTINATION);
            logger.info("Got a message from a topic [" + destination + "]");
            broker.putIfAbsent(destination, new LinkedBlockingQueue<>());
            BlockingQueue<Message> messageQueue = broker.get(destination);
            messageQueue.add(m);
        });
    }

    @Override
    public Message receive(String destination, long timeout, TimeUnit timeUnit, YamlContract contract) {
        broker.putIfAbsent(destination, new LinkedBlockingQueue<>());
        BlockingQueue<Message> messageQueue = broker.get(destination);
        Message message;
        try {
            message = messageQueue.poll(timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (message != null) {
            logger.info("Removed a message from a channel [" + destination + "]");
        }
        return message;
    }


    @Override
    public Message receive(String destination, YamlContract contract) {
        return receive(destination, 1, TimeUnit.SECONDS, contract);
    }

}
