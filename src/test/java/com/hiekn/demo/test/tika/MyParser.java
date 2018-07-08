package com.hiekn.test.tika;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AbstractParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.XHTMLContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class MyParser extends AbstractParser {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1406399589603166812L;
	private static final Set<MediaType> SUPPORTED_TYPES = Collections.singleton(MediaType.application("pdf"));
    public static final String HELLO_MIME_TYPE = "application/pdf";
    
    public Set<MediaType> getSupportedTypes(ParseContext context) {
            return SUPPORTED_TYPES;
    }

    public void parse(InputStream stream, ContentHandler handler,
                    Metadata metadata, ParseContext context)
                    throws IOException, SAXException, TikaException {
        metadata.set(Metadata.CONTENT_TYPE, HELLO_MIME_TYPE);
        XHTMLContentHandler xhtml = new XHTMLContentHandler(handler, metadata);
        xhtml.startDocument();
        xhtml.endDocument();
    }
}