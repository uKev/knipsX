package org.knipsX.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static boolean isStringOk(final String toCheck) {

        String stringPattern = "[\\u0020-\\u007e\\u00a2-\\u00a7\\u00c0-\\u00ff]*";
        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(toCheck);

        if (toCheck == null) {
            return false;
        } else if ((toCheck.equals("")) || (matcher.matches())) {
            return false;
        } else {
            return true;
        }
    }
}
