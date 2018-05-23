package fr.xebia.architectures.hexagonal.domain.account;

import fr.xebia.architectures.hexagonal.domain.operation.Operation;

import java.util.*;

public class Account {

    private String iban = UUID.randomUUID().toString();

    private String name = "";

    private boolean allowNegativeAmount = true;

    private Currency currency = Currency.getInstance(Locale.getDefault());

    private Set<Operation> operations = new TreeSet<>(Comparator.comparing(Operation::getDate));

    public String getIban() {
        return iban;
    }

    public String getName() {
        return name;
    }

    public boolean isAllowNegativeAmount() {
        return allowNegativeAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Set<Operation> getOperations() {
        return Collections.unmodifiableSet(operations);
    }

    protected void addOperation(Operation operation) {
        operations.add(operation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    public static final class Builder {

        private String iban;
        private String name;
        private boolean allowNegativeAmount = true;
        private Currency currency = Currency.getInstance(Locale.getDefault());
        private Set<Operation> operations;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withIban(String iban) {
            this.iban = iban;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAllowNegativeAmount(boolean allowNegativeAmount) {
            this.allowNegativeAmount = allowNegativeAmount;
            return this;
        }

        public Builder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder withOperations(Set<Operation> operations) {
            this.operations = operations;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.operations = this.operations;
            account.allowNegativeAmount = this.allowNegativeAmount;
            account.name = this.name;
            account.currency = this.currency;
            account.iban = this.iban;
            return account;
        }
    }
}
