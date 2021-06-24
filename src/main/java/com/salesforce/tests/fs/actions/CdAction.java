package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import com.salesforce.tests.fs.entities.Entry;
import lombok.NonNull;

import java.util.List;

public class CdAction implements CommandAction {
    private static final String ROOT_DIRECTORY_CHAR = "..";
    private static final String DIRECTORY_NOT_FOUND_MESSAGE = "Directory not found";

    @Override
    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        var directoryName = parameters.get(0);

        if (ROOT_DIRECTORY_CHAR.equals(directoryName)) {
            return changeToParentDirectory(currentDirectory);
        }

        return changeToParentSubdirectory(currentDirectory, directoryName);
    }

    private Directory changeToParentDirectory(Directory currentDirectory) {
        var parentDirectoryOpt = currentDirectory.getParent();

        if (parentDirectoryOpt.isEmpty()) {
            return currentDirectory;
        }

        var parentDirectory = parentDirectoryOpt.get();

        System.out.println(parentDirectory.getFullPath());

        return parentDirectory;
    }

    private Directory changeToParentSubdirectory(Directory currentDirectory, String directoryName) {
        var directoryOpt = currentDirectory.getContent().stream()
                .filter(Entry::isDirectory)
                .filter(entry -> entry.getName().equals(directoryName))
                .findFirst();

        if (directoryOpt.isEmpty()) {
            System.out.println(DIRECTORY_NOT_FOUND_MESSAGE);
            return currentDirectory;
        }

        var directory = directoryOpt.get();

        System.out.println(directory.getFullPath());

        return (Directory) directory;
    }

}
