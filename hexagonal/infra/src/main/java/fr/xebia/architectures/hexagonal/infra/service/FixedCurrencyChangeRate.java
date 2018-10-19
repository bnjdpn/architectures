package fr.xebia.architectures.hexagonal.infra.service;

import fr.xebia.architectures.hexagonal.domain.currency.CurrencyChangeRate;

import java.util.Currency;

public class FixedCurrencyChangeRate implements CurrencyChangeRate {

    @Override
    public double getRate(Currency from, Currency to) {
        return 1.42;
    }
}
