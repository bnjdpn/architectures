package fr.xebia.architectures.hexagonal.domain.iban;

@FunctionalInterface
public interface Iban {

    String getNewIban();

}
