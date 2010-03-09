package org.knipsX.model.reportmanagement;

import org.knipsX.Messages;

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
            return Messages.getString("WilcoxonTestType.0");
        }
    },
    /**
     * TODO: improve entry
     * Test for Greater
     */
    GREATER {
        @Override
        public String toString() {
            return Messages.getString("WilcoxonTestType.1");
        }
    },
    /**
     * TODO: improve entry
     * Test for both sides
     */
    TWO_SIDED {
        @Override
        public String toString() {
            return Messages.getString("WilcoxonTestType.2");
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
