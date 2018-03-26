package fr.xebia.architectures.hexagonal.business.provider.application.impl;

import fr.xebia.architectures.hexagonal.business.model.Client;
import fr.xebia.architectures.hexagonal.business.provider.application.ClientApplicationProvider;
import fr.xebia.architectures.hexagonal.business.provider.service.ClientServiceProvider;

public class DefaultClientApplicationProvider implements ClientApplicationProvider {

    private ClientServiceProvider clientServiceProvider;

    public DefaultClientApplicationProvider(ClientServiceProvider clientServiceProvider) {
        this.clientServiceProvider = clientServiceProvider;
    }

    @Override
    public Client create(Client client) {
        return clientServiceProvider.save(client);
    }

    @Override
    public Client getById(String id) {
        return clientServiceProvider.findClient(id);
    }
}
