package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import com.salesforce.tests.fs.exceptions.ApplicationTerminationException;
import lombok.NonNull;

import java.util.List;

public class QuitAction implements CommandAction {

    @Override
    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        throw new ApplicationTerminationException();
    }

}
