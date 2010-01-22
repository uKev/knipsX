/**
 * This package is the root of all utility packages.
 */
package org.knipsX.utils;

/* import things from the java sdk */
import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/* import things from our program */
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.model.reportmanagement.BoxplotModel;

/**
 * Has functions that can handle with the filesystem.
 */
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