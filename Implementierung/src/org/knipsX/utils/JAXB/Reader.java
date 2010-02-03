package org.knipsX.utils.JAXB;

public class Reader {
	
	 
     Unmarshaller clyde = new Unmarshaller();
     JABElement<T> element = clyde.unmarshal(JAXBElement<T>, new File( "project.xml"));


}
