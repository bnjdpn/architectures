package fr.xebia.architectures.hexagonal.infra.entity;

import fr.xebia.architectures.hexagonal.domain.operation.OperationType;
import java.time.Instant;
import java.util.Currency;
import java.util.Locale;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import static fr.xebia.architectures.hexagonal.domain.operation.Operation.Builder.newInstance;

@Getter
@Setter
@Accessors(fluent = true, chain = true)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotEmpty
    private String accountId;

    @NotNull
    private Instant date = Instant.now();

    @NotEmpty
    private String label;

    @NotNull
    private double amount;

    @NotNull
    private Currency currency = Currency.getInstance(Locale.getDefault());

    public fr.xebia.architectures.hexagonal.domain.operation.Operation toDomainOperation() {
        return newInstance().withAmount(amount).withCurrency(currency).withDate(date).withLabel(label)
                .withOperationType(amount > 0 ? OperationType.DEPOSIT : OperationType.WITHDRAWAL).build();
    }
}
