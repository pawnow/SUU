package pl.edu.agh.ki.suu.server.sender.service;

import pl.edu.agh.ki.suu.server.sender.service.impl.JmsMessageSender;
import pl.edu.agh.ki.suu.server.sender.service.impl.MailMessageSender;
import pl.edu.agh.ki.suu.server.sender.service.impl.RestMessageSender;
import pl.edu.agh.ki.suu.server.sender.service.impl.SoapMessageSender;

import static pl.edu.agh.ki.suu.common.api.Constants.*;

public class MessageSenderFactory {

    public static MessageSender getMessageSender(String protocolVersion) {
        if (protocolVersion == null) {
            return null;
        }
        if (protocolVersion.equalsIgnoreCase(JMS_PROTOCOL_NAME)) {
            return new JmsMessageSender();
        } else if (protocolVersion.equalsIgnoreCase(REST_PROTOCOL_NAME)) {
            return new RestMessageSender();
        } else if (protocolVersion.equalsIgnoreCase(SOAP_PROTOCOL_NAME)) {
            return new SoapMessageSender();
        } else if (protocolVersion.equalsIgnoreCase(MAIL_PROTOCOL_NAME)) {
            return new MailMessageSender();
        }
        return null;
    }
}
