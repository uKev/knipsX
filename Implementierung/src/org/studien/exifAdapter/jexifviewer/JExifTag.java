/*
 *  JExifTag.java
 *
 *  Created on 8. April 2006, 18:41
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


package org.studien.exifAdapter.jExifViewer;

import java.io.RandomAccessFile;
import java.io.IOException;

import org.studien.exifAdapter.jExifViewer.shared.dataformathelper.JDataFormatHelper;


/**
 *
 * @author reiner
 */

public final class JExifTag
{
    public static final int EXIFTAG_TAG_EXIFIFD = 0x8769;
    public static final int EXIFTAG_TAG_GPSIFD = 0x8825;

    public static final int TAGMASK_GPS = 0x00010000;
    public static final int TAGMASK_EXIF = 0x00000000;

    // generics
    public static final int EXIFTAG_TAG_NAME = 0x0000;
    public static final int EXIFTAG_TAG_SIZE = 0x0001;
    public static final int EXIFTAG_TAG_RESOLUTION = 0x0002;
    public static final int EXIFTAG_TAG_FOCALPLANERESOLUTION = 0x0003;
    public static final int EXIFTAG_TAG_FILESIZE = 0x0004;
    public static final int EXIFTAG_TAG_FILEDATETIME = 0x0005;
    
    // real tags
    //public static final int EXIFTAG_TAG_CANON_SERIAL = 0x000C;
    public static final int EXIFTAG_TAG_MAKE = 0x010F;
    public static final int EXIFTAG_TAG_MODEL = 0x0110;
    public static final int EXIFTAG_TAG_ORIENTATION = 0x0112;
    public static final int EXIFTAG_TAG_XRESOLUTION = 0x011A;
    public static final int EXIFTAG_TAG_YRESOLUTION = 0x011B;
    public static final int EXIFTAG_TAG_RESOLUTIONUNIT = 0x128;
    public static final int EXIFTAG_TAG_SOFTWARE = 0x131;
    public static final int EXIFTAG_TAG_CHANGEDATETIME = 0x132;
    public static final int EXIFTAG_TAG_ARTIST = 0x13B;
    public static final int EXIFTAG_TAG_YCBCRPOSITION = 0x213;
    public static final int EXIFTAG_TAG_COPYRIGHT = 0x8298;
    public static final int EXIFTAG_TAG_DESCRIPTION = 0x010E;

    // Exif ID
    public static final int EXIFTAG_TAG_EXPOSURETIME = 0x829A;
    public static final int EXIFTAG_TAG_FNUMBER = 0x829D;
    public static final int EXIFTAG_TAG_EXPOSUREPROGRAM = 0x8822;
    public static final int EXIFTAG_TAG_ISOSPEEDRATINGS = 0x8827;
    public static final int EXIFTAG_TAG_EXIFVERSION = 0x9000;
    public static final int EXIFTAG_TAG_ORIGINALDATETIME = 0x9003;
    public static final int EXIFTAG_TAG_DIGITIZEDDATETIME = 0x9004;
    public static final int EXIFTAG_TAG_COMPONENTSCCONFIG = 0x9101;
    public static final int EXIFTAG_TAG_COMPRESSEDPITSPERPIXEL = 0x9102;
    public static final int EXIFTAG_TAG_SHUTERSPEEDVALUE = 0x9201;
    public static final int EXIFTAG_TAG_APERTUREVALUE = 0x9202;
    public static final int EXIFTAG_TAG_BRIGHTNESSVALUE = 0x9203;
    public static final int EXIFTAG_TAG_EXPOSUREBIASVALUE = 0x9204;
    public static final int EXIFTAG_TAG_MAXAPERTUREVALUE = 0x9205;
    public static final int EXIFTAG_TAG_METERINGMODE = 0x9207;
    public static final int EXIFTAG_TAG_LIGHTSOURCE = 0x9208;
    public static final int EXIFTAG_TAG_FLASH = 0x9209;
    public static final int EXIFTAG_TAG_FOCALLENGTH = 0x920A;
    public static final int EXIFTAG_TAG_MAKERNOTE = 0x927C;
    public static final int EXIFTAG_TAG_USERCOMMENT = 0x9286;
    public static final int EXIFTAG_TAG_COLORSPACE = 0xA001;
    public static final int EXIFTAG_TAG_PIXELXDIMENSION = 0xA002;
    public static final int EXIFTAG_TAG_PIXELYDIMENSION = 0xA003;
    public static final int EXIFTAG_TAG_FOCALPLANEXRESOLUTION = 0xA20E;
    public static final int EXIFTAG_TAG_FOCALPLANEYRESOLUTION = 0xA20F;
    public static final int EXIFTAG_TAG_FOCALPLANERESOLUTIONUNIT = 0xA210;
    public static final int EXIFTAG_TAG_SENSINGMODE = 0xA217;
    public static final int EXIFTAG_TAG_FILESOURCE = 0xA300;
    public static final int EXIFTAG_TAG_SCENETYPE = 0xA301;
    public static final int EXIFTAG_TAG_CUSTOMRENDERED = 0xA401;
    public static final int EXIFTAG_TAG_EXPOSUREMODE = 0xA402;
    public static final int EXIFTAG_TAG_WHITEBALANCE = 0xA403;
    public static final int EXIFTAG_TAG_DIGITALZOOMRATIO = 0xA404;
    public static final int EXIFTAG_TAG_FOCALLENGTH35MM = 0xA405;
    public static final int EXIFTAG_TAG_SCENECAPTURETYPE = 0xA406;
    public static final int EXIFTAG_TAG_GAINCONTROL = 0xA407;
    public static final int EXIFTAG_TAG_CONTRAST = 0xA408;
    public static final int EXIFTAG_TAG_SATURATION = 0xA409;
    public static final int EXIFTAG_TAG_SHARPNESS = 0xA40A;
    public static final int EXIFTAG_TAG_SUBJECTDISTANCERANGE = 0xA40C;

