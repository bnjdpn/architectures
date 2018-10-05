package fr.xebia.architectures.hexagonal.domain.account;

public interface AccountRepository {

    Account open(Account account);

    Account getByIban(String iban);

}
