package org.knipsX.utils.JAXB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.knipsX.utils.JAXB.parser.*;

abstract class Handler {
	protected static Map<Class<?>,Handler> ourClass2Conv = 
	new HashMap<Class<?>,Handler>();

	static {
		
//		ourClass2Conv.put( Directory.class, new DirectoryHandler() );
//		ourClass2Conv.put( Directories.class, new DirectoriesHandler() );
//		ourClass2Conv.put( Picture.class, new PictureHandler() );
//		ourClass2Conv.put( PictureSet.class, new PictureSetHandler() );
//		ourClass2Conv.put( PictureSets.class, new PictureSetsHandler() );
//		//...

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
			//Handler h = this.getHandler( obj );
			//h.process( obj );
		}
	}
	

}