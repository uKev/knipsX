package org.knipsX.utils.JAXB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.knipsX.utils.JAXB.parser.*;

public class Reader {


	public Reader() {
		//empty constructor
	}

public void get()
	throws JAXBException {
	
	JAXBContext jc = JAXBContext.newInstance( "org.knipsX.utils.JAXB.parser" );
	Unmarshaller u = jc.createUnmarshaller();
	
	try {
		Project po =
			  (Project)u.unmarshal(
			    new FileInputStream( "project.xml" ) );

		String name = po.getName();
		System.out.println( "Name of the project: " + name );
		BigInteger id = po.getId();
		System.out.println( "Project ID: " + id );
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
			
	}

}
