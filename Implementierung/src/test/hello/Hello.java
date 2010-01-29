import java.util.*;
import javax.xml.bind.*;
import hello.*;
import java.io.*;

public class Hello {

    private ObjectFactory of;
    private GreetingListType grList;

    public Hello() {
        of = new ObjectFactory();
        grList = of.createGreetingListType();
    }

    public void make( String t, String l ){
        GreetingType g = of.createGreetingType();
        g.setText( t );
        g.setLanguage( l );
        grList.getGreeting().add( g );
    }

    public void marshal() {
        try {
            JAXBElement<GreetingListType> gl =
                of.createGreetings( grList );
            JAXBContext jc = JAXBContext.newInstance( "hello" );
            Marshaller m = jc.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                   new Boolean(true));
 
		m.marshal( gl, System.out );
		System.out.println("\n 2nd output:\n");
                m.marshal( gl, System.out );

		try{

		OutputStream os = new FileOutputStream( "output.xml" );
		System.out.println("Writing to file...\n");
		m.marshal( gl, os );

		}catch (Exception e){//Catch exception if any
      		System.err.println("Error: " + e.getMessage());
		}
		System.out.println("Done.\n");
        } catch( JAXBException jbe ){
            // ...
        }
    }
}
