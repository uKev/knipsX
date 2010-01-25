/*
 * JFileHelper.java
 *
 * Created on 28. Mai 2006, 13:06
 *
 *  Copyright (C) 28. Mai 2006  <reiner>
 
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


package org.knipsX.utils.exifAdapter.jexifviewer.shared.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.nio.channels.FileChannel;

import java.io.IOException;

/**
 *
 * @author reiner
 */

public class JFileHelper
{
    
    /** Creates a new instance of JFileHelper */
    public JFileHelper()
    {
    }
    
    public static boolean copyFile(File src, File target)
    {
        boolean flag = false;
        FileInputStream in = null;
        FileOutputStream out = null;
        try
        {
            in = new FileInputStream(src);
            out = new FileOutputStream(target);
            FileChannel inCh = in.getChannel();
            FileChannel outCh = out.getChannel();
            if (outCh.transferFrom(inCh, 0, src.length()) == src.length())
            {
                // adjust timestamp
                target.setLastModified(src.lastModified());
                flag = true;
            }
        }
        catch(IOException ex)
        {}
        finally
        {
            try
            {
                if (in != null) in.close();
            }
            catch (IOException ex)
            {}
            try
            {
                if (out != null) out.close();
            }
            catch (IOException ex)
            {}
        }
        return flag;
    }
}
