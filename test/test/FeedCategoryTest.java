package test;

import org.xml.sax.InputSource;

import com.foxit.ninemonth.bookstore.http.Http;
import com.foxit.ninemonth.bookstore.parsexml.SAXXmlUtil;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.feed.AbstrFeedCategory;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.handler.FeedCategoryHandler;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-9
   *
 */
public class FeedCategoryTest {

	/**
	 * @param args
	 * @throws  
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
//		InputStream input = new FileInputStream("/home/linger/android/9yue/xml/feed.xml");
//		InputSource source = new InputSource(input);
		
		String url = null;
		url = "http://opds.9yue.com/9yue.com/category.atom";		
		InputSource source = Http.getInputSource(url);
		
		FeedCategoryHandler handler = new FeedCategoryHandler();
		
//		XMLReader reader = SAXXmlUtil.getXmlReader();
//		reader.setContentHandler(handler);
//		reader.parse(source);
		
		boolean result = SAXXmlUtil.parser(handler, source);
		if (!result) {
			System.out.println("parser fail ...");
			return;
		}
		
		AbstrCategory c = handler.getCategory();
		
		AbstrLink link = c.getSearch();
		AbstrFeedCategory fc = c.getShelf();
		
		System.out.println(
				c.getTitle() + ", " + c.getId() + ", " + c.getUpdated() + "\n\n"
				+ link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType() + "\n\n" 
				+ fc.getTitle() + ", " + fc.getId() + ", " + fc.getUpdated() + "\n" 
				+ fc.getDetail().getLinkHref() + ", " + fc.getDetail().getLinkRel() + ", " + fc.getDetail().getLinkType() + "\n"
				);
		
		// recommended
		fc = c.getRecommended();
		System.out.println(
				fc.getTitle() + ", " + fc.getId() + ", " + fc.getUpdated() + "\n" 
				+ fc.getDetail().getLinkHref() + ", " + fc.getDetail().getLinkRel() + ", " + fc.getDetail().getLinkType() + "\n"
		);
		
		// facet
		fc = c.getFacet();
		System.out.println(
				fc.getTitle() + ", " + fc.getId() + ", " + fc.getUpdated() + "\n" 
				+ fc.getDetail().getLinkHref() + ", " + fc.getDetail().getLinkRel() + ", " + fc.getDetail().getLinkType() + "\n"
		);
		
		// category
		fc = c.getCategory();
		System.out.println(
				fc.getTitle() + ", " + fc.getId() + ", " + fc.getUpdated() + "\n" 
				+ fc.getDetail().getLinkHref() + ", " + fc.getDetail().getLinkRel() + ", " + fc.getDetail().getLinkType() + "\n"
		);

	}

}
