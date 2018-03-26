package fr.xebia.architectures.layered.business.exception;

public class NotFoundException extends RuntimeException {

    private static final String MESSAGE = "%s not foud with ID %s";

    public NotFoundException(Class clazz, String id) {
        super(String.format(MESSAGE, clazz.getName(), id));
    }

}
