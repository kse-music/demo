package com.hiekn.test.tika;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.ocr.TesseractOCRParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class ParseOcr {
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\es.png");
		
		ContentHandler handler = new BodyContentHandler();
		
		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(file);
		
		TesseractOCRConfig config = new TesseractOCRConfig();
		config.setTesseractPath("E:\\Program Files (x86)\\Tesseract-OCR");
		config.setLanguage("chi_sim");
		
		ParseContext pcontext = new ParseContext();
		pcontext.set(TesseractOCRConfig.class, config);
		
		TesseractOCRParser tesseractOCRParser = new TesseractOCRParser(); 
		tesseractOCRParser.parse(inputstream, handler, metadata,pcontext);
		
		System.out.println(handler.toString());

		for(String name : metadata.names()) {
			System.out.println(name + " = " + metadata.get(name));
		}
	}
}
