/**
 * $Id: UsingJAXBTest2.java,v 1.1 2003/01/01 03:18:32 bhakti Exp $
 * Copyright (c) 2003 Sun Microsystems, Inc. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

// http://java.sun.com/developer/technicalArticles/WebServices/jaxb/

package org.studien.XML;

import javax.naming.spi.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.validation.Validator;
import javax.xml.bind.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.GregorianCalendar;



/** 
  * This shows how to use JAXB to create a content tree and populate it
  * and marshal to an xml file
  */

public class Object2XML {

    public static void main (String args[]) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("test.jaxb");
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT ,
                   new Boolean(true));
            //Validator validator = jaxbContext.createValidator();

            ObjectFactory objFactory = new ObjectFactory();
            Collection collection= (Collection) ((Object) objFactory).createCollection();

            Collection.BooksType booksType = objFactory.
                                  createCollectionTypeBooksType();
            List bookList = booksType.getBook();

            BookType book = objFactory.createBookType();
            book.setItemId("307");
            book.setName("Kobzar");
            book.setDescription("A comprehensive collection of poems, essays and novels from T.G. Shevchenko");
            book.setISBN(987665L);
            book.setPrice("76 UAH");
            book.setPublicationDate(new GregorianCalendar(2000,2,2));
            book.setBookCategory("novel");

            BookType.PromotionType promotionType = objFactory.
                 createBookTypePromotionType();
            promotionType.setDiscount("5% off regular price");
            book.setPromotion(promotionType);
               
            BookType.AuthorsType authorsType = objFactory.
              createBookTypeAuthorsType();
            List authorList = authorsType.getAuthorName();
            authorList.add("Schevchenko T. G.");
            book.setAuthors(authorsType);

            bookList.add(book);

            //only one book
            
            
            collection.setBooks(booksType);
            
            System.out.println("Validator returned " + 
               validator.validate(collection));
            
            marshaller.marshal(collection,
                  new FileOutputStream("book_output.xml"));
            
            System.out.println("Created a content tree " + 
            "and marshalled it to book_output.xml");
            
            System.out.println("See output in book_output.xml" ) ;
     

       }catch (Exception e ) {
         e.printStackTrace();
       }
    }
}