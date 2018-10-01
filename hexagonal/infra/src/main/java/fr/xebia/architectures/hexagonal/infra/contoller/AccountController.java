package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.infra.entity.MongoAccount;
import fr.xebia.architectures.hexagonal.infra.repository.AccountRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public MongoAccount create(@Valid MongoAccount mongoAccount) {
        return accountRepository.save(mongoAccount);
    }

    @GetMapping("/{id}")
    public MongoAccount getById(@PathVariable String id) {
        return accountRepository.findById(id).orElse(null);
    }

}
