package io.eventuate.tram.spring.testing.cloudcontract;

import io.eventuate.tram.messaging.common.Message;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierReceiver;
import org.springframework.cloud.contract.verifier.messaging.MessageVerifierSender;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;

import java.util.HashMap;

public class EventuateTramContractVerifierMessaging extends ContractVerifierMessaging<Message> {

    public EventuateTramContractVerifierMessaging(MessageVerifierSender<Message> sender, MessageVerifierReceiver<Message> receiver) {
        super(sender, receiver);
    }

    @Override
    protected ContractVerifierMessage convert(Message receive) {
        return new ContractVerifierMessage(receive.getPayload(), new HashMap<>(receive.getHeaders()));
    }
}
