package pl.edu.agh.ki.suu.server.repository.impl;

import org.springframework.stereotype.Component;
import pl.edu.agh.ki.suu.common.cdm.Configuration;
import pl.edu.agh.ki.suu.server.repository.ClientRepository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Component
public class RegisteredClientRepository implements ClientRepository {

    private Set<Configuration> registeredClients = new HashSet<>();

    @Override
    public void addClient(Configuration configuration) {
        registeredClients.add(configuration);
        System.out.println("Clients number = " + registeredClients.size());
        shouldStartMessageSending();
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
            if(conf.equals(configuration)){
                i.remove();
            }
        }
        shouldStopMessageSending();
        System.out.println("Clients number = " + registeredClients.size());
    }

    private void shouldStopMessageSending(){
        //TODO stop thread which sends messages to registered clients
    }

    private void shouldStartMessageSending(){
        //TODO start thread which sends messages to registered clients
    }
}
