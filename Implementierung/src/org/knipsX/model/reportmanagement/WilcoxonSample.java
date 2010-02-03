package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

class WilcoxonSample implements Comparable<WilcoxonSample>{
	
	private double value;
	private PictureContainer source;
	private int rank;
	private boolean isLessThan;
	
	public WilcoxonSample(double value, PictureContainer source) {
		this.value = value;
		this.source = source;
	}
		
	public double getvalue(){
		return this.value;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public PictureContainer getSource() {
		return this.source;		
	}
	
	public void setRank(int rankPosi){
		this.rank = rankPosi;
	}
	
	public boolean isLessTahn() {
		return this.isLessThan;		
	}
	
	public void setIsLessThan(boolean bool) {
		this.isLessThan = bool;
	}
	
	@Override
	public int compareTo(WilcoxonSample otherSample) {
		if (this.value < otherSample.getvalue()) {
			return -1;
		} else if (this.value > otherSample.getvalue()) {
			return 1;
		} else {
			return 0;
		}
	}
}
