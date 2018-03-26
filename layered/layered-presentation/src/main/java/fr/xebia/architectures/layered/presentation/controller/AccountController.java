package fr.xebia.architectures.layered.presentation.controller;

import fr.xebia.architectures.layered.business.service.AccountService;
import fr.xebia.architectures.layered.persistence.model.Account;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Inject
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account create(@Valid Account account) {
        return accountService.createOrUpdateAccount(account);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable String id) {
        return accountService.findAccount(id);
    }

    @GetMapping
    public List<Account> getByClientId(@RequestParam("clientId") String clientId) {
        return accountService.findAccountsByClient(clientId);
    }

}
