package test;

import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.foxit.ninemonth.bookstore.http.Http;
import com.foxit.ninemonth.bookstore.parsexml.SAXXmlUtil;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookSummary;
import com.foxit.ninemonth.bookstore.parsexml.entry.booklist.AbstrSearchBookList;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrTitleLink;
import com.foxit.ninemonth.bookstore.parsexml.handler.SearchBookListHandler;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-17
   *
 */
public class SearchBookListTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
//		InputStream input = new FileInputStream("/home/linger/android/9yue/xml/recommanded.xml");
//		InputSource source = new InputSource(input);
		
		String url = null;
		url = "http://opds.9yue.com/9yue.com/search.atom?key=";	
		InputSource source = Http.getInputSource(url);
		
		SearchBookListHandler handler = new SearchBookListHandler();
		
		XMLReader reader = SAXXmlUtil.getXmlReader();
		reader.setContentHandler(handler);
		reader.parse(source);
		
		AbstrSearchBookList bList = handler.getBookList();
		System.out.println(
				bList.getTitle() + ", " + bList.getId() + ", " + bList.getUpdated() + "\n" 
				+ bList.getTotalResults() + ", " + bList.getItemsPerPage() + "\n" 
				+ bList.getQueryRole() + ", " + bList.getQueryKey() + ", " + bList.getQueryStartPage() + "\n"
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
		AbstrTitleLink title = summary.getDetail();
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
