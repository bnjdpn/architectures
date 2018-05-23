package fr.xebia.architectures.hexagonal.domain.provider.service;

import java.util.Currency;

public interface CurrencyServiceProvider {

    double getRate(Currency from, Currency to);

}
