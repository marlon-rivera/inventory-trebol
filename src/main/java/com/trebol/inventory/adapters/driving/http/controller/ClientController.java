package com.trebol.inventory.adapters.driving.http.controller;

import com.trebol.inventory.adapters.driving.http.dto.request.CreateClient;
import com.trebol.inventory.configuration.exceptionhandler.ExceptionResponse;
import com.trebol.inventory.adapters.driving.http.mapper.request.IClientRequestMapper;
import com.trebol.inventory.domain.api.IClientServicePort;
import com.trebol.inventory.domain.model.Client;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final IClientServicePort clientService;
    private final IClientRequestMapper requestMapper;

    /**
     * Crea un nuevo cliente.
     *
     * @param createClient DTO que contiene los datos del cliente a crear
     * @return 201 Created si el cliente se creó exitosamente,
     *         409 Conflict si el cliente ya existe
     */
    @Operation(summary = "Crea un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
            @ApiResponse(responseCode = "409", description = "Conflicto: el cliente ya existe",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse .class)))
    })
    @PostMapping()
    public ResponseEntity<Void> createClient(@Valid @RequestBody CreateClient createClient) {
        clientService.createClient(requestMapper.toClient(createClient));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Obtiene una lista de todos los clientes.
     *
     * @return 200 OK y la lista de clientes
     */
    @Operation(summary = "Obtiene una lista de todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida exitosamente")
    })
    @GetMapping()
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

}
