package fr.xebia.architectures.hexagonal.infra.service;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import fr.xebia.architectures.hexagonal.domain.account.AccountRepository;
import fr.xebia.architectures.hexagonal.infra.entity.MongoAccount;
import fr.xebia.architectures.hexagonal.infra.repository.MongoAccountRepository;
import fr.xebia.architectures.hexagonal.infra.repository.MongoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class AccountRepositoryService implements AccountRepository {

    private MongoAccountRepository mongoAccountRepository;
    private MongoOperationRepository mongoOperationRepository;

    @Autowired
    public AccountRepositoryService(MongoAccountRepository mongoAccountRepository,
                                    MongoOperationRepository mongoOperationRepository) {
        this.mongoAccountRepository = mongoAccountRepository;
        this.mongoOperationRepository = mongoOperationRepository;
    }

    @Override
    public Account open(Account account) {
        return mongoAccountRepository.save(MongoAccount.from(account))
                .toAccount(mongoOperationRepository.findOperationByAccountId(account.iban));
    }

    @Override
    public Account getByIban(String iban) {
        return mongoAccountRepository.findById(iban)
                .map(mongoAccount -> mongoAccount
                        .toAccount(mongoOperationRepository.findOperationByAccountId(mongoAccount.getId())))
                .orElseThrow(EntityNotFoundException::new);
    }
}
