package fr.xebia.architectures.layered.business.service;

import fr.xebia.architectures.layered.business.exception.NotFoundException;
import fr.xebia.architectures.layered.persistence.model.Account;
import fr.xebia.architectures.layered.persistence.repository.AccountRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    @Inject
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createOrUpdateAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAccountsByClient(String clientId) {
        return accountRepository.findAccountsByClientId(clientId);
    }

    public Account findAccount(String id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(Account.class, id));
    }

}
