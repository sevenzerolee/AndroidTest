package test;

import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.foxit.ninemonth.bookstore.http.Http;
import com.foxit.ninemonth.bookstore.parsexml.SAXXmlUtil;
import com.foxit.ninemonth.bookstore.parsexml.entry.AbstrResponse;
import com.foxit.ninemonth.bookstore.parsexml.entry.ads.AbstrAdsColumn;
import com.foxit.ninemonth.bookstore.parsexml.handler.AdsResponseHandler;

public class AdsResponseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String url = null;
		url = "http://www.9yue.com/wxdrip/serviceservlet?version=1.0&requestData=PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48cmVxdWVzdERhdGE%2BPHNlcnZpY2VUeXBlPmFkQ29sdW1uPC9zZXJ2aWNlVHlwZT48c2VydmljZVBhcmFtZXRlcnM%2BPHJlY29tbWVuZExvY2F0aW9uPjRsdW48L3JlY29tbWVuZExvY2F0aW9uPjwvc2VydmljZVBhcmFtZXRlcnM%2BPC9yZXF1ZXN0RGF0YT4%3D";
		
		InputSource source = Http.getInputSource(url);
		
		AdsResponseHandler handler = new AdsResponseHandler();
		
		XMLReader reader = SAXXmlUtil.getXmlReader();
		reader.setContentHandler(handler);
		reader.parse(source);
		
		AbstrResponse<List<AbstrAdsColumn>> res = handler.getResponse();
		
		System.out.println(
				res.isSuccess() + ", " + res.getVersion() + ", " + res.getResponseCode() + ", " 
				+ res.getResultMessage() + ", " + res.getEncryptKey() + ", " 
				+ res.getEncryptType() + "\n"
				);
		
		List<AbstrAdsColumn> list = res.getContent();
		
		
		for (AbstrAdsColumn c : list) {
			System.out.println(c.getAdColumnType() + ", " + c.getAdColumnUrl() + ", "
					+ c.getId() + ", " + c.getLinkTo() + ", " + c.getRecommendLocation() + "\n"
					);
//			System.out.println(c.getContent());
//			break;
		}

	}

}
