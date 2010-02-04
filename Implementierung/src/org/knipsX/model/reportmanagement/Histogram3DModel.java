package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.Validator;

/**
 * The model auf the Histogram3D which calculate the statistic categories and allocate the axes to the Data.
 * 
 * @author Kevin Zuber
 * 
 */

public class Histogram3DModel extends AbstractDoubleAxesModel {

    private Category[][] categories;
    private double zCategorySize;
    private double xCategorySize;

    /**
     * Creates a new empty Histogram3DModel. You need to set pictureContainer, xAxis and zAxis before you can use it.
     */
    public Histogram3DModel() {
        super();
    }

    /**
     * Creates a new Hisogram3DModel with initial data.
     * 
     * @param pictureContainer
     *            An ArrayList of pictureContainer which should be compared in this report.
     * @param xAxis
     *            the configuration for the xAxis.
     * @param zAxis
     *            the configuration for the zAxis.
     */
    public Histogram3DModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis, final Axis zAxis) {
        super(pictureContainer, xAxis, zAxis);

    }

    private void allocatePicturesToCategories() {
        this.generateCategories();
        Double xValue;
        Double zValue;
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
                /*
                 * each z coordinate
                 */
                for (int j = 0; j < this.categories[i].length; j++) {

                    bar = new Bar(pictureContainer);
                    category = this.categories[i][j];

                    for (final Picture picture : pictureContainer) {

                        boolean pictureValid = true;

                        final ExifParameter xParameter = this.getxAxis().getParameter();
                        final ExifParameter zParameter = this.getzAxis().getParameter();

                        final Object xValueObject = picture.getExifParameter(xParameter);
                        final Object zValueObject = picture.getExifParameter(zParameter);

                        if (xValueObject == null) {
                            this.addMissingExifPictureParameter(new PictureParameter(xParameter, picture));
                            pictureValid = false;
                        }
                        if (zValueObject == null) {
                            this.addMissingExifPictureParameter(new PictureParameter(zParameter, picture));
                            pictureValid = false;
                        }
                        
                        if (!picture.hasMinOneKeywordOf(this.getExifFilterKeywords())) {
                            pictureValid = false;
                        }

                        if (pictureValid) {
                            xValue = Converter.objectToDouble(xValueObject);

                            zValue = Converter.objectToDouble(zValueObject);

                            // if x and z value fits between <= category <
                            if ((xValue < category.getMaxValueX()) && (xValue >= category.getMinValueX())
                                    && (zValue < category.getMaxValueZ()) && (zValue >= category.getMinValueZ())) {
                                /*
                                 * Picture fits in the category, yeah!
                                 */
                                if (allreadyAllocatedPictures.contains(picture)) {
                                    System.out.println("\nError! Picture " + picture.getName()
                                            + " already einsortiert. x: " + xValue + " z: " + zValue);
                                    System.out.println("in Kategorie: " + "max X: " + category.getMaxValueX()
                                            + "  min x: " + category.getMinValueX() + "  max z: "
                                            + category.getMaxValueZ() + "  min z: " + category.getMinValueZ());
                                }
                                allreadyAllocatedPictures.add(picture);
                                bar.addPicture(picture);

                                // or if x or z is last
                            } else if (((i + 1) == this.categories.length) || ((j + 1) == this.categories[i].length)) {

                                // and fits in category.maxValue == and other value fits in other category
                                if (((xValue == category.getMaxValueX()) && (zValue == category.getMaxValueZ()))
                                        || ((xValue == category.getMaxValueX()) && (zValue >= category.getMinValueZ()) && (zValue < category
                                                .getMaxValueZ()))
                                        || ((zValue == category.getMaxValueZ()) && (xValue >= category.getMinValueX()) && (xValue < category
                                                .getMaxValueX()))) {
                                    if (allreadyAllocatedPictures.contains(picture)) {
                                        System.out.println("\nError! Picture " + picture.getName()
                                                + " already einsortiert. x: " + xValue + " z: " + zValue);
                                        System.out.println("in Kategorie: " + "max X: " + category.getMaxValueX()
                                                + "  min x: " + category.getMinValueX() + "  max z: "
                                                + category.getMaxValueZ() + "  min z: " + category.getMinValueZ());
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
    }

    @Override
    protected void calculate() {
        this.allocatePicturesToCategories();
        this.dataIsCalculated(true);

        /* Check values */

        int count = 0;

        for (int i = 0; i < this.categories.length; i++) {
            for (int j = 0; j < this.categories[i].length; j++) {
                if (this.categories[i][j].getMaxValueX() > this.maxX) {
                    System.out.println("found biggest X value: " + this.categories[i][j].getMaxValueX() + " >= "
                            + this.maxX + " (" + i + " " + j + ")");
                }
                if (this.categories[i][j].getMaxValueZ() > this.maxZ) {
                    System.out.println("found biggest Z value: " + this.categories[i][j].getMaxValueZ() + " >= "
                            + this.maxZ + " (" + i + " " + j + ")");
                }
                if (this.categories[i][j].getMinValueX() < this.minX) {
                    System.out.println("found smallest X value: " + this.categories[i][j].getMinValueX() + " <= "
                            + this.minX + " (" + i + " " + j + ")");
                }
                if (this.categories[i][j].getMinValueZ() < this.minZ) {
                    System.out.println("found smallest Z value: " + this.categories[i][j].getMinValueZ() + " <= "
                            + this.minZ + " (" + i + " " + j + ")");
                }

                for (final Bar bar : this.categories[i][j].getBars()) {
                    count += bar.getHeight();
                }

            }

        }

        if (this.maxZ != this.getMaxZ()) {
            System.out.println(" this.maxZ != this.getMaxZ() :" + this.maxZ + " != " + this.getMaxZ());
        }
        if (this.maxY != this.getMaxY()) {
            System.out.println(" this.maxY != this.getMaxY() :" + this.maxY + " != " + this.getMaxY());
        }
        if (this.maxX != this.getMaxX()) {
            System.out.println(" this.maxX != this.getMaxX() :" + this.maxX + " != " + this.getMaxX());
        }

        final ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);
        exifParameters.add(this.getxAxis().getParameter());
        exifParameters.add(this.getzAxis().getParameter());
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
        this.maxZ = -Double.MAX_VALUE;
        this.minX = Double.MAX_VALUE;
        this.minY = Double.MAX_VALUE;
        this.minZ = Double.MAX_VALUE;

        final ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);
        exifParameters.add(this.getxAxis().getParameter());
        exifParameters.add(this.getzAxis().getParameter());

        for (final Picture picture : Validator.getValidPictures(this.getPictureContainer(), exifParameters)) {

            final Object xParameter = picture.getExifParameter(this.getxAxis().getParameter());
            final Object zParameter = picture.getExifParameter(this.getzAxis().getParameter());

            // TWEAK: allow other types than double and int

            final Double xValue = Converter.objectToDouble(xParameter);

            final Double zValue = Converter.objectToDouble(zParameter);

            if (this.maxX < xValue) {
                this.maxX = xValue;
            }
            if (this.maxZ < zValue) {
                this.maxZ = zValue;
            }
            if (this.minX > xValue) {
                this.minX = xValue;
            }
            if (this.minZ > zValue) {
                this.minZ = zValue;
            }

        }

    }

    private void generateCategories() {
        this.calculateExtremeValues();
        int numberOfCategories = 5;

        int pictureCount = 0;

        final ArrayList<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);
        exifParameters.add(this.getxAxis().getParameter());
        exifParameters.add(this.getzAxis().getParameter());

        pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters);

        if (numberOfCategories > pictureCount) {
            if (pictureCount > 0) {
                numberOfCategories = pictureCount;
            } else {
                numberOfCategories = 1;
            }
        }

        this.categories = new Category[numberOfCategories][numberOfCategories];

        double deltaX = Math.abs(this.maxX - this.minX);
        double deltaZ = Math.abs(this.maxZ - this.minZ);

        if (deltaX == 0) {
            deltaX = 1;
            this.maxX = this.minX + deltaX;
        }
        if (deltaZ == 0) {
            deltaZ = 1;
            this.maxZ = this.minZ + deltaZ;
        }
        this.xCategorySize = (deltaX / numberOfCategories);
        this.zCategorySize = (deltaZ / numberOfCategories);

        double minValueX;
        double maxValueX;
        double minValueZ;
        double maxValueZ;

        for (int i = 0; i < this.categories.length; i++) {
            for (int j = 0; j < this.categories[i].length; j++) {

                minValueX = this.minX + i * this.xCategorySize;
                maxValueX = minValueX + this.xCategorySize;
                minValueZ = this.minZ + j * this.zCategorySize;
                maxValueZ = minValueZ + this.zCategorySize;
                this.categories[i][j] = new Category(null, minValueX, maxValueX, minValueZ, maxValueZ);
            }
        }

    }

    /**
     * Caculates the statistic categories and give them back.
     * 
     * @return the statistic categories
     */
    public Category[][] getCategories() {

        this.calculateIfRequired();
        return this.categories;

    }

    @Override
    public boolean isModelValid() {
        this.calculateIfRequired();
        
        Logger log = Logger.getLogger(this.getClass());
        
        if (this.maxX < this.minX) {
            log.info("maxX < minX");
            return false;
        }
        if (this.maxZ < this.minZ) {
            log.info("maxZ < minZ");
            return false;
        }
        if (this.maxY < this.minY) {
            log.info("maxY < minY");
            return false;
        }
        
        if (0 == Validator.getValidPicturesCount(this.getPictureContainer(), 
                new ExifParameter [] { this.xAxis.getParameter(), this.zAxis.getParameter() }
        )){
            log.info("0 == Validator.getValidPicturesCount");
        }
        
        return true;
    }
}
