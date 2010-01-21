package org.knipsX.utils;

public enum ExifParameter {
    
    CAMERAMODEL {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, FLASH {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, FNUMBER, EXPOSURETIME {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, ISO {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, FOCALLENGTH {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, DATE {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, DAYOFWEEK {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, TIME {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    }, OBJECTIVENAME {
        @Override
        public String toString() {
            //INTERNATIONALIZE
            return null;
        }
    };

    /**
     * {@inheritDoc}
     */
    public abstract String toString();

}
