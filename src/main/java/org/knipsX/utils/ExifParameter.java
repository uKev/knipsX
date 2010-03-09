package org.knipsX.utils;

import org.knipsX.Messages;

public enum ExifParameter {

    CAMERAMODEL {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.0");
        }

        @Override
        public boolean isOrdinal() {
            return false;
        }
    },

    FLASH {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.1");
        }

        @Override
        public boolean isOrdinal() {
            return false;
        }
    },

    FNUMBER {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.2");
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    EXPOSURETIME {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.3");
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    ISO {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.4");
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    FOCALLENGTH {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.5");
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    DATE {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.6");
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    OBJECTIVENAME {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.7");
        }

        @Override
        public boolean isOrdinal() {
            return true;
        }
    },

    KEYWORDS {
        @Override
        public String toString() {
            return Messages.getString("ExifParameter.8");
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
