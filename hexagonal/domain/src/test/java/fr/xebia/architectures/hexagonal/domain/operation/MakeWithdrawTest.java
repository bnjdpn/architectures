package fr.xebia.architectures.hexagonal.domain.operation;

import fr.xebia.architectures.hexagonal.domain.account.Account;
import java.time.Instant;
import java.util.Collections;
import java.util.Currency;
import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static fr.xebia.architectures.hexagonal.domain.operation.Operation.OperationType.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MakeWithdrawTest {

    private static final double RATE = 1;

    private static MakeWithdraw makeWithdraw;

    @BeforeAll
    public static void beforeAll() {
        makeWithdraw = new MakeWithdraw((from, to) -> RATE);
    }

    @Test
    public void should_add_operation_when_withdraw() {
        // Given
        Account account = an_account_with_one_deposit_of_20EUR();

        // When
        Account accountUpdated = makeWithdraw.make(account, "New Withdraw", 10, Currency.getInstance("EUR"));

        // Then
        assertThat(accountUpdated.operations).hasSize(2);
        assertThat(accountUpdated.getAmount()).isEqualTo(10);
    }

    @Test
    public void should_throw_exception_when_large_withdraw() {
        // Given
        Account account = an_account_with_one_deposit_of_20EUR();

        // Then
        assertThatThrownBy(() -> makeWithdraw.make(account, "Big Withdraw", 1337, Currency.getInstance("EUR")))
                .isInstanceOf(UnauthorizedOperationException.class);
    }

    private Account an_account_with_one_deposit_of_20EUR() {
        Operation operation = new Operation("Operation Deposit", 20, Instant.now(), DEPOSIT);
        return new Account(UUID.randomUUID().toString(), "An Account", false, Currency.getInstance("EUR"),
                           Collections.singleton(operation));
    }

}
