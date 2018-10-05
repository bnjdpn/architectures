package fr.xebia.architectures.hexagonal.domain.account;

import java.util.Currency;

public interface AccountManagement {

    Account open(String name, Currency currency, boolean allowNegativeAmount);

    Account getByIban(String iban);

}
