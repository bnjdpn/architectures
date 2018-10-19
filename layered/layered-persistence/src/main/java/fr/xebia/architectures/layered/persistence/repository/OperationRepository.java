package fr.xebia.architectures.layered.persistence.repository;

import fr.xebia.architectures.layered.persistence.model.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface OperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findOperationsByAccountIdAndDateBetweenOrderByDateDesc(String accountId, Instant start,
                                                                           Instant end);
}
