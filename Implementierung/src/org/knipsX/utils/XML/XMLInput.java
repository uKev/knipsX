package org.knipsX.utils.XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JOptionPane;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.knipsX.model.picturemanagement.Directory;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureNotFoundException;
import org.knipsX.model.picturemanagement.PictureSet;

/**
 * This class reads the "data.xml" file and returns the information.
 * 
 * @author Bouche, Kai (<a href="mailto:kai.bouche@kai-bouche.de">mail</a>)
 * @version 2.0 from 31.05.2005
 */
public class XMLInput {

    private File xml;

    private Element project;

    /**
     * Constructs a MyXmlHandler.
     */
    public XMLInput(final File xml) {
        this.xml = xml;
        this.initialize();
    }

    /**
     * Reads the XML file and extract the data from it to the arrays in this class.
     */
    private void initialize() {

        final SAXBuilder saxBuilder = new SAXBuilder();

        Document docXml;
        try {
            docXml = saxBuilder.build(xml);
            this.project = docXml.getRootElement();
        } catch (JDOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public int getId() {
        return Integer.parseInt(this.project.getChildText("id"));
    }

    public String getName() {
        return this.project.getChildText("name");
    }

    public String getDescription() {
        return this.project.getChildText("description");
    }

    public GregorianCalendar getCreationDate() {
        try {
            return parseTimestamp(this.project.getChildText("creationDate"));
        } catch (ParseException e) {
            e.printStackTrace();
            return new GregorianCalendar();
        }
    }

    private static GregorianCalendar parseTimestamp(String timestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date d = sdf.parse(timestamp);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal;
    }

    public List<PictureSet> getPictureSets() {

        /*
         * store all picture sets in a hashmap like:
         * 1 -> picset1
         * 1234 -> picset2
         */
        Map<Integer, PictureSet> pictureSets = new HashMap<Integer, PictureSet>();
        for (Object item : this.project.getChild("pictureSets").getChildren("pictureSet")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int id = Integer.parseInt(set.getChildText("id"));
                String name = set.getChildText("name");

                pictureSets.put(id, new PictureSet(name, id));
            }
        }

        /*
         * store all directories in a hashmap like:
         * 1 -> dir1
         * 1234 -> dir2
         */
        Map<Integer, Directory> directories = new HashMap<Integer, Directory>();
        for (Object item : this.project.getChild("directories").getChildren("directory")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int id = Integer.parseInt(set.getChildText("id"));
                String path = set.getChildText("path");

                directories.put(id, new Directory(path));
            }
        }

        /*
         * store all pictures in a hashmap like:
         * 1 -> pic1
         * 1234 -> pic2
         */
        Map<Integer, Picture> pictures = new HashMap<Integer, Picture>();
        for (Object item : this.project.getChild("pictures").getChildren("picture")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int id = Integer.parseInt(set.getChildText("id"));
                String path = set.getChildText("path");

                try {
                    pictures.put(id, new Picture(path, true));
                } catch (PictureNotFoundException e) {
                    System.err.println("[XMLInput::getPictureSets()] - Picture not found -> " + path);
                }
            }
        }

        /* walk through the picture sets an connect them to their children */
        for (Object item : this.project.getChild("pictureSets").getChildren("pictureSet")) {
            if (item instanceof Element) {
                Element set = (Element) item;

                int rootId = Integer.parseInt(set.getChildText("id"));

                for (Object child : set.getChild("children").getChildren()) {
                    if (child instanceof Element) {
                        Element container = (Element) child;

                        if (container.getName() == "pictureSet") {

                            /* get a picture set from the hashmap and connect another to it */
                            pictureSets.get(rootId).add(pictureSets.get(Integer.parseInt(container.getText())));
                        } else if (container.getName() == "directory") {

                            /* get a picture set from the hashmap and connect a directory to it */
                            pictureSets.get(rootId).add(directories.get(Integer.parseInt(container.getText())));
                        } else if (container.getName() == "picture") {

                            /* get a picture set from the hashmap and connect a directory to it */
                            pictureSets.get(rootId).add(pictures.get(Integer.parseInt(container.getText())));
                        }
                    }
                }
            }
        }
        List<PictureSet> returnList = new ArrayList<PictureSet>();
        for(PictureSet set : pictureSets.values()) {
            returnList.add(set);
        }
        return returnList;
    }

}
