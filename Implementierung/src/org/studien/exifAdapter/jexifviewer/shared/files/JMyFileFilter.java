/*
 *  JMyFileFilter.java
 *
 *  Created on 7. Januar 2005, 10:01
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


package org.studien.exifAdapter.jExifViewer.shared.files;

import java.io.File;
import javax.swing.filechooser.FileFilter;

import java.util.ArrayList;

/**
 *
 * @author  Reiner
 */
public class JMyFileFilter extends FileFilter
{
	private ArrayList<String> m_extList;
	private String m_description;
	
	/** Creates a new instance of JMyFileFilter */
	public JMyFileFilter()
	{
		m_extList = new ArrayList<String>();
		m_description = new String();
	}
	
	public void addExtension(String ext)
	{
		m_extList.add(ext.toLowerCase());
	}
	
	public boolean accept(File file)
	{
		boolean flag = false;
		if (file.isDirectory()) flag = true;
		else
		{
			String name = file.getName();
			int index = name.lastIndexOf('.');
			if (index >= 0)
			{
				String ext = name.substring(index+1, name.length());
				if (m_extList.contains(ext.toLowerCase()))
					flag = true;
			}
		}
		return flag;
	}
	
	public String getDescription()
	{
		return m_description;
	}

	public void setDescription(String description)
	{
		m_description = description;
	}
}
