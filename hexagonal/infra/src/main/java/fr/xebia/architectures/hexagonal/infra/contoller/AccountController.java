package fr.xebia.architectures.hexagonal.infra.contoller;

import fr.xebia.architectures.hexagonal.infra.entity.Account;
import fr.xebia.architectures.hexagonal.infra.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping
    public Account create(@Valid Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable String id) {
        return accountRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Account> getByClientId(@RequestParam("clientId") String clientId) {
        return accountRepository.findAccountsByClientId(clientId);
    }

}
