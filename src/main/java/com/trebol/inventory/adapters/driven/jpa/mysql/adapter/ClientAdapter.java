package com.trebol.inventory.adapters.driven.jpa.mysql.adapter;

import com.trebol.inventory.adapters.driven.jpa.mysql.mapper.IClientEntityMapper;
import com.trebol.inventory.adapters.driven.jpa.mysql.repository.IClientRepository;
import com.trebol.inventory.domain.model.Client;
import com.trebol.inventory.domain.spi.IClientPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientAdapter implements IClientPersistencePort {

    private final IClientRepository repository;
    private final IClientEntityMapper mapper;

    @Override
    public void createClient(Client client) {
        repository.save(mapper.toClientEntity(client));
    }

    @Override
    public List<Client> getAllClients() {
        return mapper.toClientsList(repository.findAll());
    }

    @Override
    public Optional<Client> getClientById(String id) {
        return mapper.toOptionalClient(repository.findById(id));
    }
}
