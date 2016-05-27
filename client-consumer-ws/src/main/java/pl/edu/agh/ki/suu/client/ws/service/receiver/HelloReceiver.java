package pl.edu.agh.ki.suu.client.ws.service.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;

import static pl.edu.agh.ki.suu.common.api.Defaults.DEFAULT_MESSAGE_COLLECTION_NAME;

@Service(value = "receiver")
public class HelloReceiver {

    public void receive(Message message){
        System.out.println("RECEIVER RECEIVED: " + message.getPayload());
    }

}
