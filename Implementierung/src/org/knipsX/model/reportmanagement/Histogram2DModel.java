package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Validator;

/**
 * The model auf the Histogram2D which calculate the statistic categories and allocate the axes to the Data.
 * 
 * @author Kevin Zuber
 * 
 */

public class Histogram2DModel extends AbstractSingleAxisModel {

    private Category[] categories;
    private double xCategorySize;

    /**
     * Creates a new empty Histogram2DModel. You need to set pictureContainer and xAxis before you can use it.
     */
    public Histogram2DModel() {
        super();
    }

    /**
     * Creates a new Hisogram2DModel with initial data.
     * 
     * @param pictureContainer
     *            An ArrayList of pictureContainer which should be compared in this report.
     * @param xAxis
     *            the configuration for the xAxis.
     */
    public Histogram2DModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis) {
        super(pictureContainer, xAxis);
    }

    private void allocatePicturesToCategories() {
        this.generateCategories();
        Double xValue;
        Category category;
        Bar bar;
        this.minY = 0;

        final ArrayList<Picture> allreadyAllocatedPictures = new ArrayList<Picture>();

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {
            /*
             * create a bar for each pictureContainer in each Category
             */
            /*
             * each x coordinate
             */
            for (int i = 0; i < this.categories.length; i++) {

                bar = new Bar(pictureContainer);
                category = this.categories[i];

                for (final Picture picture : pictureContainer) {

                    boolean pictureValid = true;

                    final ExifParameter xParameter = this.getxAxis().getParameter();

                    final Object xValueObject = picture.getExifParameter(xParameter);

                    if (xValueObject == null) {
                        this.addMissingExifPictureParameter(new PictureParameter(xParameter, picture));
                        pictureValid = false;
                    }

                    if (pictureValid) {
                        xValue = Converter.objectToDouble(xValueObject);

                        // if x and z value fits between <= category <
                        if ((xValue < category.getMaxValueX()) && (xValue >= category.getMinValueX())) {
                            /*
                             * Picture fits in the category, yeah!
                             */
                            if (allreadyAllocatedPictures.contains(picture)) {
                                System.out.println("\nError! Picture " + picture.getName()
                                        + " already einsortiert. x: " + xValue);
                                System.out.println("in Kategorie: " + "max X: " + category.getMaxValueX() + "  min x: "
                                        + category.getMinValueX() + "  max z: " + category.getMaxValueZ() + "  min z: "
                                        + category.getMinValueZ());
                            }
                            allreadyAllocatedPictures.add(picture);
                            bar.addPicture(picture);

                            // or if x or z is last
                        } else if (((i + 1) == this.categories.length)) {

                            // and fits in category.maxValue == and other value fits in other category
                            if (xValue == category.getMaxValueX()) {
                                if (allreadyAllocatedPictures.contains(picture)) {
                                    System.out.println("\nError! Picture " + picture.getName()
                                            + " already einsortiert. x: " + xValue);
                                    System.out.println("in Kategorie: " + "max X: " + category.getMaxValueX()
                                            + "  min x: " + category.getMinValueX());
                                }
                                allreadyAllocatedPictures.add(picture);

                                bar.addPicture(picture);
                            }
                        }
                    }
                }

                if (this.maxY < bar.getHeight()) {
                    this.maxY = bar.getHeight();
                }

                category.addBar(bar);
            }
        }
    }

    @Override
    protected void calculate() {
        this.allocatePicturesToCategories();
        this.dataIsCalculated(true);

        /* Check values */

        int count = 0;

        for (int i = 0; i < this.categories.length; i++) {
            if (this.categories[i].getMaxValueX() > this.maxX) {
                System.out.println("found biggest X value: " + this.categories[i].getMaxValueX() + " >= " + this.maxX
                        + " (" + i + ")");
            }

            if (this.categories[i].getMinValueX() < this.minX) {
                System.out.println("found smallest X value: " + this.categories[i].getMinValueX() + " <= " + this.minX
                        + " (" + i + ")");
            }

            for (final Bar bar : this.categories[i].getBars()) {
                count += bar.getHeight();
            }

        }

        if (this.maxY != this.getMaxY()) {
            System.out.println(" this.maxY != this.getMaxY() :" + this.maxY + " != " + this.getMaxY());
        }
        if (this.maxX != this.getMaxX()) {
            System.out.println(" this.maxX != this.getMaxX() :" + this.maxX + " != " + this.getMaxX());
        }

        final ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);
        exifParameters.add(this.getxAxis().getParameter());
        final int pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters);

        if (pictureCount != count) {

            if (this.getPicturesWithMissingExifParameter().isEmpty()) {
                System.out.println("pictureCount != count    " + pictureCount + " != " + count
                        + " , das riecht nach nem bug, da wurde was vergessen!");
            } else {
                System.out
                        .println("pictureCount != count    "
                                + pictureCount
                                + " != "
                                + count
                                + " , aber vorsicht getPicturesWithMissingExifParameter enthält elemente, darin kann ein Bild auch mehrfach vertreten sein: "
                                + this.getPicturesWithMissingExifParameter().size());
            }
        }

    }

    private void calculateExtremeValues() {
        this.maxX = -Double.MAX_VALUE;
        this.maxY = -Double.MAX_VALUE;

        this.minX = Double.MAX_VALUE;
        this.minY = Double.MAX_VALUE;

        final ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>(1);
        exifParameters.add(this.getxAxis().getParameter());

        for (final Picture picture : Validator.getValidPictures(this.getPictureContainer(), exifParameters)) {

            final Object xParameter = picture.getExifParameter(this.getxAxis().getParameter());

            // TWEAK: allow other types than double and int

            final Double xValue = Converter.objectToDouble(xParameter);

            if (this.maxX < xValue) {
                this.maxX = xValue;
            }
            if (this.minX > xValue) {
                this.minX = xValue;
            }

        }

    }

    private void generateCategories() {
        this.calculateExtremeValues();
        int numberOfCategories = 5;

        int pictureCount = 0;

        final ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);
        exifParameters.add(this.getxAxis().getParameter());

        pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters);

        if (numberOfCategories > pictureCount) {
            if (pictureCount > 0) {
                numberOfCategories = pictureCount;
            } else {
                numberOfCategories = 1;
            }
        }

        this.categories = new Category[numberOfCategories];

        double deltaX = Math.abs(this.maxX - this.minX);

        if (deltaX == 0) {
            deltaX = 1;
            this.maxX = this.minX + deltaX;
        }

        this.xCategorySize = (deltaX / numberOfCategories);

        double minValueX;
        double maxValueX;

        for (int i = 0; i < this.categories.length; i++) {

            minValueX = this.minX + i * this.xCategorySize;
            maxValueX = minValueX + this.xCategorySize;

            this.categories[i] = new Category(null, minValueX, maxValueX);
        }

    }

    /**
     * Caculates the statistic categories and give them back.
     * 
     * @return the statistic categories
     */
    public Category[] getCategories() {
        this.calculateIfRequired();
        return this.categories;

    }

    @Override
    public boolean isModelValid() {
        this.calculateIfRequired();
        Logger logger = Logger.getLogger(this.getClass());
        
        if (this.maxX < this.minX) {
            logger.info("maxX < minX");
            return false;
        }
        
        if (Validator.getValidPicturesCount(this.getPictureContainer(), this.getxAxis().getParameter()) == 0) {
            logger.info("getValidPicturesCount == 0");
            return false;
        }
        
        return true;
    }

}
