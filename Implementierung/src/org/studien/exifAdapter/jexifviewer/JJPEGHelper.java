/*
 *  JJPEGHelper.java
 *
 *  Created on 7. April 2006, 19:33
 *
 *  Copyright (C) 7. April 2006  <Reiner>

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


/**
 *
 * @author reiner
 */

/*
 * JJPEGHelper.java
 *
 * Created on 24. Juli 2005, 22:54
 */


import java.io.RandomAccessFile;
import java.io.IOException;

import jexifviewer.shared.dataformathelper.JDataFormatHelper;


/**
 *
 * @author reiner
 */

public final class JJPEGHelper
{
    /*
     http://de.wikipedia.org/wiki/JPEG	
     */
    
    public static final int SOI = 0xFFD8;
    public static final int JFIF = 0xFFE0;
    public static final int COM = 0xFFFE;
    public static final int COP = 0xFFEE; // copyright
    public static final int APP1 = 0xFFE1;
    public static final int APP2 = 0xFFE2;
    public static final int DQT = 0xFFDB;
    public static final int DHT = 0xFFC4;
    public static final int DRI = 0xFFDD;
    public static final int SOF = 0xFFC0;
    public static final int SOS = 0xFFDA;
    public static final int EOI = 0xFFD9;

    private int m_size;
    private int m_offset;
	
    /** Creates a new instance of JJPEGHelper */
    public int getSize()
    {
	    return m_size;
    }

    public int getOffset()
    {
	    return m_offset;
    }

    public JJPEGHelper()
    {
    }

    public boolean isJpegFile(RandomAccessFile file)
    {
        boolean flag = false;
        byte[] buf = new byte[2];
        try
        {
            long pos = file.getFilePointer();
            file.seek(0);
            file.read(buf);
            if (isMarker(buf, SOI))
            flag = true;
            file.seek(pos);
        }
        catch (IOException ex)
        {
        }
        return flag;
    }

    public boolean findMarker(RandomAccessFile file, int marker)
    {
        byte[] buf = new byte[2];
        boolean flag = false;
        int count = 0;
        int size = 0;
        try
        {
            while(true)
            {
                file.read(buf);
                if (isMarker(buf, marker))
                {
                    if (isMarker(buf, SOI))
                        size = 2;
                    else if (isMarker(buf, EOI))
                        size = 2;
                    else
                    {
                        file.read(buf);
                        size = JDataFormatHelper.buf2ToIntBE(buf, 0);
                    }
                    flag = true;
                    break;
                }
                else if (isMarker(buf, SOI))
                    count += 2;
                else if (isMarker(buf, EOI))
                    count += 2;
                else if (isMarker(buf, SOS))
                {
                    // don't look behind the data
                    flag = false;
                    break;
                }
                else
                {
                    if (buf[0] != -1) return false; // here is something wrong !!!!
                    file.read(buf);
                    size = JDataFormatHelper.buf2ToIntBE(buf, 0);
                    file.skipBytes(size - 2);
                    count += (size + 2);
                }
            }
        }
        catch (IOException ex)
        {
        }
        if (flag)
        {
            m_size = size;
            m_offset = count;
        }
        return flag;
    }

    protected boolean isMarker(byte[] buf, int marker)
    {
	    if ((JDataFormatHelper.byteToInt(buf[0]) == ((marker >> 8) & 0xFF)) && (JDataFormatHelper.byteToInt(buf[1]) == (marker & 0xFF)))
		    return true;
	    else return false;
    }
}
