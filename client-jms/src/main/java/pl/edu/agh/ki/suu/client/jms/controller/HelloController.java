package pl.edu.agh.ki.suu.client.jms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.ki.suu.common.cdm.Message;
import pl.edu.agh.ki.suu.client.jms.model.Hello;

import javax.jms.Queue;

@RestController
@RequestMapping("/suu")
public class HelloController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Hello getHello(){
        Hello hello = new Hello();
        hello.setMessage("App is up and running");
        return hello;
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void setHello(@RequestBody Message message){
        this.jmsMessagingTemplate.convertAndSend(this.queue, message);
    }

}
