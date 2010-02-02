package org.knipsX.utils;

/**
 * Checks strings the user can fill in. Here the rules are representet and it is checked if a string is valid.
 */
public final class StringChecker {

    private StringChecker() {
    }

    /**
     * Checks the string which is committed for validation.
     * 
     * @param toCheck
     *            the String to check.
     * @return true if it validates, false if not.
     */
    public static boolean isStringOk(String toCheck) {
        if (toCheck == null) {
            return false;
        } else if (toCheck.equals("")) {
            return false;
        } else {
            return true;
        }
    }
}
