package pl.edu.agh.ki.suu.server.service.receiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.server.repository.ClientRepository;

@Service(value = "unregister")
public class JmsUnregisterReceiver {

    @Autowired
    private ClientRepository clientRepository;

    public void receive(Configuration configuration){
        clientRepository.removeClient(configuration);
        System.out.println("UNREGISTER : " + configuration.getSender().getAddress());
    }
}
