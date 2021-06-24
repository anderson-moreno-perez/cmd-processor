package com.salesforce.tests.fs.exceptions;

public class RepeatedEntryNameException extends IllegalArgumentException {
    private static final long serialVersionUID = 3776140094478837902L;
    private static final String ERROR_MESSAGE_TEMPLATE = "The name [%s] already exists, "
            + "the entry name cannot be the same as the name already exists in the parent";

    public RepeatedEntryNameException(String entryName) {
        super(String.format(ERROR_MESSAGE_TEMPLATE, entryName));
    }

}
