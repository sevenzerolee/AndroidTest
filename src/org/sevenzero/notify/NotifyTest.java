package org.sevenzero.notify;

import org.sevenzero.BackActivity;
import org.sevenzero.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotifyTest {
	
	private int c;
	private Context context;
	
	public NotifyTest(Context context, int c) {
		this.c = c;
		this.context = context;
		
	}
	
	public void testNotify() {
		String tickerText = "标题-" + c;
		String contentTitle = "内容标题-" + c;
		String contentText = "内容-" + c;
		NotificationManager nManager = (NotificationManager) context.getSystemService(
				Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, 
				tickerText, System.currentTimeMillis());
		notification.defaults = Notification.DEFAULT_ALL;
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		
//		Context ctx = getApplicationContext();
		Intent intent = new Intent(context, BackActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
         
        //用mNotificationManager的notify方法通知用户生成标题栏消息通知
        nManager.notify(c, notification);
	}

}
