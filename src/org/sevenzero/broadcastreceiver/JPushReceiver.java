package org.sevenzero.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import cn.jpush.android.api.JPushInterface;

import com.foxit.util.Util;


/**
 * 
 * @author linger
 * 
 * @since 2015-10-21
 * 
 *        推送信息接收
 * 
 */
public class JPushReceiver extends BroadcastReceiver {

	private static final String tag = JPushReceiver.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		Util.printLog(tag, "接收推送信息");

		Bundle bundle = intent.getExtras();
		Util.printLog(tag, "[MyReceiver] onReceive - " + intent.getAction()
				+ ", extras: " + printBundle(bundle));

		if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
			String regId = bundle
					.getString(JPushInterface.EXTRA_REGISTRATION_ID);
			Util.printLog(tag, "[MyReceiver] 接收Registration Id : " + regId);
			// send the Registration Id to your server...

		}
		else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
				.getAction())) {
			Util.printLog(tag,
					"[MyReceiver] 接收到推送下来的自定义消息: "
							+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
//			processCustomMessage(context, bundle);

		}
		else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
				.getAction())) {
			Util.printLog(tag, "[MyReceiver] 接收到推送下来的通知");
			int notifactionId = bundle
					.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
			Util.printLog(tag, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
			
			if (null != context) {
				Util.printLog(tag, "保存推送数据");
				
				
			}
			
		}
		else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
				.getAction())) {
			Util.printLog(tag, "[MyReceiver] 用户点击打开了通知");


		}
		else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
				.getAction())) {
			Util.printLog(tag,
					"[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
							+ bundle.getString(JPushInterface.EXTRA_EXTRA));
			// 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
			// 打开一个网页等..

		}
		else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
				.getAction())) {
			boolean connected = intent.getBooleanExtra(
					JPushInterface.EXTRA_CONNECTION_CHANGE, false);
			Util.printLog(tag, "[MyReceiver]" + intent.getAction()
					+ " connected state change to " + connected);
		}
		else {
			Util.printLog(tag, "[MyReceiver] Unhandled intent - " + intent.getAction());
		}
	}
	
	
	
	
	
	
	// 打印所有的 intent extra 数据
	static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}
			else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			}
			else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		sb.append("\n]");
		
		return sb.toString();
	}
	

}




