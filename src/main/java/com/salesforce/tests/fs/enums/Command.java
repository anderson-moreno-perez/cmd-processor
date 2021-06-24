package com.salesforce.tests.fs.enums;

import com.salesforce.tests.fs.actions.CdAction;
import com.salesforce.tests.fs.actions.CommandAction;
import com.salesforce.tests.fs.actions.LsAction;
import com.salesforce.tests.fs.actions.MkdirAction;
import com.salesforce.tests.fs.actions.PwdAction;
import com.salesforce.tests.fs.actions.QuitAction;
import com.salesforce.tests.fs.actions.TouchAction;
import com.salesforce.tests.fs.entities.Directory;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public enum Command {
    QUIT("quit", new QuitAction()),
    PWD("pwd", new PwdAction()),
    LS("ls", new LsAction()),
    MKDIR("mkdir", new MkdirAction()),
    CD("cd", new CdAction()),
    TOUCH("touch", new TouchAction());

    String commandName;
    CommandAction commandAction;

    Command(String commandName, CommandAction commandAction) {
        this.commandName = commandName;
        this.commandAction = commandAction;
        Holder.COMMAND_BY_NAME.put(commandName, this);
    }

    public Directory execute(@NonNull Directory currentDirectory, @NonNull List<String> parameters) {
        return this.commandAction.execute(currentDirectory, parameters);
    }

    @Override
    public String toString() {
        return commandName;
    }

    public static Optional<Command> from(String commandName) {
        return Optional.ofNullable(Holder.COMMAND_BY_NAME.get(commandName));
    }

    private static class Holder {
        private static final Map<String, Command> COMMAND_BY_NAME = new HashMap<>();
    }

}
