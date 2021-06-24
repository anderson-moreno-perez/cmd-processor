package com.salesforce.tests.fs.receivers;

import com.salesforce.tests.fs.enums.Command;
import com.salesforce.tests.fs.exceptions.ApplicationTerminationException;
import com.salesforce.tests.fs.exceptions.CommandReadException;
import com.salesforce.tests.fs.managers.CommandManager;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static com.salesforce.tests.fs.utils.StringUtils.EMPTY;
import static com.salesforce.tests.fs.utils.StringUtils.SPACE_CHAR;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
public class CommandReceiver {
    private static final String UNRECOGNIZED_COMMAND_MESSAGE = "Unrecognized command";

    CommandManager commandManager;

    public void receive() {
        var bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        do {
            var commandAndParameters = getCommandAndParameters(bufferedReader);
            var commandName = getCommandName(commandAndParameters);
            var parameters = getParameters(commandAndParameters);

            try {
                executeCommand(commandName, parameters);
            } catch (ApplicationTerminationException ex) {
                break;
            }
        } while (true);
    }

    private String[] getCommandAndParameters(BufferedReader bufferedReader) {
        try {
            var line = bufferedReader.readLine();

            if (isNull(line)) {
                return new String[]{EMPTY};
            }

            return line.split(SPACE_CHAR, 2);
        } catch (IOException ex) {
            throw new CommandReadException(ex);
        }
    }

    private String getCommandName(String[] commandAndParameters) {
        return commandAndParameters[0].toLowerCase();
    }

    private List<String> getParameters(String[] commandAndParameters) {
        if (commandAndParameters.length < 2) {
            return List.of();
        }

        return List.of(commandAndParameters[1].split(SPACE_CHAR));
    }

    private void executeCommand(String commandName, List<String> parameters) {
        if (EMPTY.equals(commandName)) {
            return;
        }

        var commandOpt = Command.from(commandName);

        if (commandOpt.isEmpty()) {
            System.out.println(UNRECOGNIZED_COMMAND_MESSAGE);
            return;
        }

        commandManager.execute(commandOpt.get(), parameters);
    }

}
