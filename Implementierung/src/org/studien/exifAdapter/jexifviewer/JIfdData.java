/*
 *  JIfdData.java
 *
 *  Created on 11. April 2006, 18:25
 *
 *  Copyright (C) 11. April 2006  <Reiner>

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

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import java.util.Date;
import java.util.GregorianCalendar;

import jexifviewer.shared.files.JPathHelper;


/**
 *
 * @author reiner
 */

public final class JIfdData extends JIfd
{
    private String m_fileName;
    private long m_fileSize = -1;
    
    /** Creates a new instance of JIfdData */
    public JIfdData()
    {
    }

    public JIfdData(File imageFile)
    {
    	readFromFile(imageFile);
    }

    public JIfdData(String imageFile)
    {
        readFromFile(imageFile);
    }
    
    public long getFileSize()
    {
        if (m_fileSize == -1)
        {
            File file = new File(m_fileName);
            m_fileSize = file.length();
        }
        return m_fileSize;
    }

    public Date getFileDate()
    {
        File file = new File(m_fileName);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date(file.lastModified()));
        return cal.getTime();
    }

    public String getFileName()
    {
    	return JPathHelper.getBaseFileName(m_fileName);
    }

    public String getFullFileName()
    {
        return JPathHelper.getBaseFileName(m_fileName) + "." + JPathHelper.getFileExtension(m_fileName);
    }

    public String getFilePath()
    {
    	return m_fileName;
    }

    public void setFilePath(String fileName)
    {
    	m_fileName = fileName;
    }
    
    public boolean readFromFile(String imageFile)
    {
        return readFromFile(new File(imageFile));
    }
    
    public boolean readFromFile(File imageFile)
    {
        boolean flag = false;
        RandomAccessFile file = null;
        try
        {
            if (m_tagArray != null) m_tagArray.clear();
            m_fileName = imageFile.getPath();
            file = new RandomAccessFile(imageFile, "r");
            JJPEGHelper jpg = new JJPEGHelper();
            if (jpg.isJpegFile(file) && jpg.findMarker(file, JJPEGHelper.SOI) == true)
            {
                if (jpg.findMarker(file, JJPEGHelper.APP1) == true)
                {
                    JExif exif = new JExif();
                    if (exif.read(file))
                    {
                        JTiffHeader tiffHeader = new JTiffHeader();
                        if (tiffHeader.read(file))
                        {
                            file.seek(tiffHeader.getOffset() + tiffHeader.getIFDOffset());
                            flag = read(file, tiffHeader.isIntel(), tiffHeader.getOffset());
                        }
                    }
                }
            }
        }
        catch(IOException ex)
        {
        	System.out.println(ex.toString());        	
        }
        finally
        {
            try
            {
                if (file != null) file.close();
            }
            catch (IOException ex)
            {
            	System.out.println(ex.toString());
            }
        }
        return flag;
    }
}
