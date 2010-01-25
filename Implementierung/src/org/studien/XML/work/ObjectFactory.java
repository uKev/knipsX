//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vFCS 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2003.02.06 at 03:42:14 PST 
//


package test.jaxb;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the test.jaxb package. <p>An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups.  Factory methods for each of these are provided in this class.
 * 
 */
public class ObjectFactory
    extends com.sun.xml.bind.DefaultJAXBContextImpl
{

    private static java.util.HashMap defaultImplementations = new java.util.HashMap();

    static {
        defaultImplementations.put((test.jaxb.BookType.class), (test.jaxb.impl.BookTypeImpl.class));
        defaultImplementations.put((test.jaxb.CollectionType.class), (test.jaxb.impl.CollectionTypeImpl.class));
        defaultImplementations.put((test.jaxb.BookType.AuthorsType.class), (test.jaxb.impl.BookTypeImpl.AuthorsTypeImpl.class));
        defaultImplementations.put((test.jaxb.CollectionType.BooksType.class), (test.jaxb.impl.CollectionTypeImpl.BooksTypeImpl.class));
        defaultImplementations.put((test.jaxb.Collection.class), (test.jaxb.impl.CollectionImpl.class));
        defaultImplementations.put((test.jaxb.BookType.PromotionType.class), (test.jaxb.impl.BookTypeImpl.PromotionTypeImpl.class));
    }

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: test.jaxb
     * 
     */
    public ObjectFactory() {
        super(new test.jaxb.ObjectFactory.GrammarInfoImpl());
    }

    /**
     * Create an instance of the specified Java content interface.
     * 
     * @param javaContentInterface the Class object of the javacontent interface to instantiate
     * @return a new instance
     * @throws JAXBException if an error occurs
     */
    public java.lang.Object newInstance(java.lang.Class javaContentInterface)
        throws javax.xml.bind.JAXBException
    {
        return super.newInstance(javaContentInterface);
    }

    /**
     * Get the specified property. This method can only be
     * used to get provider specific properties.
     * Attempting to get an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name the name of the property to retrieve
     * @return the value of the requested property
     * @throws PropertyException when there is an error retrieving the given property or value
     */
    public java.lang.Object getProperty(java.lang.String name)
        throws javax.xml.bind.PropertyException
    {
        return super.getProperty(name);
    }

    /**
     * Set the specified property. This method can only be
     * used to set provider specific properties.
     * Attempting to set an undefined property will result
     * in a PropertyException being thrown.
     * 
     * @param name the name of the property to retrieve
     * @param value the value of the property to be set
     * @throws PropertyException when there is an error processing the given property or value
     */
    public void setProperty(java.lang.String name, java.lang.Object value)
        throws javax.xml.bind.PropertyException
    {
        super.setProperty(name, value);
    }

    /**
     * Create an instance of BookType
     * 
     * @throws JAXBException if an error occurs
     */
    public test.jaxb.BookType createBookType()
        throws javax.xml.bind.JAXBException
    {
        return ((test.jaxb.BookType) newInstance((test.jaxb.BookType.class)));
    }

    /**
     * Create an instance of CollectionType
     * 
     * @throws JAXBException if an error occurs
     */
    public test.jaxb.CollectionType createCollectionType()
        throws javax.xml.bind.JAXBException
    {
        return ((test.jaxb.CollectionType) newInstance((test.jaxb.CollectionType.class)));
    }

    /**
     * Create an instance of BookTypeAuthorsType
     * 
     * @throws JAXBException if an error occurs
     */
    public test.jaxb.BookType.AuthorsType createBookTypeAuthorsType()
        throws javax.xml.bind.JAXBException
    {
        return ((test.jaxb.BookType.AuthorsType) newInstance((test.jaxb.BookType.AuthorsType.class)));
    }

    /**
     * Create an instance of CollectionTypeBooksType
     * 
     * @throws JAXBException if an error occurs
     */
    public test.jaxb.CollectionType.BooksType createCollectionTypeBooksType()
        throws javax.xml.bind.JAXBException
    {
        return ((test.jaxb.CollectionType.BooksType) newInstance((test.jaxb.CollectionType.BooksType.class)));
    }

    /**
     * Create an instance of Collection
     * 
     * @throws JAXBException if an error occurs
     */
    public test.jaxb.Collection createCollection()
        throws javax.xml.bind.JAXBException
    {
        return ((test.jaxb.Collection) newInstance((test.jaxb.Collection.class)));
    }

    /**
     * Create an instance of BookTypePromotionType
     * 
     * @throws JAXBException if an error occurs
     */
    public test.jaxb.BookType.PromotionType createBookTypePromotionType()
        throws javax.xml.bind.JAXBException
    {
        return ((test.jaxb.BookType.PromotionType) newInstance((test.jaxb.BookType.PromotionType.class)));
    }

    private static class GrammarInfoImpl
        extends com.sun.xml.bind.GrammarInfo
    {


        public java.lang.Class getDefaultImplementation(java.lang.Class javaContentInterface) {
            return ((java.lang.Class) defaultImplementations.get(javaContentInterface));
        }

        public java.lang.Class getRootElement(java.lang.String uri, java.lang.String local) {
            if (("" == uri)&&("Collection" == local)) {
                return (test.jaxb.impl.CollectionImpl.class);
            }
            return null;
        }

        public java.lang.String[] getProbePoints() {
            return new java.lang.String[] {"", "Collection"};
        }

    }

}
