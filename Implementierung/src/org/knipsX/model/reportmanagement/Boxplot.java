package org.knipsX.model.reportmanagement;

/**
 * Boxplot.. TODO:
 * @author Kevin Zuber
 *
 */
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
	
	public Boxplot() {
		
	}
	
	public Boxplot(double mean, double median, double upperQuartile,
			double lowerQuartile, double upperWhisker, double lowerWhisker,
			double[] outlier, double maxValue, double minValue,
			String pictureSetName) {
		super();
		this.mean = mean;
		this.median = median;
		this.upperQuartile = upperQuartile;
		this.lowerQuartile = lowerQuartile;
		this.upperWhisker = upperWhisker;
		this.lowerWhisker = lowerWhisker;
		this.outlier = outlier;
		this.maxValue = maxValue;
		this.minValue = minValue;
		PictureSetName = pictureSetName;
	}
	
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
