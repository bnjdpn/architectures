package fr.xebia.architectures.hexagonal.business.model;

import java.util.Currency;
import java.util.Locale;

public class Account {

    private String id;

    private String name;

    private String clientId;

    private double amount;

    private boolean allowNegativeAmount = true;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
}
