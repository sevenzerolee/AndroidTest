package test;

import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.foxit.ninemonth.bookstore.http.Http;
import com.foxit.ninemonth.bookstore.parsexml.SAXXmlUtil;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookSummary;
import com.foxit.ninemonth.bookstore.parsexml.entry.booklist.AbstrBookList;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrTitleLink;
import com.foxit.ninemonth.bookstore.parsexml.handler.BookListHandler;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public class BookListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
//		InputStream input = new FileInputStream("/home/linger/android/9yue/xml/recommanded.xml");
//		InputSource source = new InputSource(input);
		
		String url = null;
		url = "http://opds.9yue.com/9yue.com/book.atom?category=1";
		url = "http://opds.9yue.com/9yue.com/book.atom?category=1&itemsPerPage=25";
		InputSource source = Http.getInputSource(url);
		
		BookListHandler handler = new BookListHandler();
		
		XMLReader reader = SAXXmlUtil.getXmlReader();
		reader.setContentHandler(handler);
		reader.parse(source);
		
		AbstrBookList bList = handler.getBookList();
		System.out.println(
				bList.getTitle() + ", " + bList.getId() + ", " + bList.getUpdated() + "\n" 
				+ bList.getTotalResults() + ", " + bList.getItemsPerPage() + "\n"
				);
		
		// self link
		AbstrLink link = bList.getSelf();
		System.out.println(
				link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
				);
		
		// search link
		link = bList.getSearch();
		System.out.println(
				link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
				);
		
		// parent link
		link = bList.getParent();
		if (null != link) {
			System.out.println(
					link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
					);
		}
		
		// first link 
		AbstrTitleLink title = bList.getFirst();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		// previous link
		title = bList.getPrevious();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		// next link
		title = bList.getNext();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		// last link
		title = bList.getLast();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		List<AbstrBookSummary> list = bList.getList();
		System.out.println(list.size());
		
		AbstrBookSummary summary = list.get(2);
		
		System.out.println(
				summary.getAuthorName() + ", " + summary.getCateTerm() + ", " + summary.getCateLabel() + "\n"
				+ summary.getId() + ", " + summary.getTitle() + ", " + summary.getUpdated() + "\n" 
				+ summary.getDcIdentifier() + ", "
				+ summary.getDcIssued() + ", " + summary.getDcLanguage() + ", " + summary.getDcPublisher() + "\n"
				);
		
		// detail link
		title = summary.getDetail();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		// comment link
		title = summary.getComment();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		// image link
		link = summary.getImage();
		System.out.println(
				link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
				);
		
		// thumb link
		link = summary.getThumb();
		System.out.println(
				link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
				);
		
		// epub link
		AbstrLengthLink length = summary.getEpub();
		if (null != length) {
			System.out.println(
					length.getLinkTitle() + ", " + length.getLinkHref() + "\n" 
					+ length.getLinkRel() + ", " + length.getLinkType() + ", " + length.getLinkLength()
					);
		}
		
		// pdf link
		length = summary.getPdf();
		if (null != length) {
			System.out.println(
					length.getLinkTitle() + ", " + length.getLinkHref() + "\n" 
					+ length.getLinkRel() + ", " + length.getLinkType() + ", " + length.getLinkLength()
					);
		}

	}

}
