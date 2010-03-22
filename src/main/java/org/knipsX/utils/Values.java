package org.knipsX.utils;

import java.awt.Toolkit;

/**
 * This class stores commonly used values.
 * @author Kevin Zuber
 *
 */
public class Values {
    private final static double CROP_FACTOR = 0.25;
    
    public final static double EPSILON = 0.000001;
    public final static String VERSION = "1.0 beta";
    
    public final static int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public final static int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    
    public final static int PREFERRED_WINDOW_HEIGHT = (int) (WINDOW_HEIGHT - WINDOW_HEIGHT * CROP_FACTOR);
    public final static int PREFERRED_WINDOW_WIDTH = (int) (WINDOW_WIDTH - WINDOW_WIDTH * CROP_FACTOR);    
}
