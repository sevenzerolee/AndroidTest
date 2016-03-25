package com.foxit.ninemonth.bookstore.parsexml.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrFeedCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.Category;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.FeedCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.BaseLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-9
   *
 */
public class FeedCategoryHandler extends AbstrCommonHandler {
	
	private AbstrCategory category;
	
	private AbstrFeedCategory abstrFeedCategory;
	private AbstrLink abstrLink;
	
	public FeedCategoryHandler() {
		category = new Category();
	}

	public AbstrCategory getCategory() {
		return category;
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
		
		if (TAG_ENTRY.equals(qName)) {
			state = STATE_LEVEL;
		}
		
		sb.delete(0, sb.length());
	}
	
	protected void processFeed() {
		String tmp = sb.toString().trim();
		
		if (TAG_TITLE.equals(tagName)) {
			category.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			category.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			category.setId(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			String rel = abstrLink.getLinkRel();
			
			if (BaseLink.LINK_REL_SELF.equals(rel)) {
				category.setSelf(abstrLink);
			}
			else if (BaseLink.LINK_REL_START.equals(rel)) {
				category.setStart(abstrLink);
			}
			else if (BaseLink.LINK_REL_SEARCH.equals(rel)) {
				category.setSearch(abstrLink);
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
				category.setShelf(abstrFeedCategory);
			}
			else if (FeedCategory.LINK_REL_RECOMMENDED.equals(rel)) {
				category.setRecommended(abstrFeedCategory);
			}
			else if (FeedCategory.LINK_REL_FACET.equals(rel)) {
				category.setFacet(abstrFeedCategory);
			}
			else if (FeedCategory.LINK_REL_CATEGORY.equals(rel)) {
				category.setCategory(abstrFeedCategory);
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
