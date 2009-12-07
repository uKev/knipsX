/*
 *  JTiffHeader.java
 *
 *  Created on 8. April 2006, 17:40
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

import jexifviewer.shared.dataformathelper.JDataFormatHelper;


/**
 *
 * @author reiner
 */

public final class JTiffHeader
{
    private int m_ifdOffset = 0;
    private long m_offset = 0;
    private boolean m_bIntel = true;
    
    public long getOffset()
    {
    	return m_offset;
    }

    public int getIFDOffset()
    {
        return m_ifdOffset;
    }

    public boolean isIntel()
    {
    	return m_bIntel;
    }
    
    /**
    * reads the TIFF header from a file
     * @param file the RandomAccessFile
     * @return true is success elese false
    */
    
    public boolean read(RandomAccessFile file)
    {
        boolean flag = false;
        try
        {
            m_offset = file.getFilePointer();
            byte[] buf = new byte[4];
            file.read(buf, 0, 2);
            if (buf[0] == buf[1] && (buf[0] == 0x49 || buf[0] == 0x4D))
            {
                m_bIntel = buf[0] == 0x49;
                file.read(buf, 0, 2);
                if (JDataFormatHelper.buf2ToInt(buf, 0, isIntel()) == 0x002A)
                {
                    file.read(buf, 0, 4);
                    m_ifdOffset = JDataFormatHelper.buf4ToInt(buf, 0, isIntel());
                    flag = true;
                }
//                else if (Main.m_settings.isDebug()) System.err.println("TIFF header byte order check failed!");
            }
//            else if (Main.m_settings.isDebug())  System.err.println("Invalid byte order in TIFF header!");
        }
        catch (IOException ex)
        {
        }
        return flag;
    }
    
 
    /** Creates a new instance of JTiffHeader */
    public JTiffHeader()
    {
    }
}
