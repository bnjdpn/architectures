package fr.xebia.architectures.hexagonal.infra.repository;

import fr.xebia.architectures.hexagonal.infra.entity.MongoOperation;
import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<MongoOperation, String> {

    List<MongoOperation> findOperationsByAccountIdAndDateBetweenOrderByDateDesc(String accountId, Instant start, Instant end);

    List<MongoOperation> findOperationByAccountId(String accountId);
}
