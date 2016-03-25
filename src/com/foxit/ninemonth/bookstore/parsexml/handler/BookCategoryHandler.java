package com.foxit.ninemonth.bookstore.parsexml.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrBookCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrFeedCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.BookCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.FeedCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.BaseLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-21
   *
 */
public class BookCategoryHandler extends AbstrCommonHandler {
	
	private AbstrBookCategory bookCategory;
	private List<AbstrFeedCategory> list;
	
	private AbstrFeedCategory abstrFeedCategory;
	private AbstrLink abstrLink;
	
	public BookCategoryHandler() {
		bookCategory = new BookCategory();
		list = new ArrayList<AbstrFeedCategory>();
	}

	public AbstrBookCategory getBookCategory() {
		return bookCategory;
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
		
		if (TAG_ENTRY.equals(tagName)) {
			state = STATE_LEVEL_2;
			abstrFeedCategory = new FeedCategory();
		}		
		else if (TAG_LINK.equals(tagName)) {
			abstrLink = new BaseLink();
			int length = attributes.getLength();
			
			for (int i=0; i<length; i++) {
				if (TAG_LINK_REL_ATTR.equals(attributes.getLocalName(i))) {
					abstrLink.setLinkRel(attributes.getValue(i));
				}
				else if (TAG_LINK_TYPE_ATTR.equals(attributes.getLocalName(i))) {
					abstrLink.setLinkType(attributes.getValue(i));
				}
				else if (TAG_LINK_HREF_ATTR.equals(attributes.getLocalName(i))) {
					abstrLink.setLinkHref(attributes.getValue(i));
				}
			}
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
		case STATE_LEVEL_2:
			this.processEntry();
			break;
		default:
			break;
		}
		
		if (TAG_ENTRY.equals(tagName)) {
			state = STATE_LEVEL;
			if (FeedCategory.LINK_REL_CATEGORY.equals(
					abstrFeedCategory.getDetail().getLinkRel())) {
				list.add(abstrFeedCategory);
			}
		}
		else if (TAG_FEED.equals(tagName)) {
			bookCategory.setList(list);
		}
		
		sb.delete(0, sb.length());
	}

	protected void processFeed() {
		String tmp = sb.toString().trim();
		
		if (TAG_TITLE.equals(tagName)) {
			bookCategory.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			bookCategory.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			bookCategory.setId(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			String rel = abstrLink.getLinkRel();
			
			if (BaseLink.LINK_REL_SELF.equals(rel)) {
				bookCategory.setSelf(abstrLink);
			}
			else if (BaseLink.LINK_REL_START.equals(rel)) {
				bookCategory.setStart(abstrLink);
			}
			else if (BaseLink.LINK_REL_SEARCH.equals(rel)) {
				bookCategory.setSearch(abstrLink);
			}
			else if (BaseLink.LINK_REL_PARENT.equals(rel)) {
				bookCategory.setParent(abstrLink);
			}
		}
	}
	
	protected void processEntry() {
		String tmp = sb.toString().trim();
		
		if (TAG_TITLE.equals(tagName)) {
			abstrFeedCategory.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			abstrFeedCategory.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			abstrFeedCategory.setId(tmp);
		}
		else if (TAG_ENTRY_DC_LANGUAGE.equals(tagName)) {
			abstrFeedCategory.setDcLanguage(tmp);
		}
		else if (TAG_ENTRY_DC_ISSUED.equals(tagName)) {
			abstrFeedCategory.setDcIssued(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			abstrFeedCategory.setDetail(abstrLink);
			String rel = abstrLink.getLinkRel();
			if (FeedCategory.LINK_REL_SHELF.equals(rel)) {
				bookCategory.setShelf(abstrFeedCategory);
			}
		}
	}
	
//	@Override
//	public void characters(char[] ch, int start, int length)
//			throws SAXException {
//		super.characters(ch, start, length);
//		
//		sb.append(ch, start, length);
//	}

}
