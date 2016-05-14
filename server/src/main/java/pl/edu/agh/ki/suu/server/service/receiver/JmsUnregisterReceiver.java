package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Configuration;

@Service(value = "unregister")
public class JmsUnregisterReceiver {

    public void receive(Configuration configuration){
        System.out.println("UNREGISTER : " + configuration.getSender().getAddress());
    }
}