    public static final int GPSTAG_TAG_VERSIONID = 0x0000 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_LATITUDEREF = 0x0001 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_LATITUDE = 0x0002 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_LONGITUDEREF = 0x0003 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_LONGITUDE = 0x0004 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_ALTITUDEREF = 0x0005 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_ALTITUDE = 0x0006 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_TIMESTAMP = 0x0007 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_SATELLITES = 0x0008 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_STATUS = 0x0009 | TAGMASK_GPS;
    public static final int GPSTAG_TAG_MEASUREMODE = 0x000A | TAGMASK_GPS;
    public static final int GPSTAG_TAG_DOP = 0x000B | TAGMASK_GPS;

    // generics
    public static final int GPSTAG_TAG_MAPLINK = 0x1000 | TAGMASK_GPS;

 
    private int m_tag;
    private int m_tagMask;
    private short m_type;
    private long m_count;
    private long m_valueOffset;
    
    private int m_fileOffset = 0; // in the file from file start

    // Data
    private String m_string;
    private int m_integer1;
    private int m_integer2;
    private long m_long1;
    private long m_long2;
    private byte [] m_byteArray;
    private long [] m_longArray1;
    private long [] m_longArray2;
    
    /** Creates a new instance of JExifTag */
    public JExifTag(int tagMask)
    {
        m_tagMask = tagMask;
    }
    
    public static boolean isGenericTag(int tag)
    {
        return  (tag == EXIFTAG_TAG_NAME || tag ==  EXIFTAG_TAG_SIZE || tag == EXIFTAG_TAG_RESOLUTION || tag == EXIFTAG_TAG_FOCALPLANERESOLUTION
            || tag == EXIFTAG_TAG_FILESIZE || tag == EXIFTAG_TAG_FILEDATETIME || tag == GPSTAG_TAG_MAPLINK);
    }

    public static boolean isTagUsedByGeneric(int tag)
    {
        if (tag == EXIFTAG_TAG_FOCALPLANEXRESOLUTION || tag == EXIFTAG_TAG_FOCALPLANEYRESOLUTION || tag == EXIFTAG_TAG_FOCALPLANERESOLUTIONUNIT)
            return true;
        else if (tag == EXIFTAG_TAG_XRESOLUTION || tag == EXIFTAG_TAG_YRESOLUTION || tag == EXIFTAG_TAG_RESOLUTIONUNIT)
            return true;
        else if (tag == GPSTAG_TAG_MAPLINK)
            return true;
        else return false;
    }

    public static boolean isGPSTag(int tag)
    {
        return ((tag & TAGMASK_GPS) != 0 ? true : false);
    }
    
    public long getFileOffset()
    {
    	return m_fileOffset;
    }
    
    public String getString()
    {
        assert m_type == 2 || (m_type == 7 && m_tag == EXIFTAG_TAG_EXIFVERSION) || (m_type == 7 && m_tag == EXIFTAG_TAG_COMPONENTSCCONFIG);
        return m_string;
    }
	
    public int getInteger1()
    {
        assert m_type == 1 || m_type == 3 || m_type == 5 || m_type == 9 || m_type == 10 || (m_type == 7 && m_tag == GPSTAG_TAG_ALTITUDEREF);
        return (m_type == 5 ? (int)m_long1 : m_integer1);
    }

