package org.sevenzero.dialog;

import org.sevenzero.R;

import com.foxit.util.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

/**
 * 定义对话框
 * 
 * @author linger
 *
 * @since 2015-7-1
 *
 */
public class PasswordDialog extends Dialog {
	
	private static final String TAG = PasswordDialog.class.getSimpleName();
	
	public static final int DIALOG_RESULT_OK = 2;
	public static final int DIALOG_RESULT_CANCEL = 3;
	
	public int flag = 0;
	
	private int result;
	private Handler handler;
	private String password;
	
	private EditText text;

	public PasswordDialog(Activity context) {
		super(context);
		Util.printLog(TAG, "init");
		
		setOwnerActivity(context);
		this.setTitle(R.string.passwd_dialog_title);
		this.setCancelable(false);
		onCreate();
		text = (EditText) findViewById(R.id.passwdText);
	}

	public String getPassword() {
		return password;
	}

	public int getFlag() {
		Util.printLog(TAG, "get flag = " + flag);
		return flag;
	}

	public void setFlag(int flag) {
		Util.printLog(TAG, "init flag = " + flag);
		this.flag = flag;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	void endDialog(int result) {
		Util.printLog(TAG, "enddialog " + result);
		dismiss();
		setResult(result);
		Message msg = handler.obtainMessage();
		handler.sendMessage(msg);
	}
	
	public int showDialog() {
		Util.printLog(TAG, "showdialog");
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
//				super.handleMessage(msg);
//				throw new RuntimeException();
				Util.printLog(TAG, "handlemessage");
			}
		};
		super.show();
		
		try {
			Looper.getMainLooper();
			Util.printLog(TAG, "mainlooper " + result);
		}
		catch (RuntimeException e) {
			Util.printLog(TAG, "E");
		}
		Util.printLog(TAG, "showdialog " + result);
		return result;
	}
	
	public void onCreate() {
		Util.printLog(TAG, "oncreate");
		setContentView(R.layout.dialog_password);
		findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				endDialog(DIALOG_RESULT_CANCEL);
				
			}
		});
		
		findViewById(R.id.okBtn).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				endDialog(DIALOG_RESULT_OK);
				password = text.getText().toString();
				flag = 2;
				Util.printLog(TAG, password);
			}
		});
	}

}
