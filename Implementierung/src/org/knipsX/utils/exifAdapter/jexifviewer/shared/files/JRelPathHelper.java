/*
 *  JRelPathHelper.java
 *
 *  Created on 26. Februar 2005, 19:48
 *
 *  Copyright (C) 26. Februar 2005  <Reiner>

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

/**
 *
 * @author Reiner
 */
public class JRelPathHelper
{
	
	/** Creates a new instance of JRelPathHelper */
	public JRelPathHelper()
	{
	}
	
	public static String getAbsolutPath(File refFile, String relPath)
	{
		return getAbsolutPath(refFile.getPath(), relPath);
	}

	public static String getAbsolutPath(String refPath, String relPath)
	{
		String str;
		File file = new File(relPath);
		if (file.isAbsolute())
			str = relPath;
		else 
		{
			str = JPathHelper.getFolder(refPath);
			while(relPath.length() > 3 && relPath.substring(0, 2).equals(".."))
			{
				str = JPathHelper.getFolder(str);
				relPath = relPath.substring(3, relPath.length());
			}
			str = JPathHelper.addSeparator(str);
			str += relPath;
		}
		return str;
	}

	public static String getRelativePath(File refFile, String fullPath)
	{
		return getRelativePath(refFile.getPath(), fullPath);
	}
	
	public static String getRelativePath(String refPath, String fullPath)
	{
		StringBuilder stringBuilder = new StringBuilder();
		String[] refArray = refPath.split("\\" + File.separator);
		String[] fullArray = fullPath.split("\\" + File.separator);
		int index = 0;
		while(index < Math.min(refArray.length,  fullArray.length))
		{
			if (!refArray[index].equals(fullArray[index]))
				break;
			else index++;
		}
		if (index != 0)
		{
			int h = index;
			while(index++ < refArray.length - 1)
			{
				stringBuilder.append(".." + File.separator);
			}
			while(h < fullArray.length)
			{
				JPathHelper.addSeparator(stringBuilder);
				stringBuilder.append(fullArray[h]);
				h++;
			}
		}
		else  stringBuilder.append(fullPath);
		return stringBuilder.toString();
	}
}
