/*
 *  JExif.java
 *
 *  Created on 7. April 2006, 22:06
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


package org.studien.exifAdapter.jExifViewer;

import java.io.RandomAccessFile;
import java.io.IOException;

/**
 *
 * @author reiner
 */

public final class JExif
{
    public static final String EXIF = "Exif";
    private long m_offset = 0;

    /** Creates a new instance of JExif
     * @see <a href="http://www.exif.org/Exif2-2.PDF">Exif specification</a>
    */
    
    public JExif()
    {
    }
    
    long getOffset()
    {
    	return m_offset;
    }
    
    boolean read(RandomAccessFile file)
    {
        boolean flag = false;
        try
        {
            m_offset = file.getFilePointer();
            byte[] buf = new byte[4];
            file.read(buf);
            String id = new String(buf, 0, 4);
            if (id.equals(EXIF))
            {
                file.skipBytes(2);
                flag = true;
            }
        }
        catch (IOException ex)
        {
        }
        return flag;
    }
}
