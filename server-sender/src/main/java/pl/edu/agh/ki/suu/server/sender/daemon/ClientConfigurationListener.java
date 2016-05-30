package pl.edu.agh.ki.suu.server.sender.daemon;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.api.Defaults;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.mongo.dao.MongoDAO;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Iterator;
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
        Set<Configuration> currentRegisteredClients = new HashSet<>();
        for (Configuration client : mongoDAO.getAllClients(Defaults.CLIENT_QUEUEU_NAME)) {
            currentRegisteredClients.add(client);
            // Configuration class has not implemented equals method so we always add client to registeredClients
            if (!registeredClients.contains(client)) {
                queueListenersManager.addNewClient(client.getSender().getName(), client);
                registeredClients.add(client);
            }
        }

        Set<Configuration> tmpRegisteredClients = new HashSet<>(registeredClients);
        tmpRegisteredClients = removeConfigurations(tmpRegisteredClients, currentRegisteredClients);
        for(Configuration client : tmpRegisteredClients){
            queueListenersManager.removeClient(client.getSender().getName(), client);
        }
    }

    private Set<Configuration> removeConfigurations(Set<Configuration> baseConfigurations, Set<Configuration> toRemove){
        for(Iterator<Configuration> baseIterator = baseConfigurations.iterator();baseIterator.hasNext();){
            Configuration tmpClient = baseIterator.next();
            for(Configuration conf : toRemove){
                if(tmpClient.getSender().getAddress().equals(conf.getSender().getAddress())
                    && tmpClient.getSender().getName().equals(conf.getSender().getName())){
                    baseIterator.remove();
                }
            }
        }
        return baseConfigurations;
    }
}
