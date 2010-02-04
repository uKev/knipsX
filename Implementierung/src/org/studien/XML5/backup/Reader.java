package org.knipsX.utils.JAXB;

import java.io.*;

import org.knipsX.utils.JAXB.parser.*;


public class Reader {
	
	 
     Unmarshaller clyde = new Unmarshaller();
     
     File f = new File("output.xml");
     //InputStream in = new FileInputStream(f);
     
     //InputStream file22 = new FileInputStream("project.xml");
     
     //InputStream is = new BufferedInputStream( new FileInputStream("image.xml"));
     
     Picture abc1 = clyde.unmarshal( Picture.class, null);
     
     //Object abc = new Picture();
     //Object element = clyde.unmarshal(abc, new File( "project.xml"));
   

}



// Inputstream input = new File( "project.xml" );