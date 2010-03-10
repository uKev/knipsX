package org.knipsX.model.reportmanagement;

/**
 * A simple text model which solely contains text
 * 
 * @author David Kaufman
 * 
 */

public class TextModel extends AbstractReportModel {

    private String text = new String();

    /**
     * Creates a new TextModel from text
     * 
     * @param text
     *            the text which is the heart of the TextModel
     */
    public TextModel(final String text) {
        this.setText(text);
    }

    /**
     * Creates a new empty TextModel.
     */
    public TextModel() {
        super();
    }

    /**
     * Setter for the text inside the TextModel
     * 
     * @param text
     *            the text inside the TextModel
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * Getter for the text inside the TextModel
     * 
     * @return the text inside the TextModel
     */
    public String getText() {
        return this.text;
    }

    @Override
    protected void calculate() {
        /* Do nothing because we don't need to calculate something */
    }

    @Override
    public boolean isModelValid() {

        /* let's calculate nothing */
        this.calculateIfRequired();

        /* Do we have some text? */
        return !this.text.isEmpty();
    }
}