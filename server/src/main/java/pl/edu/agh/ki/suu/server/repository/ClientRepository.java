package pl.edu.agh.ki.suu.server.repository;

import pl.edu.agh.ki.suu.common.cdm.Configuration;

import java.util.Set;

public interface ClientRepository {

    void addClient(Configuration configuration);
    Configuration getClientByAddress(String address);
    void removeClient(Configuration configuration);
    Set<Configuration> getAll();
}
