package com.foxit.ninemonth.bookstore.parsexml.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookSummary;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.BookSummary;
import com.foxit.ninemonth.bookstore.parsexml.entry.booklist.AbstrSearchBookList;
import com.foxit.ninemonth.bookstore.parsexml.entry.booklist.SearchBookList;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.BaseLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.LengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.TitleLink;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public class SearchBookListHandler extends AbstrCommonHandler {
	
	private AbstrSearchBookList bookList;
	private List<AbstrBookSummary> list;
	private AbstrBookSummary summary;
	private AbstrLengthLink abstrLink;
	
	public SearchBookListHandler() {
		bookList = new SearchBookList();
		list = new ArrayList<AbstrBookSummary>();
	}

	public AbstrSearchBookList getBookList() {
		return bookList;
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
			summary = new BookSummary();
		}
		else if (TAG_ENTRY_CATEGORY.equals(tagName)) {
			int length = attributes.getLength();
			
			for (int i=0; i<length; i++) {
				if (TAG_ENTRY_CATEGORY_TERM_ATTR.equals(attributes.getLocalName(i))) {
					summary.setCateTerm(attributes.getValue(i));
				}
				else if (TAG_ENTRY_CATEGORY_LABEL_ATTR.equals(attributes.getLocalName(i))) {
					summary.setCateLabel(attributes.getValue(i));
				}
			}
		}
		else if (TAG_OPENSEARCH_QUERY.equals(tagName)) {
			int length = attributes.getLength();
			
			for (int i=0; i<length; i++) {
				if (TAG_OPENSEARCH_QUERY_ROLE_ATTR.equals(attributes.getLocalName(i))) {
					bookList.setQueryRole(attributes.getValue(i));
				}
				else if (TAG_OPENSEARCH_QUERY_KEY_ATTR.equals(attributes.getLocalName(i))) {
					bookList.setQueryKey(attributes.getValue(i));
				}
				else if (TAG_OPENSEARCH_QUERY_STARTPAGE_ATTR.equals(attributes.getLocalName(i))) {
					bookList.setQueryStartPage(attributes.getValue(i));
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
		
		if (TAG_FEED.equals(qName)) {
			bookList.setList(list);
		}
		else if (TAG_ENTRY.equals(qName)) {
			state = STATE_LEVEL;
			list.add(summary);
		}
		
		sb.delete(0, sb.length());
	}

	protected void processEntry() {
		String tmp = sb.toString().trim();
		
		if (TAG_TITLE.equals(tagName)) {
			summary.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			summary.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			summary.setId(tmp);
		}
		else if (TAG_AUTHOR_NAME.equals(tagName)) {
//			log.info(tmp);
			summary.setAuthorName(tmp);
		}
		else if (TAG_ENTRY_DC_LANGUAGE.equals(tagName)) {
			summary.setDcLanguage(tmp);
		}
		else if (TAG_ENTRY_DC_ISSUED.equals(tagName)) {
			summary.setDcIssued(tmp);
		}
		else if (TAG_ENTRY_DC_IDENTIFIER.equals(tagName)) {
			summary.setDcIdentifier(tmp);
		}
		else if (TAG_ENTRY_DC_PUBLISHER.equals(tagName)) {
			summary.setDcPublisher(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			String rel = abstrLink.getLinkRel();
			String type = abstrLink.getLinkType();
			String title = abstrLink.getLinkTitle();
			
			if (BaseLink.LINK_REL_IMAGE.equals(rel)) {
				summary.setImage(abstrLink);
			}
			else if (BaseLink.LINK_REL_THUMB.equals(rel)) {
				summary.setThumb(abstrLink);
			}
			else if (LengthLink.LINK_REL_EPUB_OR_PDF.equals(rel) 
					&& LengthLink.LINK_TYPE_EPUB.equals(type)) {
				summary.setEpub(abstrLink);
			}
			else if (LengthLink.LINK_REL_EPUB_OR_PDF.equals(rel) 
					&& LengthLink.LINK_TYPE_PDF.equals(type)) {
				summary.setPdf(abstrLink);
			}
			else if (TitleLink.LINK_TITLE_DETAIL.equals(title) 
					&& TitleLink.LINK_TYPE.equals(type)) {
				summary.setDetail(abstrLink);
			}
			else if (TitleLink.LINK_TITLE_COMMENT.equals(title) 
					&& TitleLink.LINK_TYPE.equals(type)) {
				summary.setComment(abstrLink);
			}
		}
	}

	protected void processFeed() {
		String tmp = sb.toString().trim();
		
		if (TAG_OPENSEARCH_TOTAL_RESULTS.equals(tagName)) {
			bookList.setTotalResults(tmp);
		}
		else if (TAG_OPENSEARCH_ITEMS_PERPAGE.equals(tagName)) {
			bookList.setItemsPerPage(tmp);
		}
		else if (TAG_TITLE.equals(tagName)) {
			bookList.setTitle(tmp);
		}
		else if (TAG_UPDATED.equals(tagName)) {
			bookList.setUpdated(tmp);
		}
		else if (TAG_ID.equals(tagName)) {
			bookList.setId(tmp);
		}
		else if (TAG_LINK.equals(tagName)) {
			String rel = abstrLink.getLinkRel();
			
			if (BaseLink.LINK_REL_SELF.equals(rel)) {
				bookList.setSelf(abstrLink);
			}
			else if (BaseLink.LINK_REL_SEARCH.equals(rel)) {
				bookList.setSearch(abstrLink);
			}
			else if (BaseLink.LINK_REL_PARENT.equals(rel)) {
				bookList.setParent(abstrLink);
			}
			else if (TitleLink.LINK_REL_FIRST.equals(rel)) {
				bookList.setFirst(abstrLink);
			}
			else if (TitleLink.LINK_REL_PREVIOUSE.equals(rel)) {
				bookList.setPrevious(abstrLink);
			}
			else if (TitleLink.LINK_REL_NEXT.equals(rel)) {
				bookList.setNext(abstrLink);
			}
			else if (TitleLink.LINK_REL_LAST.equals(rel)) {
				bookList.setLast(abstrLink);
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
