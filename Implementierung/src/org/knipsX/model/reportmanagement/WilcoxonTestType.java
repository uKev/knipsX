package org.knipsX.model.reportmanagement;

/**
 * Represents the types of a WilcoxonTest.
 * 
 * @author Kevin Zuber
 * 
 */
public enum WilcoxonTestType {

    /**
     * TODO: improve entry
     * Test for Less
     */
    LESS {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Less";

        }
    },
    /**
     * TODO: improve entry
     * Test for Greater
     */
    GREATER {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Greater";

        }
    },
    /**
     * TODO: improve entry
     * Test for both sides
     */
    TWO_SIDED {
        @Override
        public String toString() {

            // INTERNATIONALIZE
            return "Two sided";

        }
    };
    /**
     * String representation of the Wilcoxon test type
     * 
     * @return the string representation of the Wilcoxon test type.
     */
    @Override
    public abstract String toString();
}
