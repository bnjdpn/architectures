package fr.xebia.architectures.hexagonal.domain.provider;

import java.util.Currency;

public interface CurrencyProvider {

    double getRate(Currency from, Currency to);

}
