package fr.xebia.architectures.hexagonal.infra.repository;

import fr.xebia.architectures.hexagonal.infra.entity.MongoOperation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface MongoOperationRepository extends MongoRepository<MongoOperation, String> {

    List<MongoOperation> findOperationsByAccountIdAndDateBetweenOrderByDateDesc(String accountId, Instant start, Instant end);

    List<MongoOperation> findOperationByAccountId(String accountId);
}
