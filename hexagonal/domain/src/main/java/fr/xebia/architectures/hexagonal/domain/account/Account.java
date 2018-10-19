package fr.xebia.architectures.hexagonal.domain.account;

import fr.xebia.architectures.hexagonal.domain.operation.Operation;

import java.util.Currency;
import java.util.Set;

import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.DEPOSIT;
import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.WITHDRAWAL;

public class Account {

    public final String iban;
    public final String name;
    public final Currency currency;
    public final Set<Operation> operations;
    public final boolean allowNegativeAmount;

    public Account(String iban, String name, boolean allowNegativeAmount, Currency currency,
                   Set<Operation> operations) {
        this.iban = iban;
        this.name = name;
        this.allowNegativeAmount = allowNegativeAmount;
        this.currency = currency;
        this.operations = operations;
    }

    public double getAmount() {
        return operations.stream().mapToDouble(operation -> {
            if (DEPOSIT.equals(operation.operationType)) {
                return operation.amount;
            } else if (WITHDRAWAL.equals(operation.operationType)) {
                return -operation.amount;
            } else {
                return 0;
            }
        }).sum();
    }
}
