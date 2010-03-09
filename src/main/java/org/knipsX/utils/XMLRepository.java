package org.knipsX.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.knipsX.Messages;
import org.knipsX.model.picturemanagement.PictureSet;
import org.knipsX.model.projectview.ProjectModel;
import org.knipsX.model.reportmanagement.AbstractReportModel;
import org.knipsX.utils.XML.XMLInput;
import org.knipsX.utils.XML.XMLOutput;

/**
 * Handles the creation of project configuration files.
 */
public class XMLRepository implements Repository {

    private final Logger logger = Logger.getLogger(XMLRepository.class);

    /**
     * {@inheritDoc}
     */
    public List<ProjectModel> getProjects() {

        final List<ProjectModel> projects = new ArrayList<ProjectModel>();

        final File projectDir = new File(RepositoryHandler.PROJECTS_PATH);

        /* if dir not exist, try to create it */
        if (!projectDir.exists()) {
            if (!projectDir.mkdir()) {
                this.logger.error(Messages.getString("XMLRepository.0") + RepositoryHandler.PROJECTS_PATH);
            }
        }

        /* get all projects */
        for (final File item : projectDir.listFiles()) {
            projects.add(this.getProject(Integer.parseInt(item.getName())));
        }
        return projects;
    }

    /**
     * {@inheritDoc}
     */
    public ProjectModel getProject(final int projectId) {

        /* get the contents of the project Folder */
        final File[] xmlFiles = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId).listFiles();

        return new XMLInput(this.getXML(xmlFiles)).getProject(projectId);
    }

    private File getXML(final File[] files) {
        for (final File file : files) {
            if (file.isFile() && file.getName().equals("project.xml")) {
                return file;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public int createProject() {

        /* call the createProject with a dummy ProjectModel */
        return this.createProject(new ProjectModel(0, "", "", new GregorianCalendar(), new LinkedList<PictureSet>(),
                new LinkedList<AbstractReportModel>()));
    }

    /**
     * {@inheritDoc}
     */
    public int createProject(final ProjectModel toCopy) {

        /* create a new unique id */
        int projectId = UUID.randomUUID().hashCode();
        File projectDir = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId);

        /* while the directory exists */
        while (projectDir.exists()) {
            projectId = UUID.randomUUID().hashCode();
            projectDir = new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId);
        }

        /* save the project and return the unique id */
        if (!projectDir.mkdir()) {
            this.logger.error(Messages.getString("XMLRepository.4") + RepositoryHandler.PROJECTS_PATH);
        } else {
            this.saveProject(new ProjectModel(toCopy, projectId));
        }
        return projectId;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteProject(final int projectId) {
        this.treeDelete(new File(RepositoryHandler.PROJECTS_PATH + File.separator + projectId));
    }

    /* delete recursiv */
    private void treeDelete(final File file) {

        /* if we have a directory, delete first all files */
        if (file.isDirectory()) {
            final File[] children = file.listFiles();
            for (final File element : children) {
                this.treeDelete(element);
            }
            if (!file.delete()) {
                this.logger.error(Messages.getString("XMLRepository.6") + file.getAbsolutePath());
            }
        } else {
            if (!file.delete()) {
                this.logger.error(Messages.getString("XMLRepository.7") + file.getAbsolutePath());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void saveProject(final ProjectModel toSave) {
        final XMLOutput xmlFile = new XMLOutput(toSave);
        final File projectFile = new File(RepositoryHandler.PROJECTS_PATH + File.separator + toSave.getId()
                + File.separator + "project.xml");

        try {
            final FileWriter writer = new FileWriter(projectFile);
            final XMLOutputter outputter = new XMLOutputter();
            try {
                outputter.setFormat(Format.getPrettyFormat());
                outputter.output(xmlFile.getDocument(), writer);
            } catch (final IOException e) {
                this.logger.error("[XMLRepository::saveProject()] - " + e.getMessage());
            }
        } catch (final IOException e) {
            this.logger.error("[XMLRepository::saveProject()] - " + e.getMessage());
        }
    }
}