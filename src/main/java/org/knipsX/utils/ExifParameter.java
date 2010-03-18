package org.knipsX.utils;

import java.util.ArrayList;
import java.util.List;

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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
			return true;
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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
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

		@Override
		public boolean active() {
			return false;
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
    
    
    /**
     * Specifies if the parameter should be used inside knipsX. This method was introduced
     * because some EXIF parameters weren't processed correctly.
     *  
     * @return returns true if the EXIF parameter should be used in a report, false otherwise
     */
    public abstract boolean active();
    
    
    public static ExifParameter[] getActiveParameters() {
    	List<ExifParameter> validExifParameters = new ArrayList<ExifParameter>();
    	
    	for (ExifParameter param : ExifParameter.values()) {
    		if (param.active()) {
    			validExifParameters.add(param);
    		}
    	}
    	
    	ExifParameter[] returnarray = new ExifParameter[validExifParameters.size()];
    	validExifParameters.toArray(returnarray);
    	
    	return returnarray;
    }

}
