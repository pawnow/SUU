package pl.edu.agh.ki.suu.common.consumer;

import pl.edu.agh.ki.suu.common.api.ProducerClient;
import pl.edu.agh.ki.suu.common.cdm.Message;

public abstract class AbstractClientProducer implements ProducerClient {
    protected Message createMessage(String payload) {
        Message message = new Message();
        message.setSender(new Message.Sender());
        message.setTimeout("12345");
        message.setPayload(payload);
        return message;
    }
}
