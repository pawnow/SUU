package pl.edu.agh.ki.suu.client.unregister;

import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.suu.common.cdm.Configuration;

/**
 * Created by mariuszz on 2016-05-30.
 */
public class ClientUnregister {

    public static void unregister(String senderAddress, String senderName){
        RestTemplate restTemplate = new RestTemplate();
        final Configuration configuration = new Configuration();
        final Configuration.Sender sender = new Configuration.Sender();
        sender.setAddress(senderAddress);
        sender.setName(senderName);
        configuration.setSender(sender);
        restTemplate.postForObject("http://localhost:8080/suu/unregister", configuration, String.class);
    }
}
