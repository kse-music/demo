package com.hiekn.test.tika;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.OfficeParser;
import org.apache.tika.sax.BodyContentHandler;

public class ParsePpt {

	public static void main(final String[] args) throws Exception {
		
		File file = new File("G:\\戴朝波数据\\器件和产品\\陶瓷电容\\PPT\\陶瓷电容.ppt");
		
		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(file);
		ParseContext pcontext = new ParseContext();

		OfficeParser  msofficeparser = new OfficeParser (); 
		msofficeparser.parse(inputstream, handler, metadata,pcontext);
		System.out.println("Contents of the document:" + handler.toString());
		System.out.println("Metadata of the document:");
		String[] metadataNames = metadata.names();

		for(String name : metadataNames) {
			System.out.println(name + " = " + metadata.get(name));
		}
	}
}