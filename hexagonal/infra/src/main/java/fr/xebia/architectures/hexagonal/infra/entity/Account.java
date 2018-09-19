package fr.xebia.architectures.hexagonal.infra.entity;

import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Accessors(fluent = true, chain = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotEmpty
    private String name;

    private double amount;

    private boolean allowNegativeAmount = true;

    @NotNull
    private Currency currency = Currency.getInstance(Locale.getDefault());

    public static Account fromDomainAccount(fr.xebia.architectures.hexagonal.domain.account.Account account) {
        return new Account().id(account.getIban()).name(account.getName())
                .amount(account.getOperations().stream().mapToDouble(fr.xebia.architectures.hexagonal.domain.operation.Operation::getAmount)
                                .sum()).allowNegativeAmount(account.isAllowNegativeAmount()).currency(account.getCurrency());
    }

    public fr.xebia.architectures.hexagonal.domain.account.Account toDomainAccount(List<Operation> operations) {
        return fr.xebia.architectures.hexagonal.domain.account.Account.Builder.newInstance().withAllowNegativeAmount(allowNegativeAmount)
                .withCurrency(currency).withIban(id).withName(name)
                .withOperations(operations.stream().map(Operation::toDomainOperation).collect(Collectors.toSet())).build();
    }
}