    public int getInteger()
    {
        assert (m_type == 3 || m_type == 4 || m_type == 5);
        return m_type == 3 ? m_integer1 : (int)m_long1;
    }

    public int getInteger2()
    {
        assert (m_type == 10 || m_type == 5);
        return m_type == 10 ? m_integer2 : (int)m_long2;
    }

    public long getLong1()
    {
        assert m_type == 5 || m_type == 4 || m_type == 10;
        if (m_type == 5 || m_type == 4)
            return m_long1;
        else if (m_type == 10)
            return m_integer1;
        else
        {
            return 0;
        }
    }

    public long getLong2()
    {
        assert m_type == 5 || m_type == 10;
        if (m_type == 5)
            return m_long2;
        else if (m_type == 10)
            return m_integer2;
        else
            return 0;
    }

    public int getTag()
    {
    	return m_tag;
    }

    public byte[] getByteArray()
    {
    	return m_byteArray;
    }

    public long[] getLongArray1()
    {
    	return m_longArray1;
    }
    public long[] getLongArray2()
    {
    	return m_longArray2;
    }

    public int getType()
    {
        return m_type;
    }

    public long getValueOffset()
    {
    	return m_valueOffset;
    }
	
    @Override
    public String toString()
    {
        String str = null;
        switch(m_type)
        {   
            case 1:
                str = String.format("%1$d", m_integer1);
                break;
            case 2: // ASCII
                str = m_string;
                break;
            case 3:
                str = String.format("%1$d", m_integer1);
                break;
            case 4:
                str = String.format("%1$d", m_long1);
                break;
            case 5:
                str = String.format("%1$d/%2$d", m_long1, m_long2);
                break;
            case 7:
                str = String.format("---");
                break;
            case 9:
                str = String.format("%1d", m_integer1);
                break;
            case 10:
                str = String.format("%1d/%2d", m_integer1, m_integer2);
                break;
        }
        return str;
    }
    
