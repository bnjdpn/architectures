package fr.xebia.architectures.hexagonal.domain.operation;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.Currency;
import java.util.UUID;

import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;

class MakeDepositTest {

    private static final double RATE = 2;

    private static MakeDeposit makeDeposit;

    @BeforeAll
    static void beforeAll() {
        makeDeposit = new MakeDeposit((from, to) -> RATE);
    }

    @Test
    void should_add_operation_when_deposit() {
        // Given
        Account account = an_account_with_one_deposit_of_20EUR();

        // When
        Account accountUpdated = makeDeposit.make(account, "New Deposit", 10, Currency.getInstance("EUR"));

        // Then
        assertThat(accountUpdated.operations).hasSize(2);
        assertThat(accountUpdated.getAmount()).isEqualTo(40);
    }

    private Account an_account_with_one_deposit_of_20EUR() {
        Operation operation = new Operation("Operation Deposit", 20, Instant.now(), DEPOSIT);
        return new Account(UUID.randomUUID().toString(), "An Account", false, Currency.getInstance("EUR"),
                Collections.singleton(operation));
    }

}
