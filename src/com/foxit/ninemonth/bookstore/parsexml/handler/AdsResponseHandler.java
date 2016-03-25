package com.foxit.ninemonth.bookstore.parsexml.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrResponse;
import com.foxit.ninemonth.bookstore.parsexml.entry.ads.AbstrAdsColumn;
import com.foxit.ninemonth.bookstore.parsexml.entry.ads.AdsColumn;
import com.foxit.ninemonth.bookstore.parsexml.entry.ads.AdsResponse;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
   *
 */
public class AdsResponseHandler extends AbstrCommonHandler {
	
	private AbstrResponse<List<AbstrAdsColumn>> response;
	private List<AbstrAdsColumn> list;
	private AbstrAdsColumn column;
	
	public AdsResponseHandler() {
		response = new AdsResponse();
		list = new ArrayList<AbstrAdsColumn>();
	}

	public AbstrResponse<List<AbstrAdsColumn>> getResponse() {
		return response;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
//		log.info(qName);
		
		if (AD_COLUMN.equals(tagName)) {
			state = STATE_LEVEL_4;
			column = new AdsColumn();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
//		log.info(qName);
		
		switch (state) {
		case STATE_LEVEL:
			this.processFeed();
			break;
		case STATE_LEVEL_4:
			this.processAdsColumn();
			break;
		default:
			break;
		}
		
		if (AD_COLUMN.equals(tagName)) {
			state = STATE_LEVEL;
			list.add(column);
		}
		else if (JOYREAD.equals(tagName)) {
			response.setContent(list);
		}
		
		sb.delete(0, sb.length());
	}

	protected void processAdsColumn() {
		String tmp = sb.toString().trim();
		
		if (AD_COLUMN_TYPE.equals(tagName)) {
			column.setAdColumnType(tmp);
		}
		else if (AD_COLUMN_ID.equals(tagName)) {
			column.setId(tmp);
		}
		else if (AD_COLUMN_URL.equals(tagName)) {
			column.setAdColumnUrl(tmp);
		}
		else if (LINK_TO.equals(tagName)) {
			column.setLinkTo(tmp);
		}
		else if (AD_COLUMN_CONTENT.equals(tagName)) {
			column.setContent(tmp);
		}
		else if (RECOMMEND_LOCATION.equals(tagName)) {
			column.setRecommendLocation(tmp);
		}
	}

	protected void processFeed() {
		String tmp = sb.toString().trim();
		if (IS_SUCCESS.equals(tagName)) {
			if (AdsResponse.SUCCESS.equals(tmp)) {
				response.setSuccess(true);
			}
		}
		else if (VERSION.equals(tagName)) {
			response.setVersion(tmp);
		}
		else if (RESPONSE_CODE.equals(tagName)) {
			response.setResponseCode(tmp);
		}
		else if (RESULT_MESSAGE.equals(tagName)) {
			response.setResultMessage(tmp);
		}
		else if (ENCRYPT_TYPE.equals(tagName)) {
			response.setEncryptType(tmp);
		}
		else if (ENCRYPT_KEY.equals(tagName)) {
			response.setEncryptKey(tmp);
		}
	}

}
