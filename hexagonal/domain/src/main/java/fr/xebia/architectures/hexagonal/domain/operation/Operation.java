package fr.xebia.architectures.hexagonal.domain.operation;

import java.time.Instant;

public class Operation {

    public final String label;
    public final double amount;
    public final Instant date;
    public final OperationType operationType;

    public Operation(String label, double amount, Instant date, OperationType operationType) {
        this.label = label;
        this.amount = amount;
        this.date = date;
        this.operationType = operationType;
    }

    public enum OperationType {
        DEPOSIT,
        WITHDRAWAL;

        public static OperationType getFromAmount(double amount) {
            return amount >= 0 ? DEPOSIT : WITHDRAWAL;
        }
    }
}
