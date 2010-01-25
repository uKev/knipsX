package org.knipsX.model.reportmanagement;

import java.util.ArrayList;

import org.knipsX.model.picturemanagement.PictureContainer;

/**
 * Model which contain the Boxplots and name them with an Axis.
 * @author Kevin Zuber
 *
 */
public class BoxplotModel extends AbstractSingleAxisModel{

	private Boxplot [] boxplots;
	private boolean wilcoxonTestActive;
	private WilcoxonTestType wilcoxonTestType;
	private float wilcoxonSignificance;
	
	
	public BoxplotModel() {
		super();
	}
	
	/**
	 * Constructor for the Boxplot Model
	 * @param pictureContainer
	 * @param xAxis
	 */
	public BoxplotModel(ArrayList<PictureContainer> pictureContainer, Axis xAxis) {
		super(pictureContainer, xAxis);
		// TODO: Auto-generated constructor stub
		// TODO: calculate the Stuff and save it to boxplots
	}

	public Boxplot[] getBoxplots() {
		return boxplots;
	}
	
	/**
	 * @return the result of the Wilcoxon test
	 */
	public float getWilcoxonPValue() {
		// TODO: implement
		return 0f;
	}

	/**
	 * 
	 * @return if the wilcoxon test is used
	 */
	public boolean isWilcoxonTestActive() {
		return wilcoxonTestActive;
	}

	public void setWilcoxonTestActive(boolean wilcoxonTestActive) {
		this.wilcoxonTestActive = wilcoxonTestActive;
	}

	public WilcoxonTestType getWilcoxonTestType() {
		return wilcoxonTestType;
	}

	public void setWilcoxonTestType(WilcoxonTestType wilcoxonTestType) {
		this.wilcoxonTestType = wilcoxonTestType;
	}

	public float getWilcoxonSignificance() {
		return wilcoxonSignificance;
	}

	public void setWilcoxonSignificance(float wilcoxonSignificance) {
		this.wilcoxonSignificance = wilcoxonSignificance;
	}

    @Override
    public Object getMaxX() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getMaxY() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getMinX() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object getMinY() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Axis getxAxis() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setxAxis(Axis xAxis) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public PictureParameter[] getPicturesWithMissingExifParameter() {
        // TODO Auto-generated method stub
        return null;
    }
	
	
}
