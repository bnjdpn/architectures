package fr.xebia.architectures.hexagonal.infra.repository;

import fr.xebia.architectures.hexagonal.infra.entity.Operation;
import java.time.Instant;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findOperationsByAccountIdAndDateBetweenOrderByDateDesc(String accountId, Instant start, Instant end);

    List<Operation> findOperationByAccountId(String accountId);
}
