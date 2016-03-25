package org.sevenzero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Xml;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-6-9
 * 
 * 测试代码
   *
 */
public class TestActivity extends Activity {
	
	static final Logger log = Logger.getAnonymousLogger();
	
	static final int REQUEST_CODE = 2;
	
	private Button btnTest;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        
        
        btnTest = (Button) this.findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(TestActivity.this, BackActivity.class);
				intent.putExtra("goto", "in");
				TestActivity.this.startActivityForResult(intent, REQUEST_CODE);
				
			}
		});
        
        getPhoneInfo();
        getNetInfo();
        
    }
    
    void xmlTest() {
    	Xml.newPullParser();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	log.info("++++++++");
    	log.info("" + requestCode + " " + resultCode + " " + data);
    	
    }
    
    void getVersionInfo() throws NameNotFoundException {
    	PackageManager manager = this.getPackageManager();
    	manager.getPackageInfo(this.getPackageName(), 0);
    }
    
    boolean getNetInfo() {
    	ConnectivityManager cManager=(ConnectivityManager) this.getSystemService(
    			Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			// do something
			// 能联网
			log.info("能联网");
			log.info(info.getTypeName());
			log.info(info.getDetailedState().name());
			return true;
		}
		else {
			// do something
			// 不能联网
			log.info("不能联网");
			return false;
		}
    }
    
    void getPhoneInfo() {
    	// 分辨率大小
    	Display display = getWindowManager().getDefaultDisplay();
    	log.info("w = " + display.getWidth() + ", h = " + display.getHeight());
    	
    	TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
    	// device id
    	log.info("device id = " + tm.getDeviceId());
    	log.info("device software version = " + tm.getDeviceSoftwareVersion());
    	log.info("net type = " + tm.getNetworkType());
    	log.info("line number = " + tm.getLine1Number());
    	
    	log.info(Build.MODEL);
    	log.info(Build.BRAND);
    	log.info(Build.BOARD);
    	log.info(Build.VERSION.SDK);
    	log.info(Build.VERSION.RELEASE);
    }
    
	// SDcard 操作
	public void SDCardTest() {
		// 获取扩展SD卡设备状态
		String sDStateString = android.os.Environment.getExternalStorageState();

		// 拥有可读可写权限
		if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {

			try {

				// 获取扩展存储设备的文件目录
				File SDFile = android.os.Environment
						.getExternalStorageDirectory();

				// 打开文件
				File myFile = new File(SDFile.getAbsolutePath()
						+ File.separator + "MyFile.txt");

				// 判断是否存在,不存在则创建
				if (!myFile.exists()) {
					myFile.createNewFile();
				}

				// 写数据
				String szOutText = "Hello, World!";
				FileOutputStream outputStream = new FileOutputStream(myFile);
				outputStream.write(szOutText.getBytes());
				outputStream.close();

			}
			catch (Exception e) {
				// TODO: handle exception
			}// end of try

		}// end of if(MEDIA_MOUNTED)
			// 拥有只读权限
		else if (sDStateString
				.endsWith(android.os.Environment.MEDIA_MOUNTED_READ_ONLY)) {

			// 获取扩展存储设备的文件目录
			File SDFile = android.os.Environment.getExternalStorageDirectory();

			// 创建一个文件
			File myFile = new File(SDFile.getAbsolutePath() + File.separator
					+ "MyFile.txt");

			// 判断文件是否存在
			if (myFile.exists()) {
				try {

					// 读数据
					FileInputStream inputStream = new FileInputStream(myFile);
					byte[] buffer = new byte[1024];
					inputStream.read(buffer);
					inputStream.close();

				}
				catch (Exception e) {
					// TODO: handle exception
				}// end of try
			}// end of if(myFile)
		}// end of if(MEDIA_MOUNTED_READ_ONLY)
	}// end of func
	
	public void SDCardSizeTest() {

		// 取得SDCard当前的状态
		String sDcString = android.os.Environment.getExternalStorageState();

		if (sDcString.equals(android.os.Environment.MEDIA_MOUNTED)) {

			// 取得sdcard文件路径
			File pathFile = android.os.Environment
					.getExternalStorageDirectory();

			android.os.StatFs statfs = new android.os.StatFs(pathFile.getPath());

			// 获取SDCard上BLOCK总数
			long nTotalBlocks = statfs.getBlockCount();

			// 获取SDCard上每个block的SIZE
			long nBlocSize = statfs.getBlockSize();

			// 获取可供程序使用的Block的数量
			long nAvailaBlock = statfs.getAvailableBlocks();

			// 获取剩下的所有Block的数量(包括预留的一般程序无法使用的块)
			long nFreeBlock = statfs.getFreeBlocks();

			// 计算SDCard 总容量大小MB
			long nSDTotalSize = nTotalBlocks * nBlocSize / 1024 / 1024;

			// 计算 SDCard 剩余大小MB
			long nSDFreeSize = nAvailaBlock * nBlocSize / 1024 / 1024;
		}// end of if
	}// end of func
    
}

