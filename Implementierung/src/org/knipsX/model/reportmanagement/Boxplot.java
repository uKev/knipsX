package org.knipsX.model.reportmanagement;

public class Boxplot {
	double mean;
	double median;
	double upperQuartile;
	double lowerQuartile;
	double upperWhisker;
	double lowerWhisker;
	double outlier[];
	double maxValue;
	double minValue;
	
	public double getMaxValue() {
		return maxValue;
	}
	public double getMinValue() {
		return minValue;
	}
	String PictureSetName;
	
	
	public String getPictureSetName() {
		return PictureSetName;
	}
	public double getMean() {
		return mean;
	}
	public double getMedian() {
		return median;
	}
	public double getUpperQuartile() {
		return upperQuartile;
	}
	public double getLowerQuartile() {
		return lowerQuartile;
	}
	public double getUpperWhisker() {
		return upperWhisker;
	}
	public double getLowerWhisker() {
		return lowerWhisker;
	}
	public double[] getOutlier() {
		return outlier;
	}
	
	
}
