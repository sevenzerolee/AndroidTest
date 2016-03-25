package test;

import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.foxit.ninemonth.bookstore.http.Http;
import com.foxit.ninemonth.bookstore.parsexml.SAXXmlUtil;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrBookCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrFeedCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.handler.BookCategoryHandler;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-21
   *
 */
public class BookCategoryTest {

	/**
	 * @param args
	 * @throws  
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
//		InputStream input = new FileInputStream("/home/linger/android/9yue/xml/feed.xml");
//		InputSource source = new InputSource(input);
		
		String url = null;
		url = "http://opds.9yue.com/9yue.com/category.atom?category=2";		
		InputSource source = Http.getInputSource(url);
		
		BookCategoryHandler handler = new BookCategoryHandler();
		
		XMLReader reader = SAXXmlUtil.getXmlReader();
		reader.setContentHandler(handler);
		reader.parse(source);
		
		AbstrBookCategory c = handler.getBookCategory();
		
		AbstrLink link = c.getSearch();
		AbstrFeedCategory fc = c.getShelf();
		
		System.out.println(
				c.getTitle() + ", " + c.getId() + ", " + c.getUpdated() + "\n\n"
				+ link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType() + "\n\n" 
				+ fc.getTitle() + ", " + fc.getId() + ", " + fc.getUpdated() + "\n" 
				+ fc.getDetail().getLinkHref() + ", " + fc.getDetail().getLinkRel() + ", " + fc.getDetail().getLinkType() + "\n\n"
				);
		
		List<AbstrFeedCategory> list = c.getList();
		for (AbstrFeedCategory fc1 : list) {
			System.out.println(
					fc1.getTitle() + ", " + fc1.getId() + ", " + fc1.getUpdated() + "\n" 
					+ fc1.getDcIssued() + ", " + fc1.getDcLanguage() + "\n"  
					+ fc1.getDetail().getLinkHref() + ", " + fc1.getDetail().getLinkRel() + ", " + fc1.getDetail().getLinkType() + "\n"
			);
		}
		

	}

}
