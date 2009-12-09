/*
 *  JDataFormatHelper.java
 *
 *  Created on 7. April 2006, 20:07
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


package org.studien.exifAdapter.jExifViewer.shared.dataformathelper;

/**
 *
 * @author reiner
 */

public class JDataFormatHelper
{
	
    /** Creates a new instance of JDataFormatHelper */
    public JDataFormatHelper()
    {
    }
	
    public static int byteToInt(byte b)
    {
        return ((int)(b & 127) + (int)(b & 128));
    }

    public static long byteToLong(byte b)
    {
        return ((long)(b & 127) + (long)(b & 128));
    }
	
    public static int buf4ToInt(byte [] buf, int offset, boolean bIntel) // int
    {
        assert buf.length >= offset + 4;
        return bIntel ? (byteToInt(buf[offset]) + (byteToInt(buf[offset + 1]) << 8) + (byteToInt(buf[offset + 2]) << 16) + (byteToInt(buf[offset + 3]) << 24)) : 
            (byteToInt(buf[offset+3]) + (byteToInt(buf[offset + 2]) << 8) + (byteToInt(buf[offset + 1]) << 16) + (byteToInt(buf[offset]) << 24));
    }

    public static int buf4ToIntSigned(byte [] buf, int offset, boolean bIntel) // int
    {
        assert buf.length >= offset + 4;
        long i = bIntel ? (byteToInt(buf[offset]) + (byteToInt(buf[offset + 1]) << 8) + (byteToInt(buf[offset + 2]) << 16) + (byteToInt((byte)(0x7F & buf[offset + 3])) << 24)) :
            (byteToInt(buf[offset + 3]) + (byteToInt(buf[offset + 2]) << 8) + (byteToInt(buf[offset + 1]) << 16) + (byteToInt((byte)(0x7F & buf[offset])) << 24));
        if (((bIntel ? buf[offset + 3] : buf[offset]) & 0x80) != 0)
            i -= ((long)1)<<31;
        return (int)i;
    }

    public static int buf2ToInt(byte [] buf, int offset, boolean bIntel) // WORD
    {
        assert buf.length >= offset + 2;
        return bIntel ? (byteToInt(buf[offset]) + (byteToInt(buf[offset + 1]) << 8)) : (byteToInt(buf[offset+1]) + (byteToInt(buf[offset]) << 8));
    }

    public static int buf2ToIntBE(byte [] buf, int offset) // Big endian
    {
        assert buf.length >= offset + 2;
        return (byteToInt(buf[offset]) << 8) + byteToInt(buf[offset + 1]);
    }

    public static short buf2ToShortBE(byte [] buf, int offset) // Big endian
    {
        assert buf.length >= offset + 2;
        return (short)((byteToInt(buf[offset]) << 8) + byteToInt(buf[offset + 1]));
    }

    public static int buf4ToIntBE(byte [] buf, int offset) // Big endian
    {
        assert buf.length >= offset + 4;
        return (byteToInt(buf[offset]) << 24) +( byteToInt(buf[offset + 1]) << 16) + (byteToInt(buf[offset + 1]) << 16) + (byteToInt(buf[offset + 2]) << 8) + byteToInt(buf[offset + 3]);
    }

    public static long buf4ToLong(byte [] buf, int offset, boolean bIntel) // DWORD
    {
        assert buf.length >= offset + 4;
        return bIntel ? (byteToLong(buf[offset]) + (byteToLong(buf[offset + 1]) << 8) + (byteToLong(buf[offset + 2]) << 16) + (byteToLong(buf[offset + 3]) << 24)) : 
            (byteToLong(buf[offset + 3]) + (byteToLong(buf[offset + 2]) << 8) + (byteToLong(buf[offset + 1]) << 16) + (byteToLong(buf[offset]) << 24));
    }

    public static long buf4ToLongSigned(byte [] buf, int offset) // DWORD
    {
        assert buf.length >= offset + 4;
        long l = byteToLong(buf[offset]) + (byteToLong(buf[offset + 1]) << 8) + (byteToLong(buf[offset + 2]) << 16) + (byteToLong((byte)(0x7F & buf[offset + 3])) << 24);
        if ((buf[offset + 3] & 0x80) != 0) l-= ((long)1)<<32;
        return l;
    }

    public static short buf1ToShort(byte [] buf, int offset) // Byte
    {
        assert buf.length >= offset + 1;
        return (short)byteToInt(buf[offset]);
    }
	
    //

    public static void IntToBuf2(int val, byte [] buf, int offset, boolean bIntel) // WORD
    {
        assert buf.length >= offset + 2;
        buf[offset + (bIntel ? 0 : 1)] = (byte)(val & 0xFF);
        buf[offset + (bIntel ? 1 : 0)] = (byte)((val>>8) & 0xFF);
    }

    public static void IntToBuf2BE(int val, byte [] buf, int offset) // Big endian
    {
        assert buf.length >= offset + 2;
        buf[offset] = (byte)((val>>8) & 0xFF);
        buf[offset + 1] = (byte)(val & 0xFF);
    }

    public static void IntToBuf4(int val, byte [] buf, int offset, boolean bIntel)
    {
        assert buf.length >= offset + 4;
        buf[offset + (bIntel ? 0 : 3)] = (byte)(val & 0xFF);
        buf[offset + (bIntel ? 1 : 2)] = (byte)((val>>8) & 0xFF);
        buf[offset + (bIntel ? 2 : 1)] = (byte)((val>>16) & 0xFF);
        buf[offset + (bIntel ? 3 : 0)] = (byte)((val>>24) & 0xFF);
    }

    public static void LongToBuf4(long val, byte [] buf, int offset) // WORD
    {
	    assert buf.length >= offset + 4;
	    buf[offset] = (byte)(val & 0xFF);
	    buf[offset + 1] = (byte)((val>>8) & 0xFF);
	    buf[offset + 2] = (byte)((val>>16) & 0xFF);
	    buf[offset + 3] = (byte)((val>>24) & 0xFF);
    }

    public static void ShortToBuf1(short val, byte [] buf, int offset)
    {
	    assert buf.length >= offset + 1;
	    buf[offset] = (byte)(val & 0xFF);
    }

    public static void IntSignedToBuf4(int val, byte [] buf, int offset) // int
    {
	    assert buf.length >= offset + 4;
	    buf[offset] = (byte)(val & 0xFF);
	    buf[offset + 1] = (byte)((val>>8) & 0xFF);
	    buf[offset + 2] = (byte)((val>>16) & 0xFF);
	    buf[offset + 3] = (byte)((val>>24) & 0xFF);
    }
}
