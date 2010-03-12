package org.knipsX.utils;

import org.apache.log4j.BasicConfigurator;

/**
 * Utility class for things required for better and easier testing
 * 
 * @author Kevin Zuber
 * 
 */
public final class UtilsForTesting {

    /**
     * Initialize the logger system.
     */
    static void initLogger() {
        /* setting up the logger */
        BasicConfigurator.configure();
    }

    private UtilsForTesting() {

    }
}
