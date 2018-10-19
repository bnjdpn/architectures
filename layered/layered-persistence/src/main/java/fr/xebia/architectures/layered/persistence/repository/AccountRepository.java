package fr.xebia.architectures.layered.persistence.repository;

import fr.xebia.architectures.layered.persistence.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findAccountsByClientId(String clientId);

}
