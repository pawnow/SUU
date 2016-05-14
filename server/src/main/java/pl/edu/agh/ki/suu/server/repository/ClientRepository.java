package pl.edu.agh.ki.suu.server.repository;

import pl.edu.agh.ki.suu.common.cdm.Configuration;

public interface ClientRepository {

    void addClient(Configuration configuration);
    Configuration getClientByAddress(String address);
    void removeClient(Configuration configuration);
}
