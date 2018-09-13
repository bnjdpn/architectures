package fr.xebia.architectures.layered.business.service;

import fr.xebia.architectures.layered.business.exception.NotFoundException;
import fr.xebia.architectures.layered.persistence.model.Client;
import fr.xebia.architectures.layered.persistence.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Inject
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client findClient(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Client.class, id));
    }

}
