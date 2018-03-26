package fr.xebia.architectures.hexagonal.business.provider.application.impl;

import fr.xebia.architectures.hexagonal.business.exception.UnauthorizedOperationException;
import fr.xebia.architectures.hexagonal.business.model.Account;
import fr.xebia.architectures.hexagonal.business.model.Operation;
import fr.xebia.architectures.hexagonal.business.provider.application.AccountApplicationProvider;
import fr.xebia.architectures.hexagonal.business.provider.application.OperationApplicationProvider;
import fr.xebia.architectures.hexagonal.business.provider.service.OperationServiceProvider;
import fr.xebia.architectures.hexagonal.business.util.CurrencyUtil;
import java.time.Instant;
import java.util.Currency;
import java.util.List;
import static java.util.Objects.requireNonNull;

public class DefaultOperationApplicationProvider implements OperationApplicationProvider {

    private AccountApplicationProvider accountApplicationProvider;
    private OperationServiceProvider operationServiceProvider;

    public DefaultOperationApplicationProvider(AccountApplicationProvider accountApplicationProvider,
                                               OperationServiceProvider operationServiceProvider) {
        this.accountApplicationProvider = accountApplicationProvider;
        this.operationServiceProvider = operationServiceProvider;
    }

    @Override
    public List<Operation> findOperations(String accountId,
                                          Instant startOperationDate,
                                          Instant endOperationDate) {
        return operationServiceProvider
            .findOperations(accountId, startOperationDate, endOperationDate);
    }

    @Override
    public void saveOperation(Operation operation) {
        Account account = accountApplicationProvider.getById(operation.getAccountId());

        double newPotentialAccountAmount = getNewPotentialAccountAmount(account, operation);

        if (!isOperationAllowed(account, newPotentialAccountAmount)) {
            throw new UnauthorizedOperationException(account, operation);
        }

        account.setAmount(newPotentialAccountAmount);

        accountApplicationProvider.create(account);
        operationServiceProvider.save(requireNonNull(operation));
    }

    private double getNewPotentialAccountAmount(Account account, Operation operation) {

        double accountAmount = account.getAmount();
        double operationAmount = operation.getAmount();

        Currency accountCurrency = account.getCurrency();
        Currency operationCurrency = operation.getCurrency();

        double
            operationAmountWithAccountCurrency =
            CurrencyUtil.convertAmount(operationAmount, operationCurrency, accountCurrency);

        return accountAmount + operationAmountWithAccountCurrency;
    }

    private boolean isOperationAllowed(Account account, double newPotentialAccountAmount) {
        return account.isAllowNegativeAmount() || newPotentialAccountAmount >= 0;
    }
}
