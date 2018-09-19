package fr.xebia.architectures.hexagonal.domain.operation;

import java.time.Instant;
import java.util.Currency;
import java.util.Locale;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Operation {

    private Instant date = Instant.now();

    private String label;

    private double amount;

    private Currency currency = Currency.getInstance(Locale.getDefault());

    private OperationType operationType;

    public static final class Builder {
        private Instant date = Instant.now();
        private String label;
        private double amount;
        private Currency currency = Currency.getInstance(Locale.getDefault());
        private OperationType operationType;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withDate(Instant date) {
            this.date = date;
            return this;
        }

        public Builder withLabel(String label) {
            this.label = label;
            return this;
        }

        public Builder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder withOperationType(OperationType operationType) {
            this.operationType = operationType;
            return this;
        }

        public Operation build() {
            Operation operation = new Operation();
            operation.currency = this.currency;
            operation.date = this.date;
            operation.amount = this.amount;
            operation.operationType = this.operationType;
            operation.label = this.label;
            return operation;
        }
    }
}
