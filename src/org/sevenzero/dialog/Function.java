package org.sevenzero.dialog;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-7-2
 *
 */
public class Function {
	
	private static final String TAG = Function.class.getSimpleName();
	
	private Callback call;
	
	public void setCall(Callback call) {
		this.call = call;
	}

	public String doSomething(String data) {
		Util.printLog(TAG, data);
		String value = call.callback("callback");
		
		Util.printLog(TAG, value);
		return "Function ==> " + data + ", Callback ==> " + value;
	}

}
