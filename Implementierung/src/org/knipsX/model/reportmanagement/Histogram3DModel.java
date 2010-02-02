package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;

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

    public Histogram3DModel() {
        super();
    }

    public Histogram3DModel(final ArrayList<PictureContainer> pictureContainer, final Axis xAxis, final Axis zAxis) {
        super(pictureContainer, xAxis, zAxis);

    }

    private void allocatePicturesToCategories() {
        this.generateCategories();
        Double xValue;
        Double zValue;
        Category category;
        int barCount;
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

                    barCount = 0;
                    category = this.categories[i][j];

                    for (final Picture picture : pictureContainer) {

                        xValue = ((Float) picture.getExifParameter(this.getxAxis().getParameter())).doubleValue();
                        zValue = ((Float) picture.getExifParameter(this.getzAxis().getParameter())).doubleValue();

                        // if x and z value fits between <= category <
                        if ((xValue < category.getMaxValueX()) && (xValue >= category.getMinValueX())
                                && (zValue < category.getMaxValueZ()) && (zValue >= category.getMinValueZ())) {
                            /*
                             * Picture fits in the category, yeah!
                             */
                            if (allreadyAllocatedPictures.contains(picture)) {
                                System.out.println("\nError! Picture " + picture.getName()
                                        + " already einsortiert. x: " + xValue + " z: " + zValue);
                                System.out.println("in Kategorie: " + "max X: " + category.getMaxValueX() + "  min x: "
                                        + category.getMinValueX() + "  max z: " + category.getMaxValueZ() + "  min z: "
                                        + category.getMinValueZ());
                            }
                            allreadyAllocatedPictures.add(picture);
                            barCount++;

                            // or if x or z is last
                        } else if (((i + 1) == this.categories.length) || ((j + i) == this.categories[i].length)) {

                            // and fits in category.maxValue == and other value fits in other category
                            if (
                                    ( (xValue == category.getMaxValueX()) && (zValue == category.getMaxValueZ()) )
                                    || ( xValue == category.getMaxValueX() && zValue > category.getMinValueZ() && zValue < category.getMaxValueZ() )
                                    || ( zValue == category.getMaxValueZ() && xValue > category.getMinValueX() && xValue < category.getMaxValueX() )
                                    ) {
                                if (allreadyAllocatedPictures.contains(picture)) {
                                    System.out.println("\nError! Picture " + picture.getName()
                                            + " already einsortiert. x: " + xValue + " z: " + zValue);
                                    System.out.println("in Kategorie: " + "max X: " + category.getMaxValueX()
                                            + "  min x: " + category.getMinValueX() + "  max z: "
                                            + category.getMaxValueZ() + "  min z: " + category.getMinValueZ());
                                }
                                allreadyAllocatedPictures.add(picture);

                                barCount++;
                            }
                        }
                    }

                    category.addBar(new Bar(pictureContainer, barCount));

                    if (this.maxY < barCount) {
                        this.maxY = barCount;
                    }
                }
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

        for (final PictureContainer pictureContainer : this.getPictureContainer()) {
            for (final Picture picture : pictureContainer) {

                // TWEAK: allow other types than double
                final Double xValue = ((Float) picture.getExifParameter(this.getxAxis().getParameter())).doubleValue();
                final Double zValue = ((Float) picture.getExifParameter(this.getzAxis().getParameter())).doubleValue();

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

    }

    private void calculateIfNeeded() {
        if (!this.isDataCalculated()) {

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

            int pictureCount = 0;
            for (final PictureContainer pictureContainer : this.getPictureContainer()) {
                for (final Picture picture : pictureContainer) {
                    picture.getClass();
                    pictureCount++;
                }
            }
            if (pictureCount == count) {
                System.out.println("Alles in Butter, pictureCount == count !");
            } else if (this.getPicturesWithMissingExifParameter().size() == 0) {
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

    private void generateCategories() {
        this.calculateExtremeValues();
        final int numberOfCategories = 5;

        this.categories = new Category[numberOfCategories][numberOfCategories];

        this.xCategorySize = (Math.abs(this.maxX - this.minX) / numberOfCategories);
        this.zCategorySize = (Math.abs(this.maxZ - this.minZ) / numberOfCategories);
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

    /*
     * Berechnet die Statistik-Klassen und liefert die entsprechenden Objekte zurück
     */
    public Category[][] getCategories() {

        this.calculateIfNeeded();
        return this.categories;

    }
}
