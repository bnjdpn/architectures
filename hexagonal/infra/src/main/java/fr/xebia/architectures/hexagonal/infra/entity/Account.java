package fr.xebia.architectures.hexagonal.infra.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotEmpty
    private String name;

    private double amount;

    private boolean allowNegativeAmount = true;

    @NotNull
    private Currency currency = Currency.getInstance(Locale.getDefault());

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isAllowNegativeAmount() {
        return allowNegativeAmount;
    }

    public void setAllowNegativeAmount(boolean allowNegativeAmount) {
        this.allowNegativeAmount = allowNegativeAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public fr.xebia.architectures.hexagonal.domain.account.Account toDomainAccount(List<Operation> operations) {
        return fr.xebia.architectures.hexagonal.domain.account.Account.Builder.newInstance()
                .withAllowNegativeAmount(allowNegativeAmount)
                .withCurrency(currency)
                .withIban(id)
                .withName(name)
                .withOperations(operations.stream().map(Operation::toDomainOperation).collect(Collectors.toSet())).build();
    }

    public static Account fromDomainAccount(fr.xebia.architectures.hexagonal.domain.account.Account account) {
        return new Account()
                .id(account.getIban())
                .name(account.getName())
                .amount(account.getOperations().stream().mapToDouble(fr.xebia.architectures.hexagonal.domain.operation.Operation::getAmount).sum())
                .allowNegativeAmount(account.isAllowNegativeAmount())
                .currency(account.getCurrency());
    }

    public Account id(String id) {
        this.id = id;
        return this;
    }

    public Account name(String name) {
        this.name = name;
        return this;
    }

    public Account amount(double amount) {
        this.amount = amount;
        return this;
    }

    public Account allowNegativeAmount(boolean allowNegativeAmount) {
        this.allowNegativeAmount = allowNegativeAmount;
        return this;
    }

    public Account currency(Currency currency) {
        this.currency = currency;
        return this;
    }
}
