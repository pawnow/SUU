package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Message;

@Service(value = "receiver")
public class HelloReceiver {

    public void receive(Message message){
        System.out.println("RECEIVER RECEIVED: " + message.getPayload());
        //TODO: convertMessageAndUseDAOToSaveIt
    }

}
