package org.knipsX.utils;

/**
 * Collection of functions to convert things to other things
 * 
 * @author Kevin Zuber
 * 
 */

public final class Converter {

    /**
     * Tries to convert an object to the Double value which is represented by
     * it. Raises ClassCastException if it is not possible.
     * 
     * @throws ClassCastException
     *             if it can not convert the object to Double
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
        } else if (object instanceof Long) {
            returnValue = ((Long) object).doubleValue();
        } else if (object instanceof String) {

            final String stringValue = ((String) object);
            if (!stringValue.isEmpty()) {
                returnValue = Double.valueOf((stringValue));
            } else {
                returnValue = 0.0;
                throw new ClassCastException("Converter.objectToDouble: can not handle empty String");
            }

        } else {

            returnValue = 0.0;
            throw new ClassCastException("Converter.objectToDouble: can not handle object from type "
                    + object.getClass().toString() + " Value: " + object.toString());
        }
        return returnValue;
    }

    /**
     * Converts a given object into a proper string representation. 
     * Note that this also converts object arrays. When null is passed 
     * as a parameter in returns the empty string
     * 
     * @param object the object you want to convert to a string
     * 
     * @return a nice string representation of the specified object
     */
    public static String objectToString(final Object object) {
        
        if (object == null) {
            return "";
        }
        
        String output = "";
        if (object instanceof Object[]) {

            for (int j = 0; j < ((Object[]) object).length; j++) {
                if (j == 0) {
                    output = ((Object[]) object)[j].toString();
                } else {
                    output = output + ", " + ((Object[]) object)[j].toString();
                }
            }
        } else {
            output = object.toString();
        }
        return output;
    }

    private Converter() {
    }

}
