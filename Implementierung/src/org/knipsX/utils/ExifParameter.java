package org.knipsX.utils;


public enum ExifParameter {

    CAMERAMODEL {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return "Kameramodell";
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
            return "Blitz";
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
            return "Blendenzahl";
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
            return "Verschlusszeit";
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
            return "ISO";
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
            return "Brennweite";
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
            return "Datum";
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
            return "Objektiv Name";
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
