package test;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.foxit.ninemonth.bookstore.http.Http;
import com.foxit.ninemonth.bookstore.parsexml.SAXXmlUtil;
import com.foxit.ninemonth.bookstore.parsexml.entry.book.AbstrBookDetail;
import com.foxit.ninemonth.bookstore.parsexml.entry.bookinfo.AbstrBookInfo;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLengthLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrLink;
import com.foxit.ninemonth.bookstore.parsexml.entry.link.AbstrTitleLink;
import com.foxit.ninemonth.bookstore.parsexml.handler.BookInfoHandler;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-20
   *
 */
public class BookInfoTest {

	/**
	 * @param args
	 * @throws  
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {
		
		
		String url = null;
		url = "http://opds.9yue.com/9yue.com/detail/61098.atom";
		
		InputSource source = Http.getInputSource(url);
		
		BookInfoHandler handler = new BookInfoHandler();
		
		XMLReader reader = SAXXmlUtil.getXmlReader();
		reader.setContentHandler(handler);
		reader.parse(source);
		
		AbstrBookInfo info = handler.getBookInfo();
		
		AbstrLink link = info.getSelf();
		System.out.println(
				info.getId() + ", " + info.getTitle() + ", " + info.getUpdated() + "\n"
				+ link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType() + "\n"
				);
		
		AbstrBookDetail detail = info.getBookDetail();
		System.out.println(
				detail.getId() + ", " + detail.getTitle() + ", " + detail.getUpdated() + "\n"
				+ detail.getAuthorName() + ", " + detail.getCateLabel() + ", " + detail.getCateTerm() + "\n"
				+ detail.getDcIdentifier() + ", " + detail.getDcIssued() + ", " + detail.getDcLanguage() + ", " + detail.getDcPublisher() + "\n " 
				+ detail.getContentType() + ", " + detail.getContent() + "\n"
				
				);
		
		// comment link
		AbstrTitleLink title = detail.getComment();
		if (null != title) {
			System.out.println(
					title.getLinkTitle() + ", " + title.getLinkHref() + "\n"
					+ title.getLinkRel() + ", " + title.getLinkType()
					);
		}
		
		// image link
		link = detail.getImage();
		System.out.println(
				link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
				);
		
		// thumb link
		link = detail.getThumb();
		System.out.println(
				link.getLinkHref() + ", " + link.getLinkRel() + ", " + link.getLinkType()
				);
		
		// epub link
		AbstrLengthLink length = detail.getEpub();
		if (null != length) {
			System.out.println(
					length.getLinkTitle() + ", " + length.getLinkHref() + "\n" 
					+ length.getLinkRel() + ", " + length.getLinkType() + ", " + length.getLinkLength()
					);
		}
		
		// pdf link
		length = detail.getPdf();
		if (null != length) {
			System.out.println(
					length.getLinkTitle() + ", " + length.getLinkHref() + "\n" 
					+ length.getLinkRel() + ", " + length.getLinkType() + ", " + length.getLinkLength()
					);
		}

	}

}
