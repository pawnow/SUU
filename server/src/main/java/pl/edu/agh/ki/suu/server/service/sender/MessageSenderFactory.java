package pl.edu.agh.ki.suu.server.service.sender;

import pl.edu.agh.ki.suu.server.service.sender.impl.JmsMessageSender;
import pl.edu.agh.ki.suu.server.service.sender.impl.RestMessageSender;
import pl.edu.agh.ki.suu.server.service.sender.impl.SoapMessageSender;

public class MessageSenderFactory {

    private static final String JMS = "JMS";
    private static final String REST = "REST";
    private static final String SOAP = "SOAP";

    public static MessageSender getMessageSender(String protocolVersion){
        if(protocolVersion == null){
            return null;
        }
        if(protocolVersion.equalsIgnoreCase(JMS)){
            return new JmsMessageSender();
        }else if(protocolVersion.equalsIgnoreCase(REST)){
            return new RestMessageSender();
        }else if(protocolVersion.equalsIgnoreCase(SOAP)){
            return new SoapMessageSender();
        }
        return null;
    }
}