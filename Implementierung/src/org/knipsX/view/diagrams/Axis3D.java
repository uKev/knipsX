package org.knipsX.view.diagrams;

class Axis3D {
    private String description;
    private int numberOfSegments = 10;
    private String[] segmentDescription = new String[numberOfSegments];
    private double axisSize = 10;

    public double getAxisSize() {
        return this.axisSize;
    }

    public String getDescription() {
        return this.description;
    }

    public int getNumberOfSegments() {
        return this.numberOfSegments;
    }

    public String[] getSegmentDescription() {
        return this.segmentDescription;
    }

    public double getSegmentSize() {
        return this.axisSize / (double) this.numberOfSegments;
    }

    public void setAxisSize(final double axisSize) {
        this.axisSize = axisSize;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setNumberOfSegments(final int numberOfSegments) {
        this.numberOfSegments = numberOfSegments;
        this.segmentDescription = new String[numberOfSegments];
    }

    public void setSegmentDescription(final String[] segmentDescription) {
        this.segmentDescription = segmentDescription;
    }

}