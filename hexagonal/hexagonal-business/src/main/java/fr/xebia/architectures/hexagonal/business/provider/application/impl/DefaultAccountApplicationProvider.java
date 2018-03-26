package fr.xebia.architectures.hexagonal.business.provider.application.impl;

import fr.xebia.architectures.hexagonal.business.model.Account;
import fr.xebia.architectures.hexagonal.business.provider.application.AccountApplicationProvider;
import fr.xebia.architectures.hexagonal.business.provider.service.AccountServiceProvider;
import java.util.List;

public class DefaultAccountApplicationProvider implements AccountApplicationProvider {

    private AccountServiceProvider accountServiceProvider;

    public DefaultAccountApplicationProvider(AccountServiceProvider accountServiceProvider) {
        this.accountServiceProvider = accountServiceProvider;
    }

    @Override
    public Account create(Account account) {
        return accountServiceProvider.createOrUpdate(account);
    }

    @Override
    public Account getById(String id) {
        return accountServiceProvider.findAccount(id);
    }

    @Override
    public List<Account> getByClientId(String clientId) {
        return accountServiceProvider.findAccountsByClient(clientId);
    }
}
