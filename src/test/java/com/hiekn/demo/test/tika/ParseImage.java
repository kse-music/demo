package com.hiekn.test.tika;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.jpeg.JpegParser;
import org.apache.tika.sax.BodyContentHandler;

public class ParseImage {

	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\IMG_0269.JPG");
		file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\366303.jpg");

		BodyContentHandler handler = new BodyContentHandler(-1);

		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(file);
		ParseContext pcontext = new ParseContext();

		metadata.set("Image-Id", file.getName().substring(0,file.getName().lastIndexOf(".")));  

		JpegParser jpegParser = new JpegParser();
		jpegParser.parse(inputstream, handler, metadata,pcontext);
		System.out.println(handler.toString());

		String[] metadataNames = metadata.names();

		for(String name : metadataNames) { 	
			String data = metadata.get(name);
			System.out.println(name+" = " + new String(data.getBytes("UTF-8"),"GBK"));
		}
	}
	
}
