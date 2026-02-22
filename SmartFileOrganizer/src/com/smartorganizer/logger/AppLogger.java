package com.smartorganizer.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Centralized Logger for the application.
 * 
 * Instead of using System.out.println(),
 * we use proper logging mechanism.
 */
public class AppLogger {

    private static final Logger logger = Logger.getLogger("SmartFileOrganizerLogger");

    static {
        // Configure logger
        logger.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);

        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    /*
     * Returns the shared logger instance.
     */
    public static Logger getLogger() {
        return logger;
    }
}