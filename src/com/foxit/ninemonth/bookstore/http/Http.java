package com.foxit.ninemonth.bookstore.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.xml.sax.InputSource;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-9
   *
 */
public class Http {
	
	private static final int RECYCLE_COUNT = 3;
	private static final long SLEEP_TIME = 1000L;
	
	private static Logger log = Logger.getLogger(Http.class.getSimpleName());
	
	/**
	 * Get InputStream
	 * 
	 * @param url
	 * @return
	 */
	public static InputStream getInputStream(String url) {		
		URL httpUrl = null;
		HttpURLConnection urlConn = null;
		try {
			httpUrl = new URL(url);
			urlConn = (HttpURLConnection) httpUrl.openConnection();
			long start = System.currentTimeMillis();
			InputStream input = urlConn.getInputStream();
			long end = System.currentTimeMillis();
			log.info("net time costs : " + (end - start));

			return input;
		}
		catch (IOException e) {
			log.info("Http-Exception.");
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * Get InputSource
	 * 
	 * @param url
	 * @return
	 */
	public static InputSource getInputSource(String url) {
		InputStream input = getInputStream(url);
		if (null == input) {
			int n = 0;
			do {
				try {
					Thread.sleep(SLEEP_TIME);
				}
				catch (InterruptedException e) {
					log.info("Thread-Exception.");
				}
				
				input = getInputStream(url);
				
			} while (null == input && n++ < RECYCLE_COUNT);
			
			if (null == input) {
				return null;
			}
			log.info("n = " + n);
		}
		
		return new InputSource(input);
	}
	
	/**
	 * Convert InputStream to String
	 * 
	 * @param input
	 * @return
	 */
	public static String getStrContent(InputStream input) {
		if (null == input) {
			return null;
		}
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(input));
			StringBuffer sb = new StringBuffer(2048);
			String line = null;

			while (null != br && null != (line = br.readLine())) {
				sb.append(line).append("\n");
			}

			return sb.toString();
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		finally {
			if (null != br) {
				try {
					br.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

}
