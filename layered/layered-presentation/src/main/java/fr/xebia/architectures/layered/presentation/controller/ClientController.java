package fr.xebia.architectures.layered.presentation.controller;

import fr.xebia.architectures.layered.business.service.ClientService;
import fr.xebia.architectures.layered.persistence.model.Client;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
