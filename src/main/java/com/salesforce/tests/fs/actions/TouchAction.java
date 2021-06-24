package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import com.salesforce.tests.fs.entities.File;
import com.salesforce.tests.fs.exceptions.RepeatedEntryNameException;
import lombok.NonNull;

import java.util.List;

public class TouchAction implements CommandAction {
    private static final String REPEATED_FILE_NAME_MESSAGE = "File already exists";

    @Override
    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        try {
            var fileName = parameters.get(0);
            new File(fileName, currentDirectory);
        } catch (RepeatedEntryNameException ex) {
            System.out.println(REPEATED_FILE_NAME_MESSAGE);
        }
        return currentDirectory;
    }

}
