package org.knipsX.utils.JAXB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Handler {
	protected static Map<Class<?>,Handler> ourClass2Conv = 
	new HashMap<Class<?>,Handler>();

	static {
		ourClass2Conv.put( PersonType.class, new PersonHandler() );
		ourClass2Conv.put( NameTypeclass, new NameHandler() );
		ourClass2Conv.put( AddrType.class, new AddHandler() );
		ourClass2Conv.put( ChildType.class, new Child Handler() );
		//...

	}
	
	public abstract void handle( Object o );

	protected void process( Object obj ) {
		if( obj != null ){
			Handler h = ourClass2Conv.get( obj.getClass() );
			if( h != null ){
				h.handle( obj );
			}

		}
	}

	protected <T> void processList( List<T> list ){
		for( T obj: list ) {
			Handler h = this.getHandler( obj );
			h.process( obj );
		}
	}
}