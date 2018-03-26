package fr.xebia.architectures.hexagonal.business.provider.service;

import fr.xebia.architectures.hexagonal.business.model.Operation;
import java.time.Instant;
import java.util.List;

public interface OperationServiceProvider {

    Operation save(Operation operation);

    List<Operation> findOperations(String accountId,
                                   Instant startOperationDate,
                                   Instant endOperationDate);

}
