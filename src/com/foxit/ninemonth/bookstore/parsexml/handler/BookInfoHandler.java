package com.foxit.ninemonth.bookstore.parsexml.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookDetail;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.BookDetail;
import com.foxit.ninemonth.bookstore.parsexml.entry.bookinfo.AbstrBookInfo;
import com.foxit.ninemonth.bookstore.parsexml.entry.bookinfo.BookInfo;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.BaseLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.LengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.TitleLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
   *
 */
public class BookInfoHandler extends AbstrCommonHandler {
	
	private AbstrBookInfo bookInfo;
	private AbstrBookDetail detail;
	private AbstrLengthLink abstrLink;
	
	public BookInfoHandler() {
		bookInfo = new BookInfo();
	}

	public AbstrBookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(AbstrBookInfo bookInfo) {
		this.bookInfo = bookInfo;
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
			detail = new BookDetail();
		}
		else if (TAG_ENTRY_CATEGORY.equals(tagName)) {
			int length = attributes.getLength();
			
			for (int i=0; i<length; i++) {
				if (TAG_ENTRY_CATEGORY_TERM_ATTR.equals(attributes.getLocalName(i))) {
					detail.setCateTerm(attributes.getValue(i));
				}
				else if (TAG_ENTRY_CATEGORY_LABEL_ATTR.equals(attributes.getLocalName(i))) {
					detail.setCateLabel(attributes.getValue(i));
				}
			}
		}
		else if (TAG_ENTRY_CONTENT.equals(tagName)) {
			int length = attributes.getLength();
			
			for (int i=0; i<length; i++) {
				if (TAG_ENTRY_CONTENT_TYPE_ATTR.equals(attributes.getLocalName(i))) {
					detail.setContentType(attributes.getValue(i));
				}
			}
		}
		else if (TAG_LINK.equals(tagName)) {
			int length = attributes.getLength();
			abstrLink = new LengthLink();
			
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
				else if (TAG_LINK_TITLE_ATTR.equals(attributes.getLocalName(i))) {
					abstrLink.setLinkTitle(attributes.getValue(i));
				}
				else if (TAG_LINK_LENGTH_ATTR.equals(attributes.getLocalName(i))) {
					abstrLink.setLinkLength(attributes.getValue(i));
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
			bookInfo.setBookDetail(detail);
		}
		
		sb.delete(0, sb.length());
	}

	protected void processEntry() {
		String tmp = sb.toString().trim();
		
		if (TAG_TITLE.equals(tagName)) {
			detail.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			detail.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			detail.setId(tmp);
		}
		else if (TAG_AUTHOR_NAME.equals(tagName)) {
			detail.setAuthorName(tmp);
		}
		else if (TAG_ENTRY_DC_LANGUAGE.equals(tagName)) {
			detail.setDcLanguage(tmp);
		}
		else if (TAG_ENTRY_DC_ISSUED.equals(tagName)) {
			detail.setDcIssued(tmp);
		}
		else if (TAG_ENTRY_DC_IDENTIFIER.equals(tagName)) {
			detail.setDcIdentifier(tmp);
		}
		else if (TAG_ENTRY_DC_PUBLISHER.equals(tagName)) {
			detail.setDcPublisher(tmp);
		}
		else if (TAG_ENTRY_CONTENT.equals(tagName)) {
			detail.setContent(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			String rel = abstrLink.getLinkRel();
			String type = abstrLink.getLinkType();
			String title = abstrLink.getLinkTitle();
			
			if (BaseLink.LINK_REL_IMAGE.equals(rel)) {
				detail.setImage(abstrLink);
			}
			else if (BaseLink.LINK_REL_THUMB.equals(rel)) {
				detail.setThumb(abstrLink);
			}
			else if (LengthLink.LINK_REL_EPUB_OR_PDF.equals(rel) 
					&& LengthLink.LINK_TYPE_EPUB.equals(type)) {
				detail.setEpub(abstrLink);
			}
			else if (LengthLink.LINK_REL_EPUB_OR_PDF.equals(rel) 
					&& LengthLink.LINK_TYPE_PDF.equals(type)) {
				detail.setPdf(abstrLink);
			}
			else if (TitleLink.LINK_TITLE_COMMENT.equals(title) 
					&& TitleLink.LINK_TYPE.equals(type)) {
				detail.setComment(abstrLink);
			}
		}
	}

	protected void processFeed() {
		String tmp = sb.toString().trim();
		
		if (TAG_TITLE.equals(tagName)) {
			bookInfo.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			bookInfo.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			bookInfo.setId(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			String rel = abstrLink.getLinkRel();
			
			if (BaseLink.LINK_REL_SELF.equals(rel)) {
				bookInfo.setSelf(abstrLink);
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
