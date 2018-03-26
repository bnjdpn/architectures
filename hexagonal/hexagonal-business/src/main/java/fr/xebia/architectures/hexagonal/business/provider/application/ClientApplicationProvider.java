package fr.xebia.architectures.hexagonal.business.provider.application;

import fr.xebia.architectures.hexagonal.business.model.Client;

public interface ClientApplicationProvider {

    Client create(Client client);

    Client getById(String id);

}
