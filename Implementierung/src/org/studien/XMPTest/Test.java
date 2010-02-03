package org.studien.XMPTest;

import java.io.File;
import java.io.IOException;

import com.adobe.xmp.XMPException;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.xmp.XmpDirectory;
import com.drew.metadata.xmp.XmpReader;

public class Test {

    /**
     * 
     * 
     * @param args
     * @throws IOException
     * @throws XMPException
     * @throws JpegProcessingException 
     */
    public static void main(String[] args) throws IOException, XMPException, JpegProcessingException {
        XmpReader bla;
        try {
            bla = new XmpReader(new File("/home/david/Bilder/Fotos/2008/01/25/CIMG3145.JPG"));
            XmpDirectory dir = (XmpDirectory) bla.extract().getDirectory(new XmpDirectory().getClass());
            String[] array = dir.getStringArray(XmpDirectory.TAG_KEYWORDS);
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        //System.out.println(dir.getObject(XmpDirectory.TAG_KEYWORDS));
        

        
    }

}
