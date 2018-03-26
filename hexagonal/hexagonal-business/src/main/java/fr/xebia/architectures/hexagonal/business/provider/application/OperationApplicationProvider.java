package fr.xebia.architectures.hexagonal.business.provider.application;

import fr.xebia.architectures.hexagonal.business.model.Operation;
import java.time.Instant;
import java.util.List;

public interface OperationApplicationProvider {

    void saveOperation(Operation operation);

    List<Operation> findOperations(String accountId,
                                   Instant startOperationDate,
                                   Instant endOperationDate);

}
