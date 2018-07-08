package com.hiekn.test.tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.Office;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class TikaDemo {
	
//	private static File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\Elasticsearch权威指南（中文版）.pdf");
	private static File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\zip.zip");
//	private static File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\doc.docx");
//	private static File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\p.pptx");
//	private static File file = new File("C:\\Users\\DH\\Desktop\\quick\\test-data\\es.png");
	
	public static void main(String[] args) throws Exception {
	
//		parsePdf(file);
//		tikaTool(file);
		fileToTxt(file);
	}
	
	public static void parsePdf(File f) throws Exception {
		//Tika默认是10*1024*1024，这里防止文件过大导致Tika报错
		BodyContentHandler handler = new BodyContentHandler(-1);

		Metadata metadata = new Metadata();
		FileInputStream inputstream = new FileInputStream(f);
		ParseContext pcontext = new ParseContext();

		// 解析PDF文档时应由超类AbstractParser的派生类PDFParser实现
		PDFParser pdfparser = new PDFParser();
		pdfparser.parse(inputstream, handler, metadata, pcontext);

		// 获取PDF文档的内容
//		System.out.println("PDF文档内容:" + handler.toString());

		// 获取PDF文档的元数据
		String[] metadataNames = metadata.names();
		for (String name : metadataNames) {
			System.out.println(name + " = " + metadata.get(name));
		}
	}

	public static void tikaTool(File f) throws IOException, TikaException {  
		Tika tika = new Tika();  
		Metadata metadata = new Metadata();  
		metadata.set(Office.AUTHOR, "空号");//重新设置文档的媒体内容  
		metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());  
		String str = tika.parseToString(new FileInputStream(f),metadata);  
		for(String name:metadata.names()) {  
			System.out.println(name+" = "+metadata.get(name));  
		}  
		System.out.println(str);
	}  
	
    public static void fileToTxt(File f) {  
        Parser parser = new AutoDetectParser();//自动检测文档类型，自动创建相应的解析器  
        InputStream is = null;  
        try {  
            Metadata metadata = new Metadata();  
            metadata.set(Office.AUTHOR, "空号");//重新设置文档的媒体内容  
            metadata.set(Metadata.RESOURCE_NAME_KEY, f.getName());  
            is = new FileInputStream(f);  
            ContentHandler handler = new BodyContentHandler(-1);  
            ParseContext context = new ParseContext();  
            context.set(Parser.class,parser);  
            parser.parse(is,handler, metadata,context);  
            for(String name:metadata.names()) {  
                System.out.println(name+" = "+metadata.get(name));  
            }  
            System.out.println(handler.toString());  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (SAXException e) {  
            e.printStackTrace();  
        } catch (TikaException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if(is!=null) is.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    } 
}
