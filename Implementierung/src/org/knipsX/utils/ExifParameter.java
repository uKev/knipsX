package org.knipsX.utils;

public enum ExifParameter {

    CAMERAMODEL {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Cameramodel";
        }
    },
    
    FLASH {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Flash";
        }
    },
    
    FNUMBER {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Fnumber";
        }
    },
    
    EXPOSURETIME {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Exposuretime";
        }
    },
    
    ISO {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Iso";
        }
    },
    
    FOCALLENGTH {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Focallength";
        }
    },
    
    DATE {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Date";
        }
    },
    
    DAYOFWEEK {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Dayofweek";
        }
    },
    
    TIME {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Time";
        }
    },
    
    OBJECTIVENAME {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Objectivname";
        }
    };

    /**
     * {@inheritDoc}
     */
    public abstract String toString();
}
