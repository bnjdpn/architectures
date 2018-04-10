package fr.xebia.architectures.hexagonal.domain.exception;

import fr.xebia.architectures.hexagonal.domain.Account;
import fr.xebia.architectures.hexagonal.domain.Operation;

public class UnauthorizedOperationException extends RuntimeException {

    private static final String MESSAGE = "Unauthorized operation %s on account %s";

    public UnauthorizedOperationException(Account account, Operation operation) {
        super(String.format(MESSAGE, operation, account.getIban()));
    }

}
