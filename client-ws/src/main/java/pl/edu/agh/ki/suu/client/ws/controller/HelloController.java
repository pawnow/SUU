package pl.edu.agh.ki.suu.client.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;

import javax.jms.Queue;

@RestController
@RequestMapping("/suu")
public class HelloController {

    @Autowired
    ApplicationContext applicationContext;


    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void setHello(@RequestBody Message message){
//        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
        System.out.println("Reveived message!" + message.getPayload());
    }
}
