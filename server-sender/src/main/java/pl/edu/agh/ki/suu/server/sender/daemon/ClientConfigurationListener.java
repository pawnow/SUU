package pl.edu.agh.ki.suu.server.sender.daemon;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.api.Defaults;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Component
public class ClientConfigurationListener {

    @Resource
    MongoDAO mongoDAO;

    @Resource
    QueueListenersManager queueListenersManager;

    private Set<Configuration> registeredClients = new HashSet<>();

    @Scheduled(fixedRate = Defaults.PERIOD_TIME)
    public void run() {
        for (Configuration client : mongoDAO.getAllClients(Defaults.CLIENT_QUEUEU_NAME)) {
            if (!registeredClients.contains(client)) {
                queueListenersManager.addNewClient(client.getSender().getName(), client);
                registeredClients.add(client);
            }
        }
    }
}
