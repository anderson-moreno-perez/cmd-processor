package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import com.salesforce.tests.fs.exceptions.RepeatedEntryNameException;
import lombok.NonNull;

import java.util.List;

public class MkdirAction implements CommandAction {
    private static final String REPEATED_DIRECTORY_NAME_MESSAGE = "Directory already exists";

    @Override
    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        try {
            var directoryName = parameters.get(0);
            new Directory(directoryName, currentDirectory);
        } catch (RepeatedEntryNameException ex) {
            System.out.println(REPEATED_DIRECTORY_NAME_MESSAGE);
        }
        return currentDirectory;
    }

}
