package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.model.picturemanagement.PictureInterface;
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

    private final Logger logger = Logger.getLogger(this.getClass());

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

        final List<PictureInterface> allreadyAllocatedPictures = new ArrayList<PictureInterface>();

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {

            /* create a bar for each pictureContainer in each category */

            /* each x coordinate */
            for (int i = 0; i < this.categories.length; ++i) {

                /* each z coordinate */
                for (int j = 0; j < this.categories[i].length; ++j) {
                    bar = new Bar(pictureContainer);
                    category = this.categories[i][j];

                    for (final PictureInterface picture : pictureContainer) {
                        boolean pictureValid = true;

                        final ExifParameter xParameter = this.getXAxis().getParameter();
                        final ExifParameter zParameter = this.getZAxis().getParameter();

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

                            if ((xValue < category.getMaxValueX()) && (xValue >= category.getMinValueX())
                                    && (zValue < category.getMaxValueZ()) && (zValue >= category.getMinValueZ())) {

                                /* if x and z value fits between <= category */

                                /* Picture fits in the category, yeah! */
                                if (allreadyAllocatedPictures.contains(picture)) {
                                    this.logger.error("\nError! Picture " + picture.getName()
                                            + " already einsortiert. x: " + xValue + " z: " + zValue);
                                    this.logger.error("in Kategorie: " + "max X: " + category.getMaxValueX()
                                            + "  min x: " + category.getMinValueX() + "  max z: "
                                            + category.getMaxValueZ() + "  min z: " + category.getMinValueZ());
                                }
                                allreadyAllocatedPictures.add(picture);
                                bar.addPicture(picture);
                            } else if (((i + 1) == this.categories.length) || ((j + 1) == this.categories[i].length)) {

                                /* or if x or z is last */

                                /* and fits in category.maxValue == and other value fits in other category */
                                if (((xValue == category.getMaxValueX()) && (zValue == category.getMaxValueZ()))
                                        || ((xValue == category.getMaxValueX()) && (zValue >= category.getMinValueZ()) && (zValue < category
                                                .getMaxValueZ()))
                                        || ((zValue == category.getMaxValueZ()) && (xValue >= category.getMinValueX()) && (xValue < category
                                                .getMaxValueX()))) {

                                    if (allreadyAllocatedPictures.contains(picture)) {
                                        this.logger.error("\nError! Picture " + picture.getName()
                                                + " already einsortiert. x: " + xValue + " z: " + zValue);
                                        this.logger.error("in Kategorie: " + "max X: " + category.getMaxValueX()
                                                + "  min x: " + category.getMinValueX() + "  max z: "
                                                + category.getMaxValueZ() + "  min z: " + category.getMinValueZ());
                                    }
                                    allreadyAllocatedPictures.add(picture);
                                    bar.addPicture(picture);
                                }
                            }
                        }
                    }
                    this.maxY = Math.max(this.maxY, bar.getHeight());
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

        for (int i = 0; i < this.categories.length; ++i) {

            for (int j = 0; j < this.categories[i].length; ++j) {

                if (this.categories[i][j].getMaxValueX() > this.maxX) {
                    this.logger.debug("found biggest X value: " + this.categories[i][j].getMaxValueX() + " >= "
                            + this.maxX + " (" + i + " " + j + ")");
                }

                if (this.categories[i][j].getMaxValueZ() > this.maxZ) {
                    this.logger.debug("found biggest Z value: " + this.categories[i][j].getMaxValueZ() + " >= "
                            + this.maxZ + " (" + i + " " + j + ")");
                }

                if (this.categories[i][j].getMinValueX() < this.minX) {
                    this.logger.debug("found smallest X value: " + this.categories[i][j].getMinValueX() + " <= "
                            + this.minX + " (" + i + " " + j + ")");
                }

                if (this.categories[i][j].getMinValueZ() < this.minZ) {
                    this.logger.debug("found smallest Z value: " + this.categories[i][j].getMinValueZ() + " <= "
                            + this.minZ + " (" + i + " " + j + ")");
                }

                for (final Bar bar : this.categories[i][j].getBars()) {
                    count += bar.getHeight();
                }
            }
        }

        if (this.maxZ != this.getMaxZ()) {
            this.logger.debug(" this.maxZ != this.getMaxZ() :" + this.maxZ + " != " + this.getMaxZ());
        }

        if (this.maxY != this.getMaxY()) {
            this.logger.debug(" this.maxY != this.getMaxY() :" + this.maxY + " != " + this.getMaxY());
        }

        if (this.maxX != this.getMaxX()) {
            this.logger.debug(" this.maxX != this.getMaxX() :" + this.maxX + " != " + this.getMaxX());
        }

        final List<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);

        exifParameters.add(this.getXAxis().getParameter());
        exifParameters.add(this.getZAxis().getParameter());

        final int pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters);

        if (pictureCount != count) {

            if (this.getPicturesWithMissingExifParameter().isEmpty()) {
                this.logger.debug("pictureCount != count    " + pictureCount + " != " + count
                        + " , das riecht nach nem bug, da wurde was vergessen!");
            } else {
                this.logger
                        .debug("pictureCount != count    "
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

        final List<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);

        assert this.getZAxis() != null;
        assert this.getXAxis() != null;
        assert this.getZAxis().getParameter() != null;
        assert this.getXAxis().getParameter() != null;
        
        exifParameters.add(this.getXAxis().getParameter());
        exifParameters.add(this.getZAxis().getParameter());

        for (final PictureInterface picture : Validator.getValidPictures(this.getPictureContainer(), exifParameters)) {

            final Object xParameter = picture.getExifParameter(this.getXAxis().getParameter());
            final Object zParameter = picture.getExifParameter(this.getZAxis().getParameter());

            /* TWEAK allow other types than double and int */
            final Double xValue = Converter.objectToDouble(xParameter);
            final Double zValue = Converter.objectToDouble(zParameter);

            this.maxX = Math.max(this.maxX, xValue);
            this.maxZ = Math.max(this.maxZ, zValue);
            this.minX = Math.max(this.minX, xValue);
            this.minZ = Math.max(this.minZ, zValue);
        }
    }

    private void generateCategories() {
        this.calculateExtremeValues();

        int numberOfCategories = 5;
        int pictureCount = 0;

        final List<ExifParameter> exifParameters = new ArrayList<ExifParameter>(2);

        exifParameters.add(this.getXAxis().getParameter());
        exifParameters.add(this.getZAxis().getParameter());

        pictureCount = Validator.getValidPicturesCount(this.getPictureContainer(), exifParameters);

        if (numberOfCategories > pictureCount) {
            if (pictureCount > 0) {
                numberOfCategories = pictureCount;
            } else {
                numberOfCategories = 1;
            }
        }
        this.categories = new Category[numberOfCategories][numberOfCategories];

        /* Calculate the distance from the minimum to the maximum.
         * If the distance is zero, the minimum is equal to the maximum value
         * and we have only one category. 
         * Then we need to set the distance to 1 to prevent the Armageddon.
         * Because the different only must not be exactly zero, it's OK to compare doubles without epsilon.
         * If they are slightly different - the world is rescued ;).
         *  */
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

        for (int i = 0; i < this.categories.length; ++i) {

            for (int j = 0; j < this.categories[i].length; ++j) {

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
        /*
         * Check things needed for calculation.
         */
        if (this.getXAxis() == null) {
            logger.info("xAxis is null");
            return false;
        }
        if (this.getZAxis() == null) {
            logger.info("zAxis is null");
            return false;
        }
        if (this.getXAxis().getParameter() == null) {
            logger.info("xAxis parameter is null");
            return false;
        }
        if (this.getZAxis().getParameter() == null) {
            logger.info("zAxis parameter is null");
            return false;
        }
        
        this.calculateIfRequired();
        
        /*
         * Check if calculation was correct.
         */
        
        if (this.maxX < this.minX) {
            this.logger.info("maxX < minX");
            return false;
        }

        if (this.maxZ < this.minZ) {
            this.logger.info("maxZ < minZ");
            return false;
        }

        if (this.maxY < this.minY) {
            this.logger.info("maxY < minY");
            return false;
        }

        if (0 == Validator.getValidPicturesCount(this.getPictureContainer(), new ExifParameter[] {
                this.xAxis.getParameter(), this.zAxis.getParameter() })) {
            this.logger.info("0 == Validator.getValidPicturesCount");
        }
        return true;
    }
}