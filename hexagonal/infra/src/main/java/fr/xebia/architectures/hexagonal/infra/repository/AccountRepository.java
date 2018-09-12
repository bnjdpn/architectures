package fr.xebia.architectures.hexagonal.infra.repository;

import fr.xebia.architectures.hexagonal.infra.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

}