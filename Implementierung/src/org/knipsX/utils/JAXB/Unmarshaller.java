package org.knipsX.utils.JAXB;

import java.util.*;
import javax.xml.bind.*;
import parser.*;
import java.io.*;



public class Unmarshaller {


	public Unmarshaller() {
		//empty constructor
	}
	/*
public void unmarshal() {
        try {

                JAXBContext jc = JAXBContext.newInstance( "hello" );
                Unmarshaller u = jc.createUnmarshaller();
                jc.createUnmarshaller();
	 */              
public <T> T unmarshal( Class<T> docClass, InputStream inputStream )
	throws JAXBException { 
		//String packageName = docClass.getPackage().getName();
		JAXBContext jc = JAXBContext.newInstance( org.knipsX.utils.JAXB.parser );
		javax.xml.bind.Unmarshaller u = jc.createUnmarshaller();
		JAXBElement<T> doc = (JAXBElement<T>)u.unmarshal( inputStream );
		return doc.getValue();
	}


//
//	JAXBElement collection = (JAXBElement)u.unmarshal(new File( "project.xml"));
//	System.out.println("getValue(): " + collection.getValue() + "\n");
//	System.out.println("getName(): " + collection.getName() + "\n");
//	System.out.println("getScope(): " + collection.getScope() + "\n");
//	GreetingListType greetType = (hello.GreetingListType) collection.getValue();
//	List greetZ = greetType.getGreeting();
//	System.out.println("size: " + greetZ.size() + "\n");
//
//	for (int i= 0; i<greetZ.size();i++) {
//
//		System.out.println("Element: " + i + "\n");
//
//		hello.GreetingType greetRecord = (GreetingType) greetZ.get(i);
//
//		System.out.println("Text: " + greetRecord.getText() + "\n");
//		System.out.println("Language: " + greetRecord.getLanguage() + "\n");
//
//	}
//
//} catch( JAXBException jbe ) {
//	// ...
//}

//}

}
