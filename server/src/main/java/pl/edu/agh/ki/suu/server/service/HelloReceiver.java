package pl.edu.agh.ki.suu.server.service;

import org.springframework.stereotype.Service;

@Service(value = "receiver")
public class HelloReceiver {

    public void receive(String message){
        System.out.println("RECEIVER RECEIVED: " + message);
        //TODO: convertMessageAndUseDAOToSaveIt
    }

}
