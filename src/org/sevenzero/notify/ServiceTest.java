package org.sevenzero.notify;

import com.foxit.util.Util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServiceTest extends Service {
	
	private static final String tag = ServiceTest.class.getSimpleName();
	
	public static final String ACTION = "org.sevenzero.notify.ServiceTest";

	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		Util.printLog(tag, "onstart");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Util.printLog(tag, "onstartcommand");
		new ServiceThread().start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		Util.printLog(tag, "ondestroy");
		super.onDestroy();
	}
	
	public static int count = 0;
	
	class ServiceThread extends Thread {
		
		@Override
		public void run() {
			int c = ++count;
			Util.printLog(tag, "" + c);
			NotifyTest nt = new NotifyTest(getApplicationContext(), c);
			nt.testNotify();
			
//			try {
//				Thread.sleep(3000L);
//			}
//			catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
	}

}
