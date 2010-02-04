package org.knipsX.utils.XML;

import java.util.LinkedList;
import java.util.List;

import org.jdom.Element;
public final class XMLHelper {

    private XMLHelper() {
        
    }
    
    @SuppressWarnings("unchecked")
    public static List<Element> convertList(List list) {
        List<Element> items = new LinkedList<Element>();
        for(Object item : list) {
            if(item instanceof Element) {
                items.add((Element) item);
            }
        }
        return items;
    }
    
}
