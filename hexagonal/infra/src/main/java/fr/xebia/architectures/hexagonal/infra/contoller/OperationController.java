package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.domain.provider.application.AccountOperationApplicationProvider;
import fr.xebia.architectures.hexagonal.infra.entity.Account;
import fr.xebia.architectures.hexagonal.infra.entity.Operation;
import fr.xebia.architectures.hexagonal.infra.repository.AccountRepository;
import fr.xebia.architectures.hexagonal.infra.repository.OperationRepository;
import java.time.Instant;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    private AccountOperationApplicationProvider accountOperationApplicationProvider;

    @Autowired
    public OperationController(AccountOperationApplicationProvider accountOperationApplicationProvider, AccountRepository accountRepository,
                               OperationRepository operationRepository) {
        this.accountOperationApplicationProvider = accountOperationApplicationProvider;
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public void saveOperation(@Valid Operation operation) {

        Account account =
                accountRepository.findById(operation.accountId()).orElseThrow(() -> new UnsupportedOperationException("Account not found"));
        fr.xebia.architectures.hexagonal.domain.account.Account domainAccount =
                account.toDomainAccount(operationRepository.findOperationByAccountId(account.id()));

        fr.xebia.architectures.hexagonal.domain.account.Account domainAccountUpdated;
        if (operation.amount() > 0) {
            domainAccountUpdated = accountOperationApplicationProvider
                    .makeDeposit(domainAccount, operation.label(), operation.amount(), operation.currency());
        } else {
            domainAccountUpdated = accountOperationApplicationProvider
                    .makeWithdrawal(domainAccount, operation.label(), operation.amount(), operation.currency());
        }

        accountRepository.save(Account.fromDomainAccount(domainAccountUpdated));
        operationRepository.save(operation);
    }

    @GetMapping
    public List<Operation> findOperations(@RequestParam("accountId") String accountId,
                                          @RequestParam("startOperationDate") Instant startOperationDate,
                                          @RequestParam("endOperationDate") Instant endOperationDate) {
        return operationRepository.findOperationsByAccountIdAndDateBetweenOrderByDateDesc(accountId, startOperationDate, endOperationDate);
    }

}
