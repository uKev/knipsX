package org.knipsX.model.reportmanagement;

public class Category {
	private Bar bars[];

	public Category(Bar[] bars) {
		super();
		this.bars = bars;
	}

	public Bar[] getBars() {
		return bars;
	}

	public void setBars(Bar[] bars) {
		this.bars = bars;
	}
	
}
