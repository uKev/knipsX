package org.knipsX.utils;


public enum ExifParameter {

    CAMERAMODEL {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Cameramodel";
        }

        @Override
        public boolean isOrdinal() {
            return false;
        }
    },

    FLASH {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Flash";
        }

        @Override
        public boolean isOrdinal() {
            return false;
        }
    },

    FNUMBER {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Fnumber";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    EXPOSURETIME {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Exposuretime";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    ISO {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Iso";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    FOCALLENGTH {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Focallength";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    DATE {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Date";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    OBJECTIVENAME {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Objectivname";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    KEYWORDS {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Stichwort";
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String toString();

    /**
     * Denotes if the specified EXIF parameter is ordinal. Thus is able
     * to be sorted in any way.
     * 
     * @return Returns true if EXIF parameter is ordinal, false otherwise
     */
    public abstract boolean isOrdinal();

}
