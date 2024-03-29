package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;
import pl.edu.agh.ki.suu.common.cdm.Message;

import java.io.File;

@Component
public class JmsReceiver {

    @Autowired
    ConfigurableApplicationContext context;

    @JmsListener(destination = "mailbox-destination", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(Message message) {
        System.out.println("Received <" + message + ">");
        context.close();
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }


}
