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
    private float significance = 0;
    private float result;
    private List<WilcoxonSample> wilcoxonSamplesList = new LinkedList<WilcoxonSample>();
    
    public WilcoxonTest(ArrayList<PictureContainer> pictureContainer, ExifParameter parameter) {
        this.pictureContainer = pictureContainer;
        this.parameter = parameter;      
    }  

	public WilcoxonTest(ArrayList<PictureContainer> pictureContainer, ExifParameter parameter, boolean isActive, float significance, WilcoxonTestType wilcoxenType) {
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
    
    public void calculate() { 	
    	if (wilcoxenType != null) {
    		int chancevalue = 0; 
    		int numberOfElementsInFirstSet = initTest();
    		int numberOfElementsInSecondSet = wilcoxonSamplesList.size() - numberOfElementsInFirstSet;
    		int partVectors = numberOfElementsInFirstSet + numberOfElementsInSecondSet;
    		Collections.sort(wilcoxonSamplesList);
    		if (numberOfElementsInFirstSet < numberOfElementsInSecondSet) {
	    		for (int n = 0; n < wilcoxonSamplesList.size(); n++) {
	    			wilcoxonSamplesList.get(n).setRank(n+1);
	    			if (wilcoxonSamplesList.get(n).getSource() == pictureContainer.get(0)) {
	    				wilcoxonSamplesList.get(n).setIsLessThan(true);
	    				chancevalue = chancevalue + wilcoxonSamplesList.get(n).getRank();
	    			}
    			}
    		} else {
    			for (int i = 0; i < wilcoxonSamplesList.size(); i++) {
    				wilcoxonSamplesList.get(i).setRank(i+1);
	    			if (wilcoxonSamplesList.get(i).getSource() == pictureContainer.get(1)) {
	    				wilcoxonSamplesList.get(i).setIsLessThan(true);
	    				chancevalue = chancevalue + wilcoxonSamplesList.get(i).getRank();
	    			}
    			}
    		}  	
    	}
    }
    
    private int initTest() {
    	for (Picture picture : pictureContainer.get(0)) {
    		wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture.getExifParameter(parameter)),pictureContainer.get(0)));
    	}
    	int numberOfElementsInSet = wilcoxonSamplesList.size();
    	for (Picture picture : pictureContainer.get(1)) {
    		wilcoxonSamplesList.add(new WilcoxonSample(Converter.objectToDouble(picture.getExifParameter(parameter)),pictureContainer.get(1)));
   
    	}
    	return numberOfElementsInSet;
	}      
}
