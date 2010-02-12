package org.knipsX.utils.exifAdapter.jexifviewer;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.knipsX.utils.ExifParameter;
import org.knipsX.utils.XMP.XMPAdapter;

public class ExifAdapter {

    private final Logger log = Logger.getLogger(this.getClass());

    private final JIfdData exifData;

    private String strFilePath;

    public Object getExifParameter(final ExifParameter e) {
        switch (e) {
            case CAMERAMODEL:
                return this.getCameraModel();
            case FLASH:
                return this.getFlash();
            case FNUMBER:
                return this.getFNumber();
            case EXPOSURETIME:
                return this.getExposureTime();
            case ISO:
                return this.getISOSpeedRatings();
            case FOCALLENGTH:                
                return this.getFocalLength();
            case DATE:
                return this.getOriginalDate();
            case OBJECTIVENAME:
                return this.getObjective();
            case KEYWORDS:
                return this.getKeywords();
            default:
                assert false;
        }
        return null;
    }

    public ExifAdapter() {
        this.exifData = new JIfdData();
        this.strFilePath = "";
    }

    public ExifAdapter(final File imageFile) {
        this.exifData = new JIfdData(imageFile);
        this.strFilePath = imageFile.getAbsolutePath();
    }

    public ExifAdapter(final String imageFile) {
        this.exifData = new JIfdData(imageFile);
        this.strFilePath = imageFile;
    }

    public void setFilePath(final String imageFile) {
        this.strFilePath = imageFile;
    }

    private String getCameraModel() {
        assert this.exifData != null;
        return this.exifData.getModel();
    }

    private int getFlash() {
        assert this.exifData != null;
        return this.exifData.getFlash();
    }

    private Object getFNumber() {
        assert this.exifData != null;
        final float result = this.exifData.getFNumber(new String[1]);

        if (Float.compare(result, 0) != 0) {
            return result;
        }

        return null;
    }

    private Object getExposureTime() {
        assert this.exifData != null;
        final double exposureTime = this.exifData.getExposureTime(new String[1]) / 1000000.0;
        assert exposureTime != Double.NaN;
        Object returnValue;
        if (exposureTime == 0) {
            returnValue = null;
        } else {

            returnValue = exposureTime;
        }
        return returnValue;
    }

    private Object getISOSpeedRatings() {
        assert this.exifData != null;
        final int returnValue = this.exifData.getISOSpeedRatings();

        if (returnValue == 0) {
            return null;
        } else {
            return returnValue;
        }
    }

    private float getFocalLength() {
        assert this.exifData != null;
        return this.exifData.getFocalLength();
    }

    private Object getOriginalDate() {
        assert this.exifData != null;

        final Date date = JIfd.getDateFromString(this.exifData.getOriginalDateTime());
        Object time;

        if (date == null) {
            this.log.warn("date == null");
            time = null;
        } else {
            time = date.getTime();
        }
        return time;
    }

    private String[] getKeywords() {
        final XMPAdapter xmp = new XMPAdapter(new File(this.strFilePath));
        return xmp.getKeywords();
    }

    private String getObjective() {
        return "";
    }
}