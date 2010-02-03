package org.studien.XMPTest;
import java.io.IOException;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.impl.XMPMetaParser;
import com.imagero.reader.IOParameterBlock;
import com.imagero.reader.marker.JpegMetadataReader;

public class Test {

	/**
	 * @param args
	 * @throws IOException
	 * @throws XMPException
	 */
	public static void main(String[] args) throws IOException, XMPException {

		String source = "/home/anopheles/Bilder/Fotos/2009/12/09/DSC00964.JPG";
		IOParameterBlock iopb = new IOParameterBlock(source);
		JpegMetadataReader meta = new JpegMetadataReader(iopb.getSourceStream());

		System.out.println(meta.getXMP());
		// System.out.println(XMLUtil.fromXML(meta.getXMP()).length);

		String test = meta.getXMP().replaceAll("uuid:faf5bdd5-ba3d-11da-ad31-d33d75182f1b", "");
		//String test = meta.getXMP();
		System.out.println(XMPMetaParser.parse(test, null).dumpObject());
		System.out.println(XMPMetaParser.parse(test, null).getPropertyString(
				"http://ns.adobe.com/photoshop/1.0/", "subject"));
		System.out.println(XMPMetaParser.parse(test, null).getArrayItem(
				"http://purl.org/dc/elements/1.1/", "subject", 1));
		System.out.println(XMPMetaParser.parse(test, null).getArrayItem(
				"http://purl.org/dc/elements/1.1/", "subject", 3));

	}

}
