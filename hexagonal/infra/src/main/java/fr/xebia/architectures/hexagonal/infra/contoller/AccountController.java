package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.domain.account.AccountManagement;
import fr.xebia.architectures.hexagonal.infra.entity.MongoAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountManagement accountManagement;

    @Autowired
    public AccountController(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @PostMapping
    public MongoAccount create(@Valid MongoAccount mongoAccount) {
        return MongoAccount.from(
                accountManagement.open(mongoAccount.getName(),
                        mongoAccount.getCurrency(),
                        mongoAccount.isAllowNegativeAmount()));
    }

    @GetMapping("/{id}")
    public MongoAccount getById(@PathVariable String id) {
        return MongoAccount.from(accountManagement.getByIban(id));
    }

}
