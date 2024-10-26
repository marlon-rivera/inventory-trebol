package com.trebol.inventory.adapters.driving.http.mapper.request;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateClient;
import com.trebol.inventory.domain.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientRequestMapper {

    Client toClient(CreateClient createClient);

}
