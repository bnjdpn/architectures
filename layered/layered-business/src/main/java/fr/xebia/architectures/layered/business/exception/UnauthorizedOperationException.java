package fr.xebia.architectures.layered.business.exception;

import fr.xebia.architectures.layered.persistence.model.Account;
import fr.xebia.architectures.layered.persistence.model.Operation;

public class UnauthorizedOperationException extends RuntimeException {

    private static final String MESSAGE = "Unauthorized operation %s on account %s";

    public UnauthorizedOperationException(Account account, Operation operation) {
        super(String.format(MESSAGE, operation, account.getId()));
    }

}
