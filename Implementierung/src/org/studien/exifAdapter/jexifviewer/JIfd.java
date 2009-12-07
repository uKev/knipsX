/*
 *  JIfd.java
 *
 *  Created on 8. April 2006, 18:34
 *
 *  Copyright (C) 8. April 2006  <Reiner>

 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */


package jexifviewer;

import java.io.RandomAccessFile;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import jexifviewer.shared.dataformathelper.JDataFormatHelper;


/**
 *
 * @author reiner
 */

public class JIfd
{
    private long m_offset;
    private int m_count;
    private long m_nextIfdOffset;
    private boolean m_bIsIntel;
    
    public ArrayList<JExifTag> m_tagArray;

    public static final float GRAD_INVALID = 999.0f;
    
    /** Creates a new instance of JIfd */
    public JIfd()
    {
    }
	
    public static Date getDateFromString(String str)
    {
        Date date = null;
        if (str != null && str.length() == 19)
        {
            int year=0, month=0, day=0, hour=0, min=0, sec=0;
            if (str.substring(4, 5).equals(":"))
            {
                year = Integer.parseInt(str.substring(0, 4));
                month = Integer.parseInt(str.substring(5, 7));
                day = Integer.parseInt(str.substring(8, 10));

                hour = Integer.parseInt(str.substring(11, 13));
                min = Integer.parseInt(str.substring(14, 16));
                sec = Integer.parseInt(str.substring(17, 19));
            }
            else if (str.substring(2, 3).equals("/"))
            {
                month = Integer.parseInt(str.substring(0, 2));
                day = Integer.parseInt(str.substring(3, 5));
                year = Integer.parseInt(str.substring(6, 10));

                hour = Integer.parseInt(str.substring(11, 13));
                min = Integer.parseInt(str.substring(14, 16));
                sec = Integer.parseInt(str.substring(17, 19));
            }
            else
            {
                assert false;
            }
            GregorianCalendar cal = new GregorianCalendar();
            cal.set(year, month-1, day, hour, min, sec);
            date = cal.getTime();
        }
        return date;
    }
    
    public boolean isTagPresent(int tag)
    {
        if (tag == JExifTag.EXIFTAG_TAG_NAME || tag == JExifTag.EXIFTAG_TAG_FILESIZE || tag == JExifTag.EXIFTAG_TAG_FILEDATETIME)
            return true;
        else if (tag == JExifTag.EXIFTAG_TAG_FOCALPLANERESOLUTION)
            return (findTag(JExifTag.EXIFTAG_TAG_FOCALPLANEXRESOLUTION) != null && findTag(JExifTag.EXIFTAG_TAG_FOCALPLANEYRESOLUTION) != null && findTag(JExifTag.EXIFTAG_TAG_FOCALPLANERESOLUTIONUNIT) != null);
        else if (tag == JExifTag.EXIFTAG_TAG_RESOLUTION)
            return (findTag(JExifTag.EXIFTAG_TAG_XRESOLUTION) != null && findTag(JExifTag.EXIFTAG_TAG_YRESOLUTION) != null);
        else if (tag == JExifTag.EXIFTAG_TAG_SIZE)
            return (findTag(JExifTag.EXIFTAG_TAG_PIXELXDIMENSION) != null && findTag(JExifTag.EXIFTAG_TAG_PIXELYDIMENSION) != null);
        else if (tag == JExifTag.GPSTAG_TAG_LATITUDE)
            return (findTag(JExifTag.GPSTAG_TAG_LATITUDE) != null && findTag(JExifTag.GPSTAG_TAG_LATITUDEREF) != null);
        else if (tag == JExifTag.GPSTAG_TAG_LONGITUDE)
            return (findTag(JExifTag.GPSTAG_TAG_LONGITUDE) != null && findTag(JExifTag.GPSTAG_TAG_LONGITUDEREF) != null);
        else if (tag == JExifTag.GPSTAG_TAG_ALTITUDE)
            return (findTag(JExifTag.GPSTAG_TAG_ALTITUDE) != null && findTag(JExifTag.GPSTAG_TAG_ALTITUDEREF) != null);
        else if (tag == JExifTag.GPSTAG_TAG_MAPLINK)
            return (isTagPresent(JExifTag.GPSTAG_TAG_LATITUDE) && isTagPresent(JExifTag.GPSTAG_TAG_LONGITUDE));
        else if (tag == JExifTag.GPSTAG_TAG_VERSIONID)
        {
            JExifTag exifTag = findTag(tag);
            return (exifTag != null && exifTag.getByteArray() != null);
        }
        else return (findTag(tag) != null);
    }
    
