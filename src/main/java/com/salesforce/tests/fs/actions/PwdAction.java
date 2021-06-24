package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import lombok.NonNull;

import java.util.List;

public class PwdAction implements CommandAction {

    @Override
    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        System.out.println(currentDirectory.getFullPath());
        return currentDirectory;
    }


}
