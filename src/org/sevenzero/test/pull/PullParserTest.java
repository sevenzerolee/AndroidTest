package org.sevenzero.test.pull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

import org.sevenzero.log.Log;
import org.sevenzero.pull.Book;
import org.sevenzero.pull.PullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.test.AndroidTestCase;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-11-12
   *
 */
public class PullParserTest extends AndroidTestCase {
	
	static Logger log = Logger.getLogger(PullParser.class.getSimpleName());
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testPull() {
//		InputStream input = PullParserTest.class.getResourceAsStream("/books.xml");
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("books.xml");
		Log.log(log, input.toString());
		PullParser pull = new PullParser();
		try {
			List<Book> list = pull.parse(input);
			Log.log(log, list.toString());
			
			String xml = pull.serialize(list);
			Log.log(log, xml);
		}
		catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (null != input) {
				try {
					input.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	

}
