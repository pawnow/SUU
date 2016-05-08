package pl.edu.agh.ki.suu.server;

import org.springframework.stereotype.Service;

@Service(value = "receiver")
public class HelloReceiver {
    public void receive(String message){
        System.out.println(message);
    }
}
