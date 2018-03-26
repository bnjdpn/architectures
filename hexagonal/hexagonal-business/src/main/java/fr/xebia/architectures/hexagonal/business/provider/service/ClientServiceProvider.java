package fr.xebia.architectures.hexagonal.business.provider.service;

import fr.xebia.architectures.hexagonal.business.model.Client;

public interface ClientServiceProvider {

    Client save(Client client);

    Client findClient(String id);

}
