package fr.xebia.architectures.layered.business.util;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Currency;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrencyUtil {

    private static final Map<RatesKey, Double>
        RATES =
        Collections.unmodifiableMap(Stream
            .of(new AbstractMap.SimpleEntry<>(new RatesKey("EUR", "USD"), 1.18214),
                new AbstractMap.SimpleEntry<>(new RatesKey("USD", "EUR"), 0.845945))
            .collect(Collectors
                .toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue)));

    private CurrencyUtil() {
        // Util
    }

    public static double convertAmount(double amount, Currency from, Currency to) {

        if (from.equals(to)) {
            return amount;
        } else {
            return amount * RATES.getOrDefault(new RatesKey(from, to), 1D);
        }
    }

    private static class RatesKey {
        private Currency from;
        private Currency to;

        RatesKey(String from, String to) {
            this.from = Currency.getInstance(from);
            this.to = Currency.getInstance(to);
        }

        RatesKey(Currency from, Currency to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof RatesKey))
                return false;
            RatesKey ratesKey = (RatesKey) o;
            return Objects.equals(from, ratesKey.from) && Objects.equals(to, ratesKey.to);
        }

        @Override
        public int hashCode() {

            return Objects.hash(from, to);
        }
    }

}
