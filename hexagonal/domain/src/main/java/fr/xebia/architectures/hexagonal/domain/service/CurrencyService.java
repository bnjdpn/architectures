package fr.xebia.architectures.hexagonal.domain.service;

import fr.xebia.architectures.hexagonal.domain.provider.CurrencyProvider;
import java.util.Currency;

public class CurrencyService {

    private CurrencyProvider currencyProvider;

    public CurrencyService(CurrencyProvider currencyProvider) {
        this.currencyProvider = currencyProvider;
    }

    public double convertAmount(double amount, Currency from, Currency to) {
        return amount * currencyProvider.getRate(from, to);
    }

}
