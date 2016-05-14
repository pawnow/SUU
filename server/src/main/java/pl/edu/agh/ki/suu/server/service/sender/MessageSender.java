package pl.edu.agh.ki.suu.server.service.sender;

import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;

public interface MessageSender {

    void send(Message message, Configuration client);
}
