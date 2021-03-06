package org.knipsX.utils;

import java.io.File;

/**
 * Helps to handle with Repositories.
 */
public final class RepositoryHandler {

    /**
     * Determines the path to the directory where all projects of the program are stored.
     */
    public static final String PROJECTS_PATH = System.getProperty("user.home") + File.separator + ".knipsX";

    /*
     * Set which repository should be used.
     */
    private static Repository currentRepo = new XMLRepository();

    /**
     * Get the active Repository implementation.
     * 
     * @return the active Repository implementation
     */
    public static Repository getRepository() {
        return RepositoryHandler.currentRepo;
    }

    private RepositoryHandler() {

    }
}
