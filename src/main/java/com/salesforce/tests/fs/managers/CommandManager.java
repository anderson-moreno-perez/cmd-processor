package com.salesforce.tests.fs.managers;

import com.salesforce.tests.fs.entities.Directory;
import com.salesforce.tests.fs.enums.Command;
import com.salesforce.tests.fs.exceptions.MaximumNameLengthException;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
public class CommandManager {
    private static final String INVALID_COMMAND_MESSAGE = "Invalid Command";
    private static final String INVALID_ENTRY_NAME_MESSAGE = "Invalid File or Folder Name";

    Directory currentDirectory;

    CommandManager() {
        this.currentDirectory = createRootDirectory();
    }

    public void execute(@NonNull Command command, @NonNull List<String> parameters) {
        try {
            currentDirectory = command.execute(currentDirectory, parameters);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(INVALID_COMMAND_MESSAGE);
        } catch (MaximumNameLengthException ex) {
            System.out.println(INVALID_ENTRY_NAME_MESSAGE);
        }
    }

    private Directory createRootDirectory() {
        return Directory.createRoot();
    }

}
