package pl.edu.agh.ki.suu.server.service.receiver;

import com.mongodb.gridfs.CLI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;
import pl.edu.agh.ki.suu.server.repository.ClientRepository;

import static pl.edu.agh.ki.suu.common.api.Defaults.CLIENT_QUEUEU_NAME;

@Service(value = "register")
public class JmsRegisterReceiver {

    @Autowired
    private MongoDAO mongoDAO;

    public void receive(Configuration configuration){
        mongoDAO.register(configuration, CLIENT_QUEUEU_NAME);
        System.out.println("REGISTER : " + configuration.getSender().getName());
    }
}
