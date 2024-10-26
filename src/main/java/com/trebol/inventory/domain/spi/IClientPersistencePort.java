package com.trebol.inventory.domain.spi;

import com.trebol.inventory.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientPersistencePort {

    void createClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(String id);

}
