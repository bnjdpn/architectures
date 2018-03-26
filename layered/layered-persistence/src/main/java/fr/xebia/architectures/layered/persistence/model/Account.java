package fr.xebia.architectures.layered.persistence.model;

import java.util.Currency;
import java.util.Locale;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String clientId;

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
