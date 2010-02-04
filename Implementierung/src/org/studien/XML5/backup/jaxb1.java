public <T> T unmarshal( Class<T> docClass, InputStream inputStream )
	throws JAXBException {
	String packageName = docClass.getPackage().getName();
	JAXBcontext jc = JAXBContext.newInstance( packageName );
	Unmarshaller u = jc.createUnmarshaller();
	JAXBElement<T> doc = (JAXBElement<T>)u.unmarshal( inputStream );
	return doc.getValue();
}
