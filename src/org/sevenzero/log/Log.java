package org.sevenzero.log;

import java.util.logging.Logger;

/**
 * 
 * @author sevenzero
 *
 * @since 2012-10-29 
 * 
 */
public class Log {
	
	private static final boolean DEBUG = true;
	
	private Log() {}
	
	public static void log(final Logger log, String content) {
		if (DEBUG) {
			if (null == content) {
				log.info(null);
			}
			else {
				log.info(content);
			}
		}
	}
	
	public static void log(final Logger log, Object obj) {
		if (DEBUG) {
			if (null == obj) {
				log.info(null);
			}
			else {
				log.info(obj.toString());
			}
		}
	}

}
