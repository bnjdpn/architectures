package fr.xebia.architectures.hexagonal.infra.service;

import fr.xebia.architectures.hexagonal.domain.provider.service.CurrencyServiceProvider;

import java.util.Currency;

public class CurrencyServiceProviderMock implements CurrencyServiceProvider {

    @Override
    public double getRate(Currency from, Currency to) {
        return 1.42;
    }
}
