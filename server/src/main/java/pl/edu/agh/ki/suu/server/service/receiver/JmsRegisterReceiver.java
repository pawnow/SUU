package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Configuration;


@Service(value = "register")
public class JmsRegisterReceiver {

    public void receive(Configuration configuration){
        System.out.println("REGISTER : " + configuration.getSender().getAddress());
    }
}
