package com.salesforce.tests.fs.actions;

import com.salesforce.tests.fs.entities.Directory;
import lombok.NonNull;

import java.util.List;

@FunctionalInterface
public interface CommandAction {

    Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters);

}
