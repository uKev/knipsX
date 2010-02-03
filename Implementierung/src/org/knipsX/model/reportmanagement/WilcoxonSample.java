package org.knipsX.model.reportmanagement;

import org.knipsX.model.picturemanagement.PictureContainer;

class WilcoxonSample implements Comparable<WilcoxonSample>{
	
	private double value;
	private PictureContainer source;
	private double position;
	private boolean isLessThan;
	
	public WilcoxonSample(double value, PictureContainer source) {
		this.value = value;
		this.source = source;
	}
		
	public double getvalue(){
		return this.value;
	}
	
	public double getRank() {
		return this.position;
	}
	
	public PictureContainer getSource() {
		return this.source;		
	}
	
	public void setPosition(double posi){
		this.position = posi;
	}
	
	public boolean isLessTahn() {
		return this.isLessThan;		
	}
	
	public void setIsLessThan(boolean bool) {
		this.isLessThan = bool;
	}
	
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