    public String getMake()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_MAKE);
        if (tag != null) str = tag.getString();
        return str;
    }
    
    /*
    public String getCanonCameraSerial()
    {
	String str = "";
	JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_CANON_SERIAL);
	if (tag != null) str = tag.getString();
	return str;
    }
     */

    public String getArtist()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_ARTIST);
        if (tag != null) str = tag.getString();
        return str;
    }

    public String getDescription()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_DESCRIPTION);
        if (tag != null) str = tag.getString();
        return str;
    }

    public String getModel()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_MODEL);
        if (tag != null) str = tag.getString();
        return str;
    }

    public int getOrientation()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_ORIENTATION);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public float getXResolution()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_XRESOLUTION);
        if (tag != null)
        {
            assert tag.getLong2() != 0;
            if (tag.getLong2() != 0)
                f = (float)tag.getLong1() / (float)tag.getLong2();
            else f = (float)tag.getLong1();
        }
        return f;
    }

    public float getYResolution()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_YRESOLUTION);
        if (tag != null)
        {
            assert tag.getLong2() != 0;
            if (tag.getLong2() != 0)
                f = (float)tag.getLong1() / (float)tag.getLong2();
            else f = (float)tag.getLong1();
        }
        return f;
    }

    public int getResolutionUnit()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_RESOLUTIONUNIT);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public String getSoftware()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SOFTWARE);
        if (tag != null) str = tag.getString();
        return str;
    }

    public String getChangeDateTime()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_CHANGEDATETIME);
        if (tag != null) str = tag.getString();
        return str;
    }

    public int getYCbCrPosition()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_YCBCRPOSITION);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public String getCopyright()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_COPYRIGHT);
        if (tag != null) str = tag.getString();
        return str;
    }

    /**
     * returns the exposure time in µ sec
     * @param str string representation
     * @return exposure time in µ sec
     */
    public int getExposureTime(String[] str)
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_EXPOSURETIME);
        if (tag != null)
        {
            long h1 = tag.getLong1();
            long h2 = tag.getLong2();
            long hh = h1;
            int count1 = 0, count2 = 0;
            while (true)
            {
                if ((hh/10)*10 == hh)
                {
                    hh /= 10;
                    count1++;
                }
                else break;
            }
            hh = h2;
            while (true)
            {
                if ((hh/10)*10 == hh)
                {
                    hh /= 10;
                    count2++;
                }
                else break;
            }
            h = Math.min(count1, count2);
            while(h-- > 0)
            {
                h1 /= 10;
                h2 /= 10;
            }
            if (str != null)
            {
                if (h1 > h2)
                {
                    if ((h1/h2)*h2 == h1)
                        str[0] = String.format("1/%1$d", h1/h2);
                    else
                        str[0] = String.format("%1$d/%2$d", h1, h2);
                }
                else if (h1 < h2)
                {
                    if ((h2/h1)*h1 == h2)
                        str[0] = String.format("1/%1$d", h2/h1);
                    else
                        str[0] = String.format("%1$d/%2$d", h1, h2);
                }
                else str[0] = "1";
            }
            h = (int)((h1*1000000)/h2);
        }
        return h;
    }

    public float getFNumber(String[] str)
    {
        float h = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FNUMBER);
        if (tag != null)
        {
            long h1 = tag.getLong1();
            long h2 = tag.getLong2();
            h = (float)h1/(float)h2;
            if (str != null) str[0] = String.format("f/%1$.1f", h);
        }
        return h;
    }

    public int getExposureProgram()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_EXPOSUREPROGRAM);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getISOSpeedRatings()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_ISOSPEEDRATINGS);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public String getExifVersion()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_EXIFVERSION);
        if (tag != null)
        {
            str = tag.getString();
            str = str.substring(0, 2) + "." + str.substring(2, str.length());
            if (str.charAt(0) == '0')
            str = str.substring(1);
            if (str.charAt(str.length()-1) == '0')
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    public String getOriginalDateTime()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_ORIGINALDATETIME);
        if (tag != null) str = tag.getString();
        return str;
    }

    public String getDigitizedDateTime()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_DIGITIZEDDATETIME);
        if (tag != null) str = tag.getString();
        return str;
    }

    public String getComponentsConfig()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_COMPONENTSCCONFIG);
        if (tag != null) str = tag.getString();
            return str;
    }

    public float getCompressedBitsPerPixel()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_COMPRESSEDPITSPERPIXEL);
        if (tag != null)
            f = (float)tag.getLong1()/(float)tag.getLong2();
        return f;
    }
    
    public float getShuterSpeedValue()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SHUTERSPEEDVALUE);
        if (tag != null)
            f = (float)tag.getInteger1()/(float)tag.getInteger2();
        return f;
    }

    public float getApertureValue()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_APERTUREVALUE);
        if (tag != null)
            f = (float)tag.getLong1()/(float)tag.getLong2();
        return f;
    }

    public float getBrightnessValue()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_BRIGHTNESSVALUE);
        if (tag != null)
        {
            if (tag.getInteger1() != -1)
            f = (float)tag.getInteger1()/(float)tag.getInteger2();
        }
        return f;
    }

    public float getExposureBiasValue()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_EXPOSUREBIASVALUE);
        if (tag != null)
            f = (float)tag.getInteger1()/(float)tag.getInteger2();
        return f;
    }
    
    public float getMaxApertureValue()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_MAXAPERTUREVALUE);
        if (tag != null)
            f = (float)tag.getLong1()/(float)tag.getLong2();
        return f;
    }
    
    public int getMeteringMode()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_METERINGMODE);
        if (tag != null)
            h = tag.getInteger1();
        return h;
    }

    public int getLightSource()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_LIGHTSOURCE);
        if (tag != null)  h = tag.getInteger1();
        return h;
    }

    public int getFlash()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FLASH);
        if (tag != null)  h = tag.getInteger1();
        return h;
    }
    
    public float getFocalLength()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FOCALLENGTH);
        if (tag != null)  f = (float)tag.getLong1() / (float)tag.getLong2();
        return f;
    }

    public int getColorSpace()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_COLORSPACE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getPixelXDimension()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_PIXELXDIMENSION);
        if (tag != null) h = tag.getInteger();
        return h;
    }

    public int getPixelYDimension()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_PIXELYDIMENSION);
        if (tag != null) h = tag.getInteger();
        return h;
    }

    public float getFocalPlaneXResolution()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FOCALPLANEXRESOLUTION);
        if (tag != null) f = (float)tag.getLong1() / (float)tag.getLong2();
        return f;
    }
    
    public float getFocalPlaneYResolution()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FOCALPLANEYRESOLUTION);
        if (tag != null) f = (float)tag.getLong1() / (float)tag.getLong2();
        return f;
    }

    public int getFocalPlaneResolutionUnit()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FOCALPLANERESOLUTIONUNIT);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getSensingMode()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SENSINGMODE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }
    
    public int getCustomRendered()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_CUSTOMRENDERED);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getExposureMode()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_EXPOSUREMODE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getWhiteBalance()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_WHITEBALANCE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }
    
    public float getDigitalZoomRatio()
    {
        float f = 0.0f;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_DIGITALZOOMRATIO);
        if (tag != null) f = (float)tag.getLong1() / (float)tag.getLong2();
        return f;
    }

    public int getSceneType()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SCENETYPE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getSceneCaptureType()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SCENECAPTURETYPE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getSharpness()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SHARPNESS);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getSubjectDistanceRange()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SUBJECTDISTANCERANGE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getFocalLength35mm()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FOCALLENGTH35MM);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getGainControl()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_GAINCONTROL);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getContrast()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_CONTRAST);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getSaturation()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_SATURATION);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public int getFileSource()
    {
        int h = 0;
        JExifTag tag = findTag(JExifTag.EXIFTAG_TAG_FILESOURCE);
        if (tag != null) h = tag.getInteger1();
        return h;
    }

    public String getGPSVersionID()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_VERSIONID);
        if (tag != null)
        {
            byte [] byteArray = tag.getByteArray();
            // assert (byteArray != null && byteArray.length == 4);
            if (byteArray != null && byteArray.length == 4)
                str = String.format("%d.%d.%d.%d", (int)byteArray[0], (int)byteArray[1], (int)byteArray[2], (int)byteArray[3]);
        }
        return str;
    }

    public String getGPSLatitudeRef()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_LATITUDEREF);
        if (tag != null) str = tag.getString();
        return str;
    }

    private float getGradFromExifTag(JExifTag tag)
    {
        float f = GRAD_INVALID;
        long[] longArray1 = tag.getLongArray1();
        long[] longArray2 = tag.getLongArray2();
        assert (longArray1 != null && longArray1 != null && longArray1.length == 3 && longArray2.length == 3);
        if (longArray1 != null && longArray1 != null && longArray1.length == 3 && longArray2.length == 3)
        {
            float grad = ((float)longArray1[0]) / ((float)longArray2[0]);
            float min = (((float)longArray1[1]) / ((float)longArray2[1]));
            float sec = (((float)longArray1[2]) / ((float)longArray2[2]));
            f = grad + (min + sec / 60.0f) / 60.0f;
        }
        return f;
    }

    public float getGPSLatitude()
    {
        float f = GRAD_INVALID;
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_LATITUDE);
        if (tag != null) f = getGradFromExifTag(tag);
        return f;
    }

    public String getGPSLongitudeRef()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_LONGITUDEREF);
        if (tag != null) str = tag.getString();
        return str;
    }

    public float getGPSLongitude()
    {
        float f = GRAD_INVALID;
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_LONGITUDE);
        if (tag != null) f = getGradFromExifTag(tag);
        return f;
    }

    public int getGPSAltitudeRef()
    {
        int h = -1;
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_ALTITUDEREF);
        if (tag != null)
            h = tag.getInteger1();
        return h;
    }

    public float getGPSAltitude()
    {
        float f = 0;
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_ALTITUDE);
        if (tag != null)
            f = ((float)tag.getLong1()) / ((float)tag.getLong2());
        return f;
    }

    public String getGPSSatellites()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_SATELLITES);
        if (tag != null)
            str= tag.getString();
        return str;
    }

    public String getGPSStatus()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_STATUS);
        if (tag != null)
            str= tag.getString();
        return str;
    }

    public String getGPSMeasureMode()
    {
        String str = "";
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_MEASUREMODE);
        if (tag != null)
            str= tag.getString();
        return str;
    }

    public Date getGPSTimestamp()
    {
        Date date = null;
        JExifTag tag = findTag(JExifTag.GPSTAG_TAG_TIMESTAMP);
        if (tag != null)
        {
            long[] array1 = tag.getLongArray1();
            long[] array2 = tag.getLongArray2();
            assert(array1 != null && array2 != null && array1.length == 3 && array2.length == 3);
            if (array1 != null && array2 != null && array1.length == 3 && array2.length == 3)
            {
                float hour = ((float)array1[0]) / ((float)array2[0]);
                float min = ((float)array1[1]) / ((float)array2[1]);
                float sec = ((float)array1[2]) / ((float)array2[2]);
                date = new Date((long)(((hour*60 + min)*60) + sec)*1000);
            }
        }
        return date;
    }
    
    public JExifTag findTag(int tag)
    {
        if (m_tagArray != null)
        {
            for (JExifTag tagItem : m_tagArray)
            {
                if (tagItem.getTag() == tag)
                    return tagItem;
            }
        }
        return null;
    }

    public int getCount()
    {
    	return m_count;
    }
    
    public boolean read(RandomAccessFile file, boolean bIntel, long tiffOffset)
    {
        boolean flag = false;
        if (readIFDTag(file, bIntel, tiffOffset, JExifTag.TAGMASK_EXIF))
        {
            flag = true;
            try
            {
                byte[] buf = new byte[4];
                file.read(buf);
                m_nextIfdOffset = JDataFormatHelper.buf4ToLong(buf, 0, bIntel);
                for (JExifTag tag : m_tagArray)
                {
                    if (tag.getTag() == JExifTag.EXIFTAG_TAG_EXIFIFD)
                    {
                        file.seek(tiffOffset + tag.getValueOffset());
                        JIfd exifIfd = new JIfd();
                        exifIfd.readIFDTag(file, bIntel, tiffOffset, JExifTag.TAGMASK_EXIF);
                        m_tagArray.addAll(exifIfd.m_tagArray);
                        break;
                    }
                }
                for (JExifTag tag : m_tagArray)
                {
                    if (tag.getTag() == JExifTag.EXIFTAG_TAG_GPSIFD)
                    {
                        file.seek(tiffOffset + tag.getValueOffset());
                        JIfd exifIfd = new JIfd();
                        exifIfd.readIFDTag(file, bIntel, tiffOffset, JExifTag.TAGMASK_GPS);
                        m_tagArray.addAll(exifIfd.m_tagArray);
                        break;
                    }
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return flag;
    }
    public boolean readIFDTag(RandomAccessFile file, boolean bIntel, long tiffOffset, int tagMask)
    {
        boolean flag = false;
        try
        {
            m_bIsIntel = bIntel;
            m_offset = file.getFilePointer();
            byte[] buf = new byte[2];
            file.read(buf, 0, 2);
            m_count = JDataFormatHelper.buf2ToInt(buf, 0, bIntel);
            m_tagArray = new ArrayList<JExifTag>();
            int i;
            for (i=0; i<m_count; i++)
            {
                JExifTag exifTag = new JExifTag(tagMask);
                if (exifTag.read(file, bIntel, tiffOffset))
                {
                    m_tagArray.add(exifTag);
//                    if (Main.m_settings.isDebug())
//                    {
//                        System.err.println(exifTag.getTag());
//                        boolean _flag = false;
//                        if (JExifTag.isTagUsedByGeneric(exifTag.getTag()))
//                            _flag = true;
//                        else
//                        {
//                            for (int _i=0; _i<JExifDataModel.m_colTag.length; _i++)
//                            {
//                                if (JExifDataModel.m_colTag[_i] == exifTag.getTag())
//                                {
//                                    _flag = true;
//                                    break;
//                                }
//                            }
//                        }
//                        if (!_flag)
//                            System.err.println("!!! UNKOWN TAG FOUND !!!");
//                    }
                }
            }
            flag = true;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return flag;
    }
    
    public boolean updateTag(RandomAccessFile file, int tag, int value)
    {
        boolean flag = false;
        try
        {
            JExifTag exifTag = findTag(tag);
            if (exifTag != null)
            {
                long offset = exifTag.getFileOffset();
                assert offset != 0 && exifTag.getType() == 3;
                if (offset != 0 && exifTag.getType() == 3)
                {
                    file.seek(offset);
                    byte[] buf = new byte[4];
                    JDataFormatHelper.IntToBuf4(value, buf, 0, m_bIsIntel);
                    file.write(buf);
                    flag = true;
                }
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return flag;
    }
}