    public boolean read(RandomAccessFile file, boolean bIntel, long tiffOffset)
    {
        boolean flag = false;
        try
        {
            m_fileOffset = (int)file.getFilePointer() + 8;

            byte[] buf = new byte[4];
            file.read(buf, 0, 2);
            m_tag = JDataFormatHelper.buf2ToInt(buf, 0, bIntel);
            m_tag = m_tag | m_tagMask;

            file.read(buf, 0, 2);
            m_type = (short)JDataFormatHelper.buf2ToInt(buf, 0, bIntel);

            file.read(buf, 0, 4);
            m_count = JDataFormatHelper.buf4ToLong(buf, 0, bIntel);

            file.read(buf, 0, 4);
            m_valueOffset = JDataFormatHelper.buf4ToLong(buf, 0, bIntel);

            if (m_tag == JExifTag.EXIFTAG_TAG_SCENETYPE || m_tag == JExifTag.EXIFTAG_TAG_FILESOURCE)
                m_type = 1;

            long pos;
            switch(m_type)
            {
                case 1: // BYTE (8 bit unsigned)
                    if (m_count == 1)
                    {
                        if (bIntel)
                            m_integer1 = (int)(m_valueOffset & 0xFF);
                        else
                            m_integer1 = (int)((m_valueOffset & 0xFF000000) >> 24);
                    }
                    else if (m_count <= 4)
                    {
                        m_byteArray = new byte[(int)m_count];
                        for (int i=0; i<m_count; i++)
                        {
                            if (bIntel)
                                m_byteArray[i] = (byte)((m_valueOffset & (0x000000FF << (i*8))) >> (i*8));
                            else
                                m_byteArray[i] = (byte)((m_valueOffset & (0xFF000000 >> (i*8))) >> ((3-i)*8));
                        }
                    }
                    break;
                case 2: // ASCII
                    pos = file.getFilePointer();
                    if (m_count <= 4) // GPS data uses this !
                    {
                        StringBuilder strBuilder = new StringBuilder();
                        int count = 0;
                        while(count++ < m_count)
                        {
                            char c;
                            if (bIntel)
                                c = (char)((byte)((m_valueOffset & (0x000000FF << ((count-1)*8))) >> ((count-1)*8)));
                            else
                                c = (char)((byte)((m_valueOffset & (0x000000FF << ((4-count)*8))) >> ((4-count)*8)));
                            if (c != '\0')
                                strBuilder.append((char)c);
                            else break;
                        }
                        m_string = strBuilder.toString();
                    }
                    else
                    {
                        file.seek(tiffOffset + m_valueOffset);
                        buf = new byte[(int)(m_count)];
                        file.read(buf);
                        m_string = new String(buf);
                        if (m_string.length() < m_count)
                        {
                            String str = new String(buf, m_string.length() + 1, (int)(m_count - m_string.length() - 1));
                            m_string += "\t";
                            m_string += str;
                        }
                    }
                    m_string = m_string.trim();
                    file.seek(pos);
                    break;
                case 3: // SHORT (2byte unsigned)
                    if (bIntel)
                        m_integer1 = (short)(m_valueOffset & 0x0000FFFF);
                    else
                        m_integer1 = (short)((m_valueOffset & 0xFFFF0000) >> 16);
                    break;
                case 4: // LONG (4byte unsigned)
                    m_long1 = m_valueOffset;
                    break;
                case 5: // RATIONAL (4byte unsigned)
                    pos = file.getFilePointer();
                    file.seek(tiffOffset + m_valueOffset);
                    buf = new byte[(int)(m_count * 8)];
                    file.read(buf);
                     if (m_count == 1)
                    {
                         m_long1 = JDataFormatHelper.buf4ToLong(buf, 0, bIntel);
                         m_long2 = JDataFormatHelper.buf4ToLong(buf, 4, bIntel);
                    }
                    else
                    {
                        m_longArray1 = new long[(int)m_count];
                        m_longArray2 = new long[(int)m_count];
                        for (int i=0; i<m_count; i++)
                        {
                            m_longArray1[i] = JDataFormatHelper.buf4ToLong(buf, i*8, bIntel);
                            m_longArray2[i] = JDataFormatHelper.buf4ToLong(buf, i*8+4, bIntel);
                        }
                    }
                    file.seek(pos);
                    break;
                case 7: // UNDEFINED
                    if (m_tag == EXIFTAG_TAG_EXIFVERSION)
                    {
                        //assert m_count == 4;
                        pos = file.getFilePointer();
                        file.seek(pos - 4);
                        buf = new byte[4];
                        file.read(buf);
                        m_string = new String(buf);
                    }
                    else if (m_tag == EXIFTAG_TAG_COMPONENTSCCONFIG)
                    {
                        assert m_count == 4;
                        pos = file.getFilePointer();
                        file.seek(pos - 4);
                        buf = new byte[4];
                        file.read(buf);
                        m_string = "";
                        for (int i=0;i<4; i++)
                        {
                            if (buf[i] == 0)
                                m_string += "";
                            else if (buf[i] == 1)
                                m_string += "Y";
                            else if (buf[i] == 2)
                                m_string += "Cb";
                            else if (buf[i] == 3)
                                m_string += "Cr";
                            else if (buf[i] == 4)
                                m_string += "R";
                            else if (buf[i] == 5)
                                m_string += "G";
                            else if (buf[i] == 6)
                                m_string += "B";
                            else m_string += "?";
                        }
                    }
                    else if (m_tag == EXIFTAG_TAG_USERCOMMENT)
                    {
                        pos = file.getFilePointer();
                        file.seek(tiffOffset + m_valueOffset);
                        buf = new byte[(int)(m_count)];
                        file.read(buf);
                        int encSize = Math.min(8, buf.length);
                        String encStr = new String(buf, 0, encSize);
                        file.seek(pos);
                        if (m_count > encSize)
                        {
                            m_string = new String(buf, encSize, (int)(m_count - encSize));
                            if (m_string.charAt(0) == '\0')
                                m_string = null;
                        }
                        else m_string = null;
                        if (m_string != null)
                            m_string = m_string.trim();
                    }
                    else if (m_tag == GPSTAG_TAG_ALTITUDEREF)
                    {
                        if (m_count == 1)
                        {
                            pos = file.getFilePointer();
                            file.seek(pos - 1);
                            buf = new byte[1];
                            file.read(buf);
                            m_integer1 = (int)buf[0];
                        }
                    }

                    break;
                case 9: // SLONG (4 byte signed)
                    m_integer1 = (int)(m_valueOffset & 0x7FFFFFFF);
                    if ((m_valueOffset & 0x80000000) != 0) m_integer1 -= ((long)1)<<32;
                    break;
                case 10: // SRATIONAL (4 byte signed)
                    pos = file.getFilePointer();
                    file.seek(tiffOffset + m_valueOffset);
                    buf = new byte[(int)(m_count * 8)];
                    file.read(buf);
                    m_integer1 = JDataFormatHelper.buf4ToIntSigned(buf, 0, bIntel);
                    m_integer2 = JDataFormatHelper.buf4ToIntSigned(buf, 4, bIntel);
                    file.seek(pos);
                    break;
            }
            flag = true;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return flag;
    }
}
