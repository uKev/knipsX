package org.knipsX.utils;


public enum ExifParameter {

    CAMERAMODEL {
        @Override
        public String toString() {
            // INTERNATIONALIZE
            return Messages.getString("ExifParameter.0"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.1"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.2"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.3"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.4"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.5"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.6"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.7"); //$NON-NLS-1$
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
            return Messages.getString("ExifParameter.8"); //$NON-NLS-1$
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
