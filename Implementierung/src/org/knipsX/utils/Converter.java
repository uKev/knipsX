package org.knipsX.utils;

/**
 * Collection of functions to convert things to other things
 * 
 * @author Kevin Zuber
 * 
 */

public final class Converter {

    private Converter() {
    }
    
    /**
     * Tries to convert an object to the Double value which is represented by it. Raises ClassCastException if it is not
     * possible.
     * 
     * @throws ClassCastException if it can not convert the object to Double
     * @param object
     *            the object which should be converted to Double
     * @return an Double representation of the object
     */
    public static Double objectToDouble(final Object object) throws ClassCastException {

        Double returnValue;

        if (object instanceof Double) {
            returnValue = ((Double) object);
        } else if (object instanceof Float) {
            returnValue = ((Float) object).doubleValue();
        } else if (object instanceof Integer) {
            returnValue = ((Integer) object).doubleValue();
        } else {
            returnValue = 0.0;
            throw new ClassCastException("Converter.objectToDouble: can not handle object from type "
                    + object.getClass().toString());
        }
        return returnValue;
    }


}
