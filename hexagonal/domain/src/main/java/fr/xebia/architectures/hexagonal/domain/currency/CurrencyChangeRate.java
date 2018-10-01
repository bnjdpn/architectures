package fr.xebia.architectures.hexagonal.domain.currency;

import java.util.Currency;

@FunctionalInterface
public interface CurrencyChangeRate {

    double getRate(Currency from, Currency to);

}
