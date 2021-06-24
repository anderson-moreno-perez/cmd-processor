package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import com.salesforce.tests.fs.entities.Entry;
import lombok.NonNull;

import java.util.List;

public class LsAction implements CommandAction {

    @Override
    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        currentDirectory.getContent().stream().map(Entry::getName).forEach(System.out::println);
        return currentDirectory;
    }

}
