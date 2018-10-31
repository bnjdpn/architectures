package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import fr.xebia.architectures.hexagonal.domain.operation.Deposit;
import fr.xebia.architectures.hexagonal.domain.operation.Operation;
import fr.xebia.architectures.hexagonal.domain.operation.Withdraw;
import fr.xebia.architectures.hexagonal.infra.entity.MongoAccount;
import fr.xebia.architectures.hexagonal.infra.entity.MongoOperation;
import fr.xebia.architectures.hexagonal.infra.repository.MongoAccountRepository;
import fr.xebia.architectures.hexagonal.infra.repository.MongoOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private Deposit deposit;
    private Withdraw withdraw;

    private MongoAccountRepository mongoAccountRepository;
    private MongoOperationRepository mongoOperationRepository;

    @Autowired
    public OperationController(Deposit deposit, Withdraw withdraw, MongoAccountRepository mongoAccountRepository,
                               MongoOperationRepository mongoOperationRepository) {
        this.deposit = deposit;
        this.withdraw = withdraw;
        this.mongoOperationRepository = mongoOperationRepository;
        this.mongoAccountRepository = mongoAccountRepository;
    }

    @PostMapping
    public void saveOperation(@Valid MongoOperation mongoOperation) {

        MongoAccount mongoAccount = mongoAccountRepository.findById(mongoOperation.getAccountId())
                .orElseThrow(() -> new UnsupportedOperationException("Account not found"));

        Account account =
                mongoAccount.toAccount(mongoOperationRepository.findOperationByAccountId(mongoAccount.getId()));

        Account accountUpdated;

        switch (Operation.OperationType.getFromAmount(mongoOperation.getAmount())) {
            case DEPOSIT:
                accountUpdated =
                        deposit.make(account, mongoOperation.getLabel(), mongoOperation.getAmount(),
                                mongoOperation.getCurrency());
                break;
            case WITHDRAWAL:
                accountUpdated =
                        withdraw.make(account, mongoOperation.getLabel(), mongoOperation.getAmount(),
                                mongoOperation.getCurrency());
                break;
            default:
                throw new UnsupportedOperationException();
        }

        mongoAccountRepository.save(MongoAccount.from(accountUpdated));
        mongoOperationRepository.save(mongoOperation);
    }
}
