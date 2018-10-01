package fr.xebia.architectures.hexagonal.domain.operation;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import java.time.Instant;
import java.util.Collections;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.DEPOSIT;

public class MakeDepositTest {

    private static final double RATE = 2;

    private MakeDeposit makeDeposit;

    @BeforeAll
    public void beforeAll() {
        this.makeDeposit = new MakeDeposit((from, to) -> RATE);
    }

    @Test
    public void should_add_operation_when_deposit() {
        // Given
        Account account = an_account_with_one_deposit_of_20EUR();
        Operation deposit = new Operation("New Deposit", 10, Instant.now(), DEPOSIT);

        // When
        Account accountUpdated = makeDeposit.make(account, "New Deposit", 10, Currency.getInstance("EUR"));

        // Then
    }

    private Account an_account_with_one_deposit_of_20EUR() {
        Operation operation = new Operation("Operation Deposit", 20, Instant.now(), DEPOSIT);
        return new Account(UUID.randomUUID().toString(), "An Account", false, Currency.getInstance("EUR"),
                           Collections.singleton(operation));
    }

}
