package fr.xebia.architectures.hexagonal.business.model;

import java.time.Instant;
import java.util.Currency;
import java.util.Locale;

public class Operation {

    private String id;

    private String accountId;

    private Instant date = Instant.now();

    private String label;

    private double amount;

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
}
