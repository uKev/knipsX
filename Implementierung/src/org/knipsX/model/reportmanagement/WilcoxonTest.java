package org.knipsX.model.reportmanagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.knipsX.model.picturemanagement.Picture;
import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.Converter;
import org.knipsX.utils.ExifParameter;

public class WilcoxonTest {

    private boolean isActive = false;
    private boolean isValid = false;
    private WilcoxonTestType wilcoxenType;
    private ArrayList<PictureContainer> pictureContainer;
    private ExifParameter parameter;
    private double significance = 0;
    private double result;
    private List<WilcoxonSample> wilcoxonSamplesList = new LinkedList<WilcoxonSample>();

    public WilcoxonTest(ArrayList<PictureContainer> pictureContainer, ExifParameter parameter) {
        this.pictureContainer = pictureContainer;
        this.parameter = parameter;
    }

    public WilcoxonTest(ArrayList<PictureContainer> pictureContainer, ExifParameter parameter, boolean isActive,
            float significance, WilcoxonTestType wilcoxenType) {
        this.pictureContainer = pictureContainer;
        this.parameter = parameter;
        this.isActive = isActive;
        this.significance = significance;
        this.wilcoxenType = wilcoxenType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActiveStatus(boolean activeStatus) {
        isActive = activeStatus;
    }

    public boolean isValid() {
        return isValid;
    }
    
    public WilcoxonTestType getWilcoxonTestType(){
        return this.wilcoxenType;    
    }
    
    public void setWilcoxonTestType(WilcoxonTestType testType){
        this.wilcoxenType = testType;
    }
    
    public double getResult() {
        return this.result;
    }
    
    public double getSignificance() {
        return this.significance;
    }
    
    public void setSignificance(double value) {
        this.significance = value;
    }

    public void calculate() {
        if (wilcoxenType != null) {
            double rangeSum = 0;
            int numberOfElementsInFirstSet = initTest();
            assert wilcoxonSamplesList.size() >= 2;
            int numberOfElementsInSecondSet = wilcoxonSamplesList.size() - numberOfElementsInFirstSet;
            int partVectors = fakultaet(numberOfElementsInFirstSet + numberOfElementsInSecondSet)
                    / ((fakultaet(numberOfElementsInFirstSet)) * (fakultaet(numberOfElementsInSecondSet)));
            Collections.sort(wilcoxonSamplesList);
            if (numberOfElementsInFirstSet < numberOfElementsInSecondSet) {
                for (int n = 0; n < wilcoxonSamplesList.size(); n++) {
                    rankSample(n);
                    if (wilcoxonSamplesList.get(n).getSource() == pictureContainer.get(0)) {
                        wilcoxonSamplesList.get(n).setIsLessThan(true);
                        rangeSum = rangeSum + wilcoxonSamplesList.get(n).getRank();
                    }
                }
            } else {
                for (int i = 0; i < wilcoxonSamplesList.size(); i++) {
                    rankSample(i);
                    if (wilcoxonSamplesList.get(i).getSource() == pictureContainer.get(1)) {
                        wilcoxonSamplesList.get(i).setIsLessThan(true);
                        rangeSum = rangeSum + wilcoxonSamplesList.get(i).getRank();
                    }
                }
            }
            double chance = calcChanceOfSpecificRangeSum(rangeSum, wilcoxonSamplesList.size(), partVectors);
            result = calcPValue(chance, partVectors);
            this.isValid = true;

        }
    }

    private int initTest() {
        for (Picture picture : pictureContainer.get(0)) {
            wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture.getExifParameter(parameter)),
                    pictureContainer.get(0)));
        }
        int numberOfElementsInSet = wilcoxonSamplesList.size();
        for (Picture picture : pictureContainer.get(1)) {
            wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture.getExifParameter(parameter)),
                    pictureContainer.get(1)));

        }
        return numberOfElementsInSet;
    }

    private double calcChanceOfSpecificRangeSum(double rangeSum, int numberOfAllSamples, int partVectors) {
        int vectors = 0;
        for (int n = 1; n < numberOfAllSamples; n++) {
            for (int i = 1; i < numberOfAllSamples; i++) {
                if ((n != i) && (n + i == rangeSum)) {
                    vectors++;
                }
            }
        }     
        return vectors/partVectors;
    }
    
    private void rankSample(int index) {
        if (index == (wilcoxonSamplesList.size() - 1)) {
            if (wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index - 1).getvalue()){
                wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        } else if (index == 0) {
            if (wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index + 1).getvalue()){
                wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                wilcoxonSamplesList.get(index).setPosition(index + 1);
            }                     
        } else {
            if ((wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index + 1).getvalue()) || (wilcoxonSamplesList.get(index).getvalue() == wilcoxonSamplesList.get(index - 1).getvalue())) {
                wilcoxonSamplesList.get(index).setPosition(index + 1.5);
            } else {
                wilcoxonSamplesList.get(index).setPosition(index + 1);
            }
        }    
    }

    private int fakultaet(int n) {
        int fakultaet = 1;
        int faktor = 1;
        while (faktor <= n) {
            fakultaet = fakultaet * faktor++;
        }
        return fakultaet;
    }
    
    private double chanceToBeLess (){
        double chance = 0;
        return chance;
        
    }
    
    private double calcPValue(double chanceOfPosition, int partVectors) {
        double pValue = 0;
        return pValue;
        
    }
}
