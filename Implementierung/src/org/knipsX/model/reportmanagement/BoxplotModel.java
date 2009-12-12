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
	public boolean isWilcoxonSignificant() {
		return true;
	}

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
	
	
}
