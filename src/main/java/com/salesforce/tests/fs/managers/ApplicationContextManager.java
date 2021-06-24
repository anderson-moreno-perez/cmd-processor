package com.salesforce.tests.fs.managers;

import com.salesforce.tests.fs.receivers.CommandReceiver;

public class ApplicationContextManager {
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error occurred while running the application";

    public static void run(String... args) {
        try {
            var commandManager = new CommandManager();
            var commandReceiver = new CommandReceiver(commandManager);
            commandReceiver.receive();
        } catch (Exception ex) {
            System.out.println(UNEXPECTED_ERROR_MESSAGE);
            ex.printStackTrace(); //ToDo: Change to log file
            System.exit(1);
        }
    }

}
