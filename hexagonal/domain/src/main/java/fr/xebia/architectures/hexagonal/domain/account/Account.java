package fr.xebia.architectures.hexagonal.domain.account;

import fr.xebia.architectures.hexagonal.domain.operation.Operation;
import java.util.Currency;
import java.util.Set;

public class Account {

    public final String iban;
    public final String name;
    public final Currency currency;
    public final Set<Operation> operations;
    public final boolean allowNegativeAmount;

    public Account(String iban, String name, boolean allowNegativeAmount, Currency currency, Set<Operation> operations) {
        this.iban = iban;
        this.name = name;
        this.allowNegativeAmount = allowNegativeAmount;
        this.currency = currency;
        this.operations = operations;
    }

    public double getAmount() {
        return operations.stream().mapToDouble(o -> o.amount).sum();
    }
}
