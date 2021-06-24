package com.salesforce.tests.fs.exceptions;

public class MaximumNameLengthException extends IllegalArgumentException {
    private static final long serialVersionUID = 3776140094478837902L;
    private static final String ERROR_MESSAGE_TEMPLATE = "The name length cannot be longer than [%s] characters";

    public MaximumNameLengthException(int nameLength) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, nameLength));
    }

}
