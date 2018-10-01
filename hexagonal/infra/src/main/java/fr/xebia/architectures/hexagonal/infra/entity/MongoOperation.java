package fr.xebia.architectures.hexagonal.infra.entity;

import fr.xebia.architectures.hexagonal.domain.operation.Operation;
import fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType;
import java.time.Instant;
import java.util.Currency;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MongoOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotEmpty
    private String accountId;

    @NotNull
    private Instant date;

    @NotEmpty
    private String label;

    private double amount;

    @NotNull
    private Currency currency;

    public MongoOperation(@NotEmpty String accountId, @NotNull Instant date, @NotEmpty String label, double amount,
                          @NotNull Currency currency) {
        this.accountId = accountId;
        this.date = date;
        this.label = label;
        this.amount = amount;
        this.currency = currency;
    }

    public Operation to() {
        return new Operation(label, amount, date, OperationType.getFromAmount(amount));
    }

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
}
