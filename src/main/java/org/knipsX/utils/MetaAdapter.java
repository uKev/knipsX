package org.knipsX.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.Tag;

public class MetaAdapter {

    private final Logger logger = Logger.getLogger(this.getClass());

    private final Metadata metadata;

    private final Map<String, Tag> metaTags = new TreeMap<String, Tag>();

    public Object getExifParameter(final ExifParameter e) throws MetadataException {
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

    public MetaAdapter(final File imageFile) throws JpegProcessingException {
        this.metadata = JpegMetadataReader.readMetadata(imageFile);
        this.initialize();
    }

    public MetaAdapter(final String imageFile) throws JpegProcessingException {
        this.metadata = JpegMetadataReader.readMetadata(new File(imageFile));
        this.initialize();
    }

    private void initialize() {

        final Iterator directories = this.metadata.getDirectoryIterator();
        while (directories.hasNext()) {
            final Directory directory = (Directory) directories.next();

            final Iterator tags = directory.getTagIterator();
            while (tags.hasNext()) {
                final Tag tag = (Tag) tags.next();
                this.metaTags.put(tag.getTagName(), tag);
            }
        }
    }

    private String getCameraModel() throws MetadataException {
        return this.metaTags.get("Model").getDescription();
    }

    private String getFlash() throws MetadataException {
        return this.metaTags.get("Flash").getDescription();
    }

    private Double getFNumber() throws MetadataException {
        final String fNumber = this.metaTags.get("F-Number").getDescription();
        final Double result = Double.parseDouble(fNumber.substring(1).replace(',', '.'));

        if (Double.compare(result, 0d) != 0) {
            return result;
        }
        return null;
    }

    private Double getExposureTime() throws MetadataException {
        final String exposureTime = this.metaTags.get("Exposure Time").getDescription();
        
        try {
            final String[] parts = exposureTime.split("\\s");
            final String[] times = parts[0].split("/");
            return Double.parseDouble(times[0]) / Double.parseDouble(times[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    private Double getISOSpeedRatings() throws MetadataException {
        final String isoSpeedRatings = this.metaTags.get("ISO Speed Ratings").getDescription();
        final Double result = Double.parseDouble(isoSpeedRatings);

        if (Double.compare(result, 0d) != 0) {
            return result;
        }
        return null;
    }

    private Double getFocalLength() throws MetadataException {
        final String focalLength = this.metaTags.get("Focal Length").getDescription();
        final String[] parts = focalLength.split("\\s");
        final Double result = Double.parseDouble(parts[0].replace(',', '.'));

        if (Double.compare(result, 0d) != 0) {
            return result;
        }
        return null;
    }

    private Long getOriginalDate() throws MetadataException {
        final String focalLength = this.metaTags.get("Date/Time Original").getDescription();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        try {
            return sdf.parse(focalLength).getTime();
        } catch (final ParseException exception) {
            this.logger.warn("Couldn't parse the date.");
        }
        return null;
    }

    private String getObjective() {
        return "";
    }

    private String[] getKeywords() throws MetadataException {
        final String keywords = this.metaTags.get("Keywords").getDescription();
        return keywords.split("\\s");
    }
}
