package fr.xebia.architectures.hexagonal.domain;

import fr.xebia.architectures.hexagonal.domain.exception.UnauthorizedOperationException;
import fr.xebia.architectures.hexagonal.domain.service.CurrencyService;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import static fr.xebia.architectures.hexagonal.domain.OperationType.WITHDRAWAL;

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

    public void addOperation(CurrencyService currencyService, Operation operation) {

        boolean operationAuthorized;

        switch (operation.getOperationType()) {
            case DEPOSIT:
                operationAuthorized = true;
                break;
            case WITHDRAWAL:
                operationAuthorized = isWithdrawalAuthorized(currencyService, operation);
                break;
            default:
                operationAuthorized = false;
                break;
        }

        if (operationAuthorized) {
            operations.add(operation);
        } else {
            throw new UnauthorizedOperationException(this, operation);
        }
    }

    private boolean isWithdrawalAuthorized(CurrencyService currencyService, Operation operation) {

        double currentAmount = getAmount(currencyService, currency);

        double
            amountAfterWithdrawal =
            currentAmount - currencyService
                .convertAmount(operation.getAmount(), operation.getCurrency(), currency);

        return allowNegativeAmount || amountAfterWithdrawal >= 0;
    }

    public double getAmount(CurrencyService currencyService, Currency currency) {
        return operations.stream().mapToDouble(operation -> {

            double
                amount =
                currencyService
                    .convertAmount(operation.getAmount(), operation.getCurrency(), currency);

            if (WITHDRAWAL.equals(operation.getOperationType())) {
                amount *= -1;
            }

            return amount;
        }).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    public static final class AccountBuilder {
        private String iban;
        private String name;
        private boolean allowNegativeAmount = true;
        private Currency currency = Currency.getInstance(Locale.getDefault());
        private Set<Operation> operations;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withIban(String iban) {
            this.iban = iban;
            return this;
        }

        public AccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AccountBuilder withAllowNegativeAmount(boolean allowNegativeAmount) {
            this.allowNegativeAmount = allowNegativeAmount;
            return this;
        }

        public AccountBuilder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder withOperations(Set<Operation> operations) {
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
