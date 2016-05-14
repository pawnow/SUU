package pl.edu.agh.ki.suu.server.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.server.daemon.DaemonMessageSender;
import pl.edu.agh.ki.suu.server.repository.ClientRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class RegisteredClientRepository implements ClientRepository {

    @Autowired
    private DaemonMessageSender daemonMessageSender;

    private Set<Configuration> registeredClients = new HashSet<>();

    @Override
    public void addClient(Configuration configuration) {
        registeredClients.add(configuration);
        System.out.println("Clients number = " + registeredClients.size());
    }

    @Override
    public Configuration getClientByAddress(String address) {
        for( Configuration configuration : registeredClients){
            if(configuration.getSender().getAddress().equals(address)){
                return configuration;
            }
        }
        return null;
    }

    @Override
    public void removeClient(Configuration configuration) {
        for(Iterator<Configuration> i = registeredClients.iterator(); i.hasNext();){
            Configuration conf = i.next();
            if(conf.getSender().getName().equals(configuration.getSender().getName())){
                i.remove();
            }
        }
        System.out.println("Clients number = " + registeredClients.size());
    }

    @Override
    public Set<Configuration> getAll() {
        return registeredClients;
    }

}
