package fr.xebia.architectures.hexagonal.business.provider.application;

import fr.xebia.architectures.hexagonal.business.model.Account;
import java.util.List;

public interface AccountApplicationProvider {

    Account create(Account account);

    Account getById(String id);

    List<Account> getByClientId(String clientId);

}
