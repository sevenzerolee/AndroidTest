package org.sevenzero.pull;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-11-12
   *
 */
public class PullParser {
	
	static final String ENCODING = "UTF-8";
	static Logger log = Logger.getLogger(PullParser.class.getSimpleName());
	public static final String XML_PATH = "/mnt/sdcard/9yue/data.xml";
	
	public PullParser() {}
	
	public String serialize(List<Book> books) throws IllegalArgumentException,
			IllegalStateException, IOException {
		if (null != books && books.size() > 0) {
			Writer writer = new StringWriter();
			XmlSerializer serializer = Xml.newSerializer();
			serializer.setOutput(writer);
			serializer.startDocument(ENCODING, true);
			serializer.setPrefix("fdrm", "http://www.foxit.com");
			serializer.startTag("http://www.foxit.com", "books");
			for (Book b : books) {
				serializer.startTag(null, "book");
				serializer.attribute(null, "id", b.getId());
				
				serializer.startTag(null, "id");
				serializer.text(String.valueOf(b.getId2()));
				serializer.endTag(null, "id");
				serializer.startTag(null, "name");
				if (null != b.getName()) {
					serializer.text(b.getName());
				}
				serializer.endTag(null, "name");
				serializer.startTag(null, "price");
				serializer.text(String.valueOf(b.getPrice()));
				serializer.endTag(null, "price");
				
				serializer.endTag(null, "book");
			}
			serializer.endTag("http://www.foxit.com", "books");
			serializer.endDocument();
			
			String result = writer.toString();
			
			PrintWriter w = new PrintWriter(XML_PATH);
			w.write(result);
			w.flush();
			w.close();
			
			return result;
		}
		
		return null;
	}
	
	public List<Book> parse(InputStream input) throws XmlPullParserException,
			IOException {
		if (null != input) {
			List<Book> books = new ArrayList<Book>();
			Book b = null;
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(input, ENCODING);
			
			int event = parser.getEventType();
			while (XmlPullParser.END_DOCUMENT != event) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT: 
					break;
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if ("book".equals(parser.getName())) {
						b = new Book();
						b.setId(parser.getAttributeValue(0));
						b.setId2(Integer.valueOf(parser.getAttributeValue(1)));
					}
					if (null != b) {
						if ("name".equals(parser.getName())) {
							b.setName(parser.nextText());
						}
						else if ("price".equals(parser.getName())) {
							b.setPrice(Float.valueOf(parser.nextText()).floatValue());
						}
					}
					break;
				case XmlPullParser.END_TAG:
					if ("book".equals(parser.getName())) {
						books.add(b);
						b = null;
					}
					break;
				}
				event = parser.next();
			}
			
			return books;
		}
		
		return null;
	}

}
