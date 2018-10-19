package fr.xebia.architectures.hexagonal.domain.account;

import fr.xebia.architectures.hexagonal.domain.iban.Iban;

import java.util.Collections;
import java.util.Currency;

public class AccountManager implements AccountManagement {

    private Iban iban;
    private AccountRepository accountRepository;

    public AccountManager(Iban iban, AccountRepository accountRepository) {
        this.iban = iban;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account open(String name, Currency currency, boolean allowNegativeAmount) {
        return accountRepository
                .open(new Account(iban.getNewIban(), name, allowNegativeAmount, currency, Collections.emptySet()));
    }

    @Override
    public Account getByIban(String iban) {
        return accountRepository.getByIban(iban);
    }
}
