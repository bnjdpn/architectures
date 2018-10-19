package fr.xebia.architectures.hexagonal.domain.operation;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import fr.xebia.architectures.hexagonal.domain.currency.CurrencyChangeRate;

import java.time.Instant;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.DEPOSIT;

public class MakeDeposit implements Deposit {

    private CurrencyChangeRate currencyChangeRate;

    public MakeDeposit(CurrencyChangeRate currencyChangeRate) {
        this.currencyChangeRate = currencyChangeRate;
    }

    @Override
    public Account make(Account account, String label, double amount, Currency currency) {

        double amountAsAccountCurrent = currencyChangeRate.getRate(currency, account.currency) * amount;

        Set<Operation> operations = new HashSet<>(account.operations);
        operations.add(new Operation(label, amountAsAccountCurrent, Instant.now(), DEPOSIT));

        return new Account(account.iban, account.name, account.allowNegativeAmount, account.currency, operations);
    }
}
