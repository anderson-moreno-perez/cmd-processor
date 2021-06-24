package com.salesforce.tests.fs.exceptions;

public class ApplicationTerminationException extends RuntimeException {
    private static final long serialVersionUID = 3776140094478837902L;
    private static final String ERROR_MESSAGE = "The application was terminated by user";

    public ApplicationTerminationException() {
        super(ERROR_MESSAGE);
    }

}
