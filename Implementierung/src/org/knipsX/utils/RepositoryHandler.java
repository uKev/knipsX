package org.knipsX.utils;

import java.io.File;

public final class RepositoryHandler {

    /**
     * Determines the path to the directory where all projects of the program are stored.
     */
    public static final String PROJECTS_PATH = System.getProperty("user.home") + File.separator + ".knipsX";
    
    private static Repository currentRepo = new DummyRepository();

    public static Repository getRepository(){
        return RepositoryHandler.currentRepo;
    }
}