package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import fr.xebia.architectures.hexagonal.domain.operation.Deposit;
import fr.xebia.architectures.hexagonal.domain.operation.Operation;
import fr.xebia.architectures.hexagonal.domain.operation.Withdraw;
import fr.xebia.architectures.hexagonal.infra.entity.MongoAccount;
import fr.xebia.architectures.hexagonal.infra.entity.MongoOperation;
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

    private Deposit deposit;
    private Withdraw withdraw;

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @Autowired
    public OperationController(Deposit deposit, Withdraw withdraw, AccountRepository accountRepository,
                               OperationRepository operationRepository) {
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public void saveOperation(@Valid MongoOperation mongoOperation) {

        MongoAccount mongoAccount = accountRepository.findById(mongoOperation.getAccountId())
                .orElseThrow(() -> new UnsupportedOperationException("Account not found"));

        Account domainAccount = mongoAccount.to(operationRepository.findOperationByAccountId(mongoAccount.getId()));

        Account accountUpdated;

        switch (Operation.OperationType.getFromAmount(mongoOperation.getAmount())) {
            case DEPOSIT:
                accountUpdated =
                        deposit.make(domainAccount, mongoOperation.getLabel(), mongoOperation.getAmount(), mongoOperation.getCurrency());
                break;
            case WITHDRAWAL:
                accountUpdated =
                        withdraw.make(domainAccount, mongoOperation.getLabel(), mongoOperation.getAmount(), mongoOperation.getCurrency());
                break;
            default:
                throw new UnsupportedOperationException();
        }

        accountRepository.save(MongoAccount.from(accountUpdated));
        operationRepository.save(mongoOperation);
    }

    @GetMapping
    public List<MongoOperation> findOperations(@RequestParam("accountId") String accountId,
                                               @RequestParam("startOperationDate") Instant startOperationDate,
                                               @RequestParam("endOperationDate") Instant endOperationDate) {
        return operationRepository.findOperationsByAccountIdAndDateBetweenOrderByDateDesc(accountId, startOperationDate, endOperationDate);
    }

}
