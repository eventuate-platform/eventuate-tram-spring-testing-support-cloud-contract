package io.eventuate.tram.spring.testing.cloudcontract;

import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.MessageProducer;
import io.eventuate.tram.spring.inmemory.TramInMemoryConfiguration;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@AutoConfigureMessageVerifier
@Import(TramInMemoryConfiguration.class)
public class ContractVerifierEventuateTramConfiguration {

    @Bean
    EventuateTramMessageVerifierReceiver kafkaTemplateMessageVerifier(MessageConsumer messageConsumer) {
        return new EventuateTramMessageVerifierReceiver(messageConsumer);
    }

    @Bean
    MessageVerifierSender<Message> messageVerifierSender(MessageProducer messageProducer) {
        return new EventuateTramMessageVerifierSender(messageProducer);
    }

    @Bean
    EventuateTramContractVerifierMessaging contractVerifierMessaging(MessageVerifierSender<Message> sender, MessageVerifierReceiver<Message> receiver) {
        return new EventuateTramContractVerifierMessaging(sender, receiver);
    }
}
