package com.trebol.inventory.domain.api;

import com.trebol.inventory.domain.model.Client;

import java.util.List;

public interface IClientServicePort {

    void createClient(Client client);
    List<Client> getAllClients();

}
