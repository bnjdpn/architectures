package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.infra.entity.Client;
import fr.xebia.architectures.hexagonal.infra.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientRepository clientService;

    @Autowired
    public ClientController(ClientRepository clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client create(@Valid Client client) {
        return clientService.save(client);
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable String id) {
        return clientService.findById(id).orElse(null);
    }

}
