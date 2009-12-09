/*
 *  JPathHelper.java
 *
 *  Created on 7. Januar 2005, 17:14
 *
 *  Copyright (C) 7. Januar 2005  <Reiner>

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


package org.studien.exifAdapter.jexifviewer.shared.files;

import java.io.File;

/**
 *
 * @author  Reiner
 */
public class JPathHelper
{
	
	/** Creates a new instance of JPathHelper */
	public JPathHelper()
	{
	}

	public static String getFileExtension(File file)
	{
		return getFileExtension(file.getName());
	}

	public static String getFileExtension(String fileName)
	{
		int index = fileName.lastIndexOf('.');
		if (index >= 0)
			return fileName.substring(index+1, fileName.length());
		else return "";
	}

	public static String getBaseFileName(File file)
	{
		String name = file.getName();
		int index = name.lastIndexOf('.');
		if (index >= 0)
			return name.substring(0, index);
		else return name;
	}

	public static String getBaseFileName(String fileName)
	{
		return getBaseFileName(new File(fileName));
	}
	
	public static String getFolder(File file)
	{
		String str="";
        File parent = file.getParentFile();
        if (parent != null)
            str = parent.getAbsolutePath();
        return str;
	}

	public static String getFolder(String fileName)
	{
		return getFolder(new File(fileName));
	}

	public static String getSubFolder(File file)
	{
		String str="";
        File parent = file.getParentFile();
        if (parent != null)
            str = parent.getName();
		return str;
	}

	public static String getSubFolder(String fileName)
	{
		return getSubFolder(new File(fileName));
	}

	public static void addSeparator(StringBuilder stringBuilder)
	{
		if (stringBuilder.length() != 0)
		{
			if (stringBuilder.charAt(stringBuilder.length()-1) != File.separatorChar)
				stringBuilder.append(File.separatorChar);
		}
	}

	public static String addSeparator(String str)
	{
		String nstr = new String(str);
		if (str.length() != 0)
		{
			if (str.charAt(str.length()-1) != File.separatorChar)
				nstr += File.separator;
		}
		return nstr;
	}
}
