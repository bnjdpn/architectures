package fr.xebia.architectures.hexagonal.domain.account;

import fr.xebia.architectures.hexagonal.domain.exception.UnauthorizedOperationException;
import fr.xebia.architectures.hexagonal.domain.operation.Operation;
import fr.xebia.architectures.hexagonal.domain.operation.OperationType;
import fr.xebia.architectures.hexagonal.domain.provider.application.AccountOperationApplicationProvider;
import fr.xebia.architectures.hexagonal.domain.service.CurrencyService;

import java.util.Currency;

import static fr.xebia.architectures.hexagonal.domain.operation.OperationType.DEPOSIT;
import static fr.xebia.architectures.hexagonal.domain.operation.OperationType.WITHDRAWAL;

public class AccountOperationApplicationProviderImpl implements AccountOperationApplicationProvider {

    private CurrencyService currencyService;

    public AccountOperationApplicationProviderImpl(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public Account makeDeposit(Account account, String label, double amount, Currency currency) {
        return addOperation(account, buildOperation(DEPOSIT, label, amount, currency));
    }

    @Override
    public Account makeWithdrawal(Account account, String label, double amount, Currency currency) {
        return addOperation(account, buildOperation(WITHDRAWAL, label, amount, currency));
    }

    private Operation buildOperation(OperationType operationType, String label, double amount, Currency currency) {
        return Operation.Builder.newInstance()
                .withOperationType(operationType)
                .withLabel(label)
                .withAmount(amount)
                .withCurrency(currency)
                .build();
    }

    private Account addOperation(Account account, Operation operation) {
        if (isOperationAuthorized(account, operation)) {
            account.addOperation(operation);
            return account;
        } else {
            throw new UnauthorizedOperationException(account, operation);
        }
    }

    private boolean isOperationAuthorized(Account account, Operation operation) {
        boolean operationAuthorized;

        switch (operation.getOperationType()) {
            case DEPOSIT:
                operationAuthorized = true;
                break;
            case WITHDRAWAL:
                operationAuthorized = isWithdrawalAuthorized(account, operation);
                break;
            default:
                operationAuthorized = false;
                break;
        }

        return operationAuthorized;
    }

    private boolean isWithdrawalAuthorized(Account account, Operation operation) {

        double currentAmount = getAmount(account, operation.getCurrency());

        double
                amountAfterWithdrawal =
                currentAmount - currencyService
                        .convertAmount(operation.getAmount(), operation.getCurrency(), operation.getCurrency());

        return account.isAllowNegativeAmount() || amountAfterWithdrawal >= 0;
    }

    private double getAmount(Account account, Currency currency) {
        return account.getOperations().stream().mapToDouble(operation -> {

            double
                    amount =
                    currencyService
                            .convertAmount(operation.getAmount(), operation.getCurrency(), currency);

            if (WITHDRAWAL.equals(operation.getOperationType())) {
                amount *= -1;
            }

            return amount;
        }).sum();
    }
}
