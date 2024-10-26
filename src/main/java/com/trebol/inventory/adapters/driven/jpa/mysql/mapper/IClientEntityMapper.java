package com.trebol.inventory.adapters.driven.jpa.mysql.mapper;

import com.trebol.inventory.adapters.driven.jpa.mysql.entity.ClientEntity;
import com.trebol.inventory.domain.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IClientEntityMapper {

    Client toClient(ClientEntity source);

    ClientEntity toClientEntity(Client source);

    List<Client> toClientsList(List<ClientEntity> source);

    default Optional<Client> toOptionalClient(Optional<ClientEntity> source){
        return source.map(this::toClient);
    }
}
