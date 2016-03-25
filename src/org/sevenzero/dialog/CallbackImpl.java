package org.sevenzero.dialog;

import android.app.Activity;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-7-2
 *
 */
public class CallbackImpl implements Callback {
	
	private static final String TAG = CallbackImpl.class.getSimpleName();
	
	private Activity context;
	
	public CallbackImpl(Activity context) {
		this.context = context;
	}

	@Override
	public String callback(String msg) {
		PasswordDialog dialog = new PasswordDialog(context);
		dialog.setFlag(0);
		dialog.showDialog();
		Util.printLog(TAG, "end dialog");
		
		int count = 0;
//		while (dialog.getFlag() != 2) {
//			try {
//				Thread.sleep(1000L);
//				Util.printLog(TAG, "wait ... " + count ++);
//			}
//			catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		Util.printLog(TAG, dialog.getPassword());
		
		return "password from callback " + dialog.getPassword();
	}

}
