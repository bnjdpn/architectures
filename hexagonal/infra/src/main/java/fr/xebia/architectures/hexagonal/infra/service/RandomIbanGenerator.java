package fr.xebia.architectures.hexagonal.infra.service;

import fr.xebia.architectures.hexagonal.domain.iban.Iban;

import java.util.UUID;

public class RandomIbanGenerator implements Iban {

    @Override
    public String getNewIban() {
        return UUID.randomUUID().toString();
    }
}
