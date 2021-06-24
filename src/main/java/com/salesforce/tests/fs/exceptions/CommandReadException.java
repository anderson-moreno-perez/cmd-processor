package com.salesforce.tests.fs.exceptions;

public class CommandReadException extends RuntimeException {
    private static final long serialVersionUID = 3776140094478837902L;
    private static final String ERROR_MESSAGE = "The command cannot be read";

    public CommandReadException(Throwable cause) {
        super(ERROR_MESSAGE);
    }

}
