package fr.xebia.architectures.layered.business.service;

import fr.xebia.architectures.layered.business.exception.UnauthorizedOperationException;
import fr.xebia.architectures.layered.business.util.CurrencyUtil;
import fr.xebia.architectures.layered.persistence.model.Account;
import fr.xebia.architectures.layered.persistence.model.Operation;
import fr.xebia.architectures.layered.persistence.repository.OperationRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Currency;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class OperationService {

    private AccountService accountService;
    private OperationRepository operationRepository;

    @Inject
    public OperationService(AccountService accountService,
                            OperationRepository operationRepository) {
        this.accountService = requireNonNull(accountService);
        this.operationRepository = requireNonNull(operationRepository);
    }

    public List<Operation> findOperations(String accountId,
                                          Instant startOperationDate,
                                          Instant endOperationDate) {

        return operationRepository.findOperationsByAccountIdAndDateBetweenOrderByDateDesc(accountId,
                startOperationDate,
                endOperationDate);
    }

    public void saveOperation(Operation operation) {

        Account account = accountService.findAccount(operation.getAccountId());

        double newPotentialAccountAmount = getNewPotentialAccountAmount(account, operation);

        if (!isOperationAllowed(account, newPotentialAccountAmount)) {
            throw new UnauthorizedOperationException(account, operation);
        }

        account.setAmount(newPotentialAccountAmount);

        accountService.createOrUpdateAccount(account);
        operationRepository.save(requireNonNull(operation));
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
