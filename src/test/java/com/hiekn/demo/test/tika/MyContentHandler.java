package com.hiekn.test.tika;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {

    private final String HTML_ELEMENT = "html";
    private final String HEAD_ELEMENT = "head";
    private final String TITLE_ELEMENT = "title";
//    private final String BODY_ELEMENT = "body";
    private final String IMG_ELEMENT = "img";
    private final String META_ELEMENT = "meta";
//    private final String TABLE_ELEMENT = "table";
//    private final String DIV_ELEMENT = "div";
//    private final String TR_ELEMENT = "tr";
//    private final String TD_ELEMENT = "td";
    private final String P_ELEMENT = "p";
    private final String B_ELEMENT = "b";
    private StringBuilder stringBuilder;
    private static final String BASE_IMG_PATH = "C:\\Users\\DH\\Desktop\\quick\\test-data\\pdfimg";
    
    public MyContentHandler(InputStream stream){
//    	extractImage(stream);
    }
    
    public void extractImage(InputStream stream){
    	PDDocument document = null;
    	try {
    		document = PDDocument.load(stream);
    		int pages = document.getNumberOfPages();
    		int j = 0;
    		for(int i=0;i < pages;i++) {  
    			PDPage page = document.getPage(i);
    			PDResources resources = page.getResources();  

    			Iterable<COSName> xobjects = resources.getXObjectNames();
    			if (xobjects != null) {  
    				Iterator<COSName> imageIter = xobjects.iterator();  
    				while (imageIter.hasNext()) {  
    					COSName key = imageIter.next();
    					if(resources.isImageXObject(key)){  
    						PDImageXObject image = (PDImageXObject) resources.getXObject(key);
    						File target = new File(BASE_IMG_PATH+"\\image"+j+".jpg");
    						BufferedImage bufferedImage = image.getImage();  
    						ImageIO.write(bufferedImage, "jpeg", target);
    						j++;
    					}
    				}
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					if(document != null){
						document.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
    }
    
    @Override
    public void startDocument() throws SAXException {
        stringBuilder = new StringBuilder();
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
    	String src = "";
        if(localName.equals(IMG_ELEMENT)){
            for(int i=0;i<attributes.getLength();i++){
                if(attributes.getLocalName(i)=="src" && attributes.getValue(i).startsWith("embedded:")){
                    String filename = attributes.getValue(i).substring(attributes.getValue(i).indexOf(":")+1);
                    src = "pdfimg/"+filename;
                    break;
                }
            }
        }
        //开始标签
        if(!localName.equals(B_ELEMENT) && !localName.equals(HTML_ELEMENT)){
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("<"+localName);
        if(localName==META_ELEMENT &&  attributes.getLength()==2 && attributes.getValue(0)=="Content-Type"){
            stringBuilder.append(" name=\"Content-Type\" content=\"text/html\"");
        }else{
            for(int i=0;i<attributes.getLength();i++){
            	if("".equals(src)){
            		stringBuilder.append(" "+attributes.getLocalName(i)+"=\""+attributes.getValue(i)+"\"");
            	}else{
            		stringBuilder.append(" "+attributes.getLocalName(i)+"=\""+src+"\"");
            	}
            }
        }
        if(localName.equals(IMG_ELEMENT) || localName.equals(META_ELEMENT)){
            stringBuilder.append("/>");
        }else{
            stringBuilder.append(">");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(localName.equals(HEAD_ELEMENT)){
            stringBuilder.append("\r\n<style>\r\ntable{margin:0;padding:0;border-collapse:collapse;}\r\ntable td{border:1px solid black;}\r\n</style>");
        }
        if(localName.equals(IMG_ELEMENT) || localName.equals(META_ELEMENT)){
            
        }else{
            if(!localName.equals(B_ELEMENT) && !localName.equals(P_ELEMENT) && !localName.equals(TITLE_ELEMENT)){
                stringBuilder.append("\r\n");
            }
            stringBuilder.append("</"+localName+">");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String theString = new String(ch, start, length);
        stringBuilder.append(theString.trim());
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
	
}
