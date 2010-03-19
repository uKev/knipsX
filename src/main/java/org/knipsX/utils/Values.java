package org.knipsX.utils;

import java.awt.Toolkit;

/**
 * This class stores commonly used values.
 * @author Kevin Zuber
 *
 */
public class Values {
    private final static int CROP_FACTOR = 50;
    
    public final static double EPSILON = 0.000001;
    public final static String VERSION = "1.0 beta";
    
    public final static int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public final static int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    
    public final static int PREFERRED_WINDOW_HEIGHT = WINDOW_HEIGHT - CROP_FACTOR;
    public final static int PREFERRED_WINDOW_WIDTH = WINDOW_WIDTH - CROP_FACTOR;    
}
