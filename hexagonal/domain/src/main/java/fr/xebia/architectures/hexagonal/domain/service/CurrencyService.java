package fr.xebia.architectures.hexagonal.domain.service;

import fr.xebia.architectures.hexagonal.domain.provider.service.CurrencyServiceProvider;
import java.util.Currency;

public class CurrencyService {

    private CurrencyServiceProvider currencyServiceProvider;

    public CurrencyService(CurrencyServiceProvider currencyServiceProvider) {
        this.currencyServiceProvider = currencyServiceProvider;
    }

    public double convertAmount(double amount, Currency from, Currency to) {
        return amount * currencyServiceProvider.getRate(from, to);
    }

}
