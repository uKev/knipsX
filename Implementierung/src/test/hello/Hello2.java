import java.util.*;
import javax.xml.bind.*;
import hello.*;
import java.io.*;



public class Hello2 {
        

public Hello2() {
	//empty constructor
}

public void unmarshal() {
	try {

		JAXBContext jc = JAXBContext.newInstance( "hello" );
       		Unmarshaller u = jc.createUnmarshaller();
		//u.setValidating(true);
       		//Object o = u.unmarshal( new File( "nosferatu.xml" ) );

		Object collection = u.unmarshal(new File( "output.xml"));

		//CollectionType.BooksType booksType = collection.getBooks();
            	//List bookList = booksType.getBook();

		//hello.ObjectFactory.GreetingType po = hello.ObjectFactory.createGreetingType();

		//hello.ObjectFactory.GreetingListType GreetList = hello.ObjectFactory.createGreetingListType(); 
		//hello.ObjectFactory.JAXBElement GreetList = hello.ObjectFactory.createGreetings(GreetList); //---

		//hello.GreetingListType GreetList = collection.getGreetingListType(); //***********************************
		
		//JAXBElement GreetList = collection.createGreetings(GreetList); //---


		//if ( o != NULL ){
		//	System.out.println("we have some data!!!");
		List Greetz = new ArrayList(collection.getGreeting());
		System.out.println("Nr.: " + Greetz.size()  + "\n");

		//for( int i = 1; i < GreetList.size(); i++ ){
	/*
		for( int i = 1; i < collection.size(); i++ ){

			System.out.println("");
			hello.GreetingType Greeting = (hello.GreetingType) GreetList.get(i);
			
			System.out.println("Nr.: " + i + "\n");
               		System.out.println("Greeting's text: " +  Greeting.getText() + "\n");
               		System.out.println("Greeting's language: " +  Greeting.getLanguage() + "\n");

			}
*/
		
	} catch( JAXBException jbe ) {
            // ...
        }

    }
 