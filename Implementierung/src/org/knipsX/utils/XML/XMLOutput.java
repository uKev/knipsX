package org.knipsX.utils.XML;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.jdom.Document;
import org.jdom.Element;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;

/**
 * This class reads a project file and returns the information.
 * 
 */
public class XMLOutput {

    private Document xmlDocument;
    
    private ProjectModel project;

    public XMLOutput(final ProjectModel project) {
        this.project = project;
        
        Element root = new Element("project");
        
        root.addContent(this.resolveId());
        root.addContent(this.resolveName());
        root.addContent(this.resolveDescription());
        root.addContent(this.resolveCreationDate());
        root.addContent(this.resolvePictureSets());
        root.addContent(this.resolveDirectories());
        root.addContent(this.resolvePictures());
        root.addContent(new Element("reports"));
        
        this.xmlDocument = new Document(root);
    }

    public Document getDocument() {
        return this.xmlDocument;
    }

    /* #########################################################
     * META DATA
     * #########################################################
     */
    
    private Element resolveId() {
        return new Element("id").setText("" + this.project.getId());
    }

    private Element resolveName() {
        return new Element("name").setText(this.project.getName());
    }
    
    private Element resolveDescription() {
        return new Element("description").setText(this.project.getDescription());
    }
    
    private Element resolveCreationDate() {
        return new Element("creationDate").setText(XMLOutput.parseDate(this.project.getCreationDate()));
    }
    
    private static String parseDate(GregorianCalendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
    
    /* #########################################################
     * PICTURE SETS
     * #########################################################
     */

    private Element resolvePictureSets() {
        Element pictureSets = new Element("pictureSets");
        
        for(PictureSet set : this.project.getPictureSets()) {
            pictureSets.addContent(this.resolvePictureSet(set));
        }
        return pictureSets;
    }
    
    private Element resolvePictureSet(PictureSet set) {
        Element pictureSet = new Element("pictureSet");
        
        pictureSet.addContent(new Element("id").setText("" + set.hashCode()));
        pictureSet.addContent(new Element("name").setText(set.getName()));
        pictureSet.addContent(this.getChildrenFromPictureSet(set));
        return pictureSet;
    }
    
    private Element getChildrenFromPictureSet(PictureSet set) {
        Element children = new Element("children");
        
        for(PictureContainer child : set.getItems()) {
            if(child instanceof PictureSet) {
                children.addContent(new Element("pictureSet").setText("" + child.hashCode()));
            } else if(child instanceof Directory) {
                children.addContent(new Element("directory").setText("" + child.hashCode()));
            } else if(child instanceof Picture) {
                children.addContent(new Element("picture").setText("" + child.hashCode()));
            } 
        }        
        return children;
    }
    
    /* #########################################################
     * DIRECTORIES
     * #########################################################
     */
    
    private Element resolveDirectories() {
        Element directories = new Element("directories");
        
        for(PictureSet set : this.project.getPictureSets()) {
            for(Directory dir : this.project.getDirectoriesOfAPictureSet(set)) {
                directories.addContent(this.resolveDirectory(dir));
            }
        }        
        return directories;
    }
    
    private Element resolveDirectory(Directory dir) {
        Element directory = new Element("directory");
        
        directory.addContent(new Element("id").setText("" + dir.hashCode()));
        directory.addContent(new Element("path").setText(dir.getPath()));
        return directory;
    }
    
    /* #########################################################
     * PICTURES
     * #########################################################
     */
    
    private Element resolvePictures() {
        Element pictures = new Element("pictures");
        
        for(PictureSet set : this.project.getPictureSets()) {
            for(Picture pic : this.project.getPicturesOfAPictureSet(set)) {
                pictures.addContent(this.resolvePicture(pic));
            }
        }        
        return pictures;
    }
    
    private Element resolvePicture(Picture pic) {
        Element picture = new Element("picture");
        
        picture.addContent(new Element("id").setText("" + pic.hashCode()));
        picture.addContent(new Element("path").setText(pic.getPath()));
        return picture;
    }    
}
