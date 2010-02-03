package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;
import org.knipsX.utils.ExifParameter;

public class WilcoxonTest {
    
    private boolean isActive = false;
    private boolean isValid = false;
    private WilcoxonTestType wilcoxenType;
    private ArrayList<PictureContainer> pictureContainer;
    private ExifParameter parameter;
    private float significance = 0;
    private float result;
    
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
    
    public WilcoxonTestType getWilcoxonTestType(){
        return wilcoxenType;
        
    }
    public void setWilcoxonTestType(WilcoxonTestType wilcoxenType){
        this.wilcoxenType = wilcoxenType;
    }
}
