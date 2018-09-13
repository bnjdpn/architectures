package fr.xebia.architectures.layered.presentation.controller;

import fr.xebia.architectures.layered.business.service.ClientService;
import fr.xebia.architectures.layered.persistence.model.Client;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    @Inject
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client create(@Valid Client client) {
        return clientService.createClient(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable String id) {
        return clientService.findClient(id);
    }

}
