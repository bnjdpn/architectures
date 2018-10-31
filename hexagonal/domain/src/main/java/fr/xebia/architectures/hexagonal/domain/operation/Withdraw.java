package fr.xebia.architectures.hexagonal.domain.operation;

import fr.xebia.architectures.hexagonal.domain.account.Account;

import java.util.Currency;

@FunctionalInterface
public interface Withdraw {

    Account make(Account account, String label, double amount, Currency currency);

}
