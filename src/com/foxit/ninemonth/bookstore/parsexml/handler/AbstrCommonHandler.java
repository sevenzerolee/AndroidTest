package com.foxit.ninemonth.bookstore.parsexml.handler;

import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.foxit.ninemonth.bookstore.parsexml.spi.TagAndAttrSpi;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public abstract class AbstrCommonHandler extends DefaultHandler implements
		TagAndAttrSpi {
	
	protected static final Logger log = Logger.getLogger(AbstrCommonHandler.class.getSimpleName());
	private boolean debug = true;
	
	protected StringBuilder sb;
	protected String tagName;
	protected int state = STATE_LEVEL;
	
	protected AbstrCommonHandler() {
		sb = new StringBuilder(1024);
	}
	
	protected void log(String msg) {
		if (debug) {
			log.info(msg);
		}
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		
		state = STATE_LEVEL;
	}
	
	

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		
		sb = null;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		this.tagName = qName;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		this.tagName = qName;
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		
		this.sb.append(ch, start, length);
	}
	
}
