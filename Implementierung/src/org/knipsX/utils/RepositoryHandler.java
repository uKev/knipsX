package org.knipsX.utils;

import java.io.File;

public final class RepositoryHandler {

    /**
     * Determines the path to the directory where all projects of the program are stored.
     */
    public static final String PROJECTS_PATH = System.getProperty("user.home") + File.separator + ".knipsX";
    
<<<<<<< HEAD:Implementierung/src/org/knipsX/utils/RepositoryHandler.java
    private static Repository currentRepo = new XMLRepository();
    
    //private static Repository currentRepo = new XMLRepository();
=======
    /*
     * Set which repository should be used.
     */
    private static Repository currentRepo = new XMLRepositoryBackup();
    // private static Repository currentRepo = new XMLRepository();
    // private static Repository currentRepo = new DummyRepository();
>>>>>>> 0febe10767b0dc2d16e0bfb4726fa08b509ed4c7:Implementierung/src/org/knipsX/utils/RepositoryHandler.java

    public static Repository getRepository(){
        return RepositoryHandler.currentRepo;
    }
}
