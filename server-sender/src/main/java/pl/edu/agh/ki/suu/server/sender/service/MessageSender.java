package pl.edu.agh.ki.suu.server.sender.service;

import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.common.cdm.Message;

public interface MessageSender {

    void send(Message message, Configuration client) throws SendingFailedException;
}
