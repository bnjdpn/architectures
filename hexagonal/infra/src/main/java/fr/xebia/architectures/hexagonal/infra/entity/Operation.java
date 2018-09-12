package fr.xebia.architectures.hexagonal.infra.entity;

import fr.xebia.architectures.hexagonal.domain.operation.OperationType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Currency;
import java.util.Locale;

import static fr.xebia.architectures.hexagonal.domain.operation.Operation.Builder.newInstance;

public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotEmpty
    private String accountId;

    @NotNull
    private Instant date = Instant.now();

    @NotEmpty
    private String label;

    @NotNull
    private double amount;

    @NotNull
    private Currency currency = Currency.getInstance(Locale.getDefault());

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public fr.xebia.architectures.hexagonal.domain.operation.Operation toDomainOperation() {
        return newInstance().withAmount(amount).withCurrency(currency).withDate(date).withLabel(label).withOperationType(amount > 0 ?
                OperationType.DEPOSIT : OperationType.WITHDRAWAL).build();
    }
}
