package fr.xebia.architectures.hexagonal.infra.entity;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class MongoAccount {

    @Id
    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @NotNull
    private Currency currency;

    private boolean allowNegativeAmount;

    private double amount;

    public MongoAccount(@NotEmpty String id, @NotEmpty String name, @NotNull Currency currency, boolean allowNegativeAmount,
                        double amount) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.allowNegativeAmount = allowNegativeAmount;
        this.amount = amount;
    }

    public static MongoAccount from(Account account) {
        return new MongoAccount(account.iban, account.name, account.currency, account.allowNegativeAmount, account.getAmount());
    }

    public Account toAccount(List<MongoOperation> operations) {
        return new Account(id, name, allowNegativeAmount, currency,
                           operations.stream().map(MongoOperation::to).collect(Collectors.toSet()));
    }

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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public boolean isAllowNegativeAmount() {
        return allowNegativeAmount;
    }

    public void setAllowNegativeAmount(boolean allowNegativeAmount) {
        this.allowNegativeAmount = allowNegativeAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
