package com.foxit.ninemonth.bookstore.parsexml;

import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.foxit.ninemonth.bookstore.parsexml.handler.AbstrCommonHandler;

/**
 * 
 * @author sevenzero
 *
 * @since 2012-8-4 
 *
 */
public class SAXXmlUtil {
	
	private static final Logger log = Logger.getLogger(SAXXmlUtil.class.getName());
	
	/**
	 * Get SAXParser
	 * 
	 * @return
	 */
	public static SAXParser getSAXParser() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			return factory.newSAXParser();
		}
		catch (Exception e) {
			log.info("Get SAXParser Exception.");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get XMLReader
	 * 
	 * @return
	 */
	public static XMLReader getXmlReader() {
		SAXParser parser = getSAXParser();
		if (null == parser) {
			return null;
		}
		
		try {
			return parser.getXMLReader();
		}
		catch (SAXException e) {
			log.info("Get XMLReader Exception.");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param handler
	 * @param source
	 * @return true parser success <br/> false parser fail
	 */
	public static boolean parser(AbstrCommonHandler handler, InputSource source) {
		XMLReader reader = getXmlReader();
		if (null == reader) {
			return false;
		}
		
		try {
			reader.setContentHandler(handler);
			reader.parse(source);
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
