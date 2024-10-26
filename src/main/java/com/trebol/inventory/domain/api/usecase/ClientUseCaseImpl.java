package com.trebol.inventory.domain.api.usecase;

import com.trebol.inventory.domain.api.IClientServicePort;
import com.trebol.inventory.domain.exception.ClientAlreadyExistsException;
import com.trebol.inventory.domain.model.Client;
import com.trebol.inventory.domain.spi.IClientPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClientUseCaseImpl implements IClientServicePort {

    private final IClientPersistencePort persistencePort;

    @Override
    public void createClient(Client client) {
        if(persistencePort.getClientById(client.getId()).isPresent()) throw new ClientAlreadyExistsException();
        persistencePort.createClient(client);
    }

    @Override
    public List<Client> getAllClients() {
        return persistencePort.getAllClients();
    }
}
