package pl.edu.agh.ki.suu.client;

import org.springframework.web.client.RestTemplate;

public class UsageExampleREST {

    public static void main(String[] args){
        String message = "HelloRESTCAMELAPI";
        sendRestMessage(message);
    }

    private static void sendRestMessage(String message){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://localhost:8080/suu/message", message, String.class);
    }

}
