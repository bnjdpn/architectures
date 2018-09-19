package fr.xebia.architectures.hexagonal.domain.provider.application;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import java.util.Currency;

public interface AccountOperationApplicationProvider {

    Account makeDeposit(Account account, String label, double amount, Currency currency);

    Account makeWithdrawal(Account account, String label, double amount, Currency currency);

}
