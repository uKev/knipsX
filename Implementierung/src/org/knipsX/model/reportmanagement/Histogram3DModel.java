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

    public Histogram3DModel(ArrayList<PictureContainer> pictureContainer, Axis xAxis, Axis zAxis) {
        super(pictureContainer, xAxis, zAxis);

    }

    public Histogram3DModel() {
        super();
    }

    /*
     * Berechnet die Statistik-Klassen und liefert die entsprechenden Objekte zurück
     */
    public Category[][] getCategories() {

        calculateIfNeeded();
        return this.categories;

    }

    private void calculateIfNeeded() {
        if (!this.isDataCalculated()) {

            allocatePicturesToCategories();
            this.dataIsCalculated(true);
            
            /* Check values */
            for (int i=0; i <this.categories.length; i++){
                for (int j=0; j < this.categories[i].length; j++)
                {
                    if (this.categories[i][j].getMaxValueX() >= this.maxX) {
                        System.out.println("found biggest X value: " + this.categories[i][j].getMaxValueX() + " >= " + this.maxX + " ("+i+" "+j+")");
                    }
                    if (this.categories[i][j].getMaxValueZ() >= this.maxZ) {
                        System.out.println("found biggest Z value: " + this.categories[i][j].getMaxValueZ() + " >= " + this.maxZ + " ("+i+" "+j+")");
                    }
                    if (this.categories[i][j].getMinValueX() <= this.minX) {
                        System.out.println("found smallest X value: " + this.categories[i][j].getMinValueX() + " <= " + this.minX + " ("+i+" "+j+")");
                    }
                    if (this.categories[i][j].getMinValueZ() <= this.minZ) {
                        System.out.println("found smallest Z value: " + this.categories[i][j].getMinValueZ() + " <= " + this.minZ + " ("+i+" "+j+")");
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

        for (PictureContainer pictureContainer : this.getPictureContainer()) {
            for (Picture picture : pictureContainer) {

                // TWEAK: allow other types than double
                Double xValue = ((Float) picture.getExifParameter(this.getxAxis().getParameter())).doubleValue();
                Double zValue = ((Float) picture.getExifParameter(this.getzAxis().getParameter())).doubleValue();

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

    private void allocatePicturesToCategories() {
        generateCategories();
        Double xValue;
        Double zValue;
        Category category;
        int barCount;
        this.minY = 0;
        
        for (PictureContainer pictureContainer : this.getPictureContainer()) {
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
                    category = categories[i][j];

                    for (Picture picture : pictureContainer) {
                        
                        xValue = ((Float) picture.getExifParameter(this.getxAxis().getParameter())).doubleValue();
                        zValue = ((Float) picture.getExifParameter(this.getzAxis().getParameter())).doubleValue();
                     
                        if (xValue < category.getMaxValueX() && xValue >= category.getMinValueX()
                                && zValue < category.getMaxValueZ() && zValue >= category.getMinValueZ()) {
                            /* 
                             * Picture fits in the category, yeah!
                             */
                            // FIXME: correct that biggest value is not <. it should be <=!
                            barCount++;
                        }
                        
                    }
                    category.addBar(new Bar(pictureContainer, barCount));
                    
                    if (this.maxY < barCount){
                        this.maxY = barCount;
                    }
                }
            }
        }
    }
    
    private void generateCategories() {
        calculateExtremeValues();
        int numberOfCategories = 5;

        this.categories = new Category[numberOfCategories][numberOfCategories];

        this.xCategorySize = (Math.abs(this.maxX - this.minX) / numberOfCategories);
        this.zCategorySize = (Math.abs(this.maxX - this.minX) / numberOfCategories);
        double minValueX;
        double maxValueX;
        double minValueZ;
        double maxValueZ;

        for (int i = 0; i < this.categories.length; i++) {
            for (int j = 0; j < this.categories[i].length; j++) {
                minValueX = this.minX + i * xCategorySize;
                maxValueX = minValueX + xCategorySize;
                minValueZ = this.minZ + i * zCategorySize;
                maxValueZ = minValueZ + zCategorySize;
                this.categories[i][j] = new Category(null, minValueX, maxValueX, minValueZ, maxValueZ);
            }
        }

    }
}
