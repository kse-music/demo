package com.hiekn.test.tika;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.apache.tika.sax.ToXMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class ParsePdf {
	
	private static final String BASE_PATH = "C:\\Users\\DH\\Desktop\\quick\\test-data\\";
	private static final int MAXIMUM_TEXT_CHUNK_SIZE = 10;

	public static void main(String[] args) throws Exception {
		File file = new File("G:\\戴朝波数据\\电子书\\ABEL\\ABEL_REF.PDF");
		file = new File(BASE_PATH+"java规范1.2.pdf");
		file = new File("G:\\戴朝波数据\\PSD软件\\2017电力系统仿真技术研讨会资料-PSD软件\\PSD-FDS全过程动态仿真程序-2017年8月云南.pdf");
		
		// Only get things under html -> body -> div (class=header)
//		XPathParser xhtmlParser = new XPathParser("xhtml", XHTMLContentHandler.XHTML);
//		Matcher matcher = xhtmlParser.parse("/xhtml:html/xhtml:body/xhtml:div/xhtml:p/text()");

		Metadata metadata = new Metadata();

		ContentHandler handler = new BodyContentHandler(-1);
		handler = new ToXMLContentHandler();
//		handler = new MatchingContentHandler(new ToXMLContentHandler(), matcher);
//		handler = new PhoneExtractingContentHandler(new BodyContentHandler(),metadata);

//		handler = new BodyContentHandler(new ToXMLContentHandler());
		
		FileInputStream inputstream = new FileInputStream(file);
		ParseContext pcontext = new ParseContext();
		
		Parser parser = new AutoDetectParser(); 
		
//		PDFParser parser = new PDFParser();
//		PDFParserConfig config = new PDFParserConfig();
//		config.setExtractInlineImages(true);
//		pcontext.set(PDFParserConfig.class, config);
		
		try {
			parser.parse(inputstream, handler, metadata,pcontext);
		} finally {
			inputstream.close();
		}
		
		
		IOUtils.write(handler.toString(), new FileOutputStream(new File(BASE_PATH+"a.html")),"utf-8");
//		System.out.println(handler);
		
//		for (String name : metadata.names()) {
//			System.out.println(name + " = " + metadata.get(name));
//		}
//		System.out.println(Arrays.toString(metadata.getValues("phonenumbers")));
		
//		LanguageIdentifier identifier = new LanguageIdentifier(handler.toString());
//	    System.out.println(identifier.getLanguage());
	    
	
	}

	public static List<String> parseToPlainTextChunks(File file) throws IOException, SAXException, TikaException {
		final List<String> chunks = new ArrayList<>();
		chunks.add("");
		ContentHandlerDecorator handler = new ContentHandlerDecorator() {
			@Override
			public void characters(char[] ch, int start, int length) {
				String lastChunk = chunks.get(chunks.size() - 1);
				String thisStr = new String(ch, start, length);

				if (lastChunk.length() + length > MAXIMUM_TEXT_CHUNK_SIZE) {
					chunks.add(thisStr);
				} else {
					chunks.set(chunks.size() - 1, lastChunk + thisStr);
				}
			}
		};

		AutoDetectParser parser = new AutoDetectParser();
		Metadata metadata = new Metadata();
		parser.parse(new FileInputStream(file), handler, metadata);
		return chunks;
	}
}
