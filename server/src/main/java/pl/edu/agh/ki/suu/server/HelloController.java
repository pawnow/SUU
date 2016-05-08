package pl.edu.agh.ki.suu.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suu")
public class HelloController {

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    Hello getHello(){
        Hello hello = new Hello();
        hello.setMessage("App is up and running");
        return hello;
    }

}
