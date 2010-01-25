/**
 * $Id: UsingJAXBTest1.java,v 1.1 2003/01/01 03:18:32 bhakti Exp $
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

package org.studien.XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;


/** 
  * This shows how to use JAXB to unmarshal an xml file
  * Then display the information from the content tree
  */

public class XML2Object {

    public static void main (String args[]) {
        try {
            JAXBContext jc = JAXBContext.newInstance("test.jaxb");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setValidating(true);

            Collection collection= (Collection)
                     unmarshaller.unmarshal(new File( "books.xml"));

            CollectionType.BooksType booksType = collection.getBooks();
            List bookList = booksType.getBook();

            for( int i = 0; i < bookList.size();i++ ) {
               System.out.println("Book  details "   );
               test.jaxb.BookType book =(test.jaxb.BookType) bookList.get(i);
               System.out.println("Item id: " +  book.getItemId());

               System.out.println("Book Name: " +  book.getName().trim());
               System.out.println("Book ISBN: " +  book.getISBN());
               System.out.println("Book Price: " +  book.getPrice().trim());
               System.out.println("Book category: " +  book.getBookCategory());
               System.out.println("Book promotion: " +  book.getPromotion().
                   getDiscount().trim());
               
               System.out.println("No of Authors " + 
                    book.getAuthors().getAuthorName().size());

               BookType.AuthorsType authors = book.getAuthors();
               for (int j = 0; j < authors.getAuthorName().size();j++) {
                  String authorName = (String) authors.getAuthorName().get(j);
                  System.out.println("Author Name " + authorName.trim());
               }
               System.out.println();

            }

       }catch (Exception e ) {
         e.printStackTrace();
       }
    }
}
