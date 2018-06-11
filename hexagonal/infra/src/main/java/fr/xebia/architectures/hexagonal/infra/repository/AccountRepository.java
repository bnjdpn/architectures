package fr.xebia.architectures.hexagonal.infra.repository;

import fr.xebia.architectures.hexagonal.infra.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findAccountsByClientId(String clientId);

}
