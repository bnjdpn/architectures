package fr.xebia.architectures.hexagonal.domain.operation;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import fr.xebia.architectures.hexagonal.domain.currency.CurrencyChangeRate;

import java.time.Instant;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.WITHDRAWAL;

public class MakeWithdraw implements Withdraw {

    private CurrencyChangeRate currencyChangeRate;

    public MakeWithdraw(CurrencyChangeRate currencyChangeRate) {
        this.currencyChangeRate = currencyChangeRate;
    }

    @Override
    public Account make(Account account, String label, double amount, Currency currency) {

        double amountAsAccountCurrent = currencyChangeRate.getRate(currency, account.currency) * amount;
        Operation operation = new Operation(label, amountAsAccountCurrent, Instant.now(), WITHDRAWAL);

        if (isWithdrawAuthorized(account, operation)) {

            Set<Operation> operations = new HashSet<>(account.operations);
            operations.add(operation);

            return new Account(account.iban, account.name, account.allowNegativeAmount, account.currency, operations);
        }

        throw new UnauthorizedOperationException();
    }

    private boolean isWithdrawAuthorized(Account account, Operation operation) {

        if (account.allowNegativeAmount) {
            return true;
        }

        return account.getAmount() - operation.amount >= 0;
    }
}
