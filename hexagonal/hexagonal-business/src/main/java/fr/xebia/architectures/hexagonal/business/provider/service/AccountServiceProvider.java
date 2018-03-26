package fr.xebia.architectures.hexagonal.business.provider.service;

import fr.xebia.architectures.hexagonal.business.model.Account;
import java.util.List;

public interface AccountServiceProvider {

    Account createOrUpdate(Account account);

    List<Account> findAccountsByClient(String clientId);

    Account findAccount(String id);

}
