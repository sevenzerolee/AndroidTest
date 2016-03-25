package org.sevenzero;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.sevenzero.baidu.BaiduMapTestActivity;
import org.sevenzero.dialog.CityChangeWindow;
import org.sevenzero.dialog.DefineDialog;
import org.sevenzero.floattest.FloatMainActivity;
import org.sevenzero.fragment.FragmentMainActivity;
import org.sevenzero.image.ImageDragDropTest;
import org.sevenzero.image.ImageTwoTest;
import org.sevenzero.image.ImageViewActivity;
import org.sevenzero.image.Zoom2Activity;
import org.sevenzero.image.Zoom3Activity;
import org.sevenzero.image.Zoom4Activity;
import org.sevenzero.image.ZoomActivity;
import org.sevenzero.list.GroupListActivity;
import org.sevenzero.menu.Menu2Activity;
import org.sevenzero.menu.MenuActivity;
import org.sevenzero.notify.NotifyTest;
import org.sevenzero.notify.PollUtils;
import org.sevenzero.notify.ServiceTest;
import org.sevenzero.view.ViewPagerActivity;

import util.DateTimeDialog;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.alipay.sdk.app.PayTask;
import com.foxit.util.Util;
import com.handmark.pulltorefresh.samples.PullToRefreshListActivity;
import com.markupartist.android.example.pulltorefresh.PullToRefreshActivity;
import com.readystatesoftware.viewbadger.BadgeView;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-8-14
   *
 */
public class BackActivity extends Activity {
	
	private static final String TAG = BackActivity.class.getSimpleName();
	
	String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
	
	// 标题懒
	private TextView titleLeft, title, titleRight;
	
	private Button back, alert, myDefineDialog, alertDialog;
	private Button dialog, btnProgressBar;
	
	private Button btnZoom, btnZoom2, btnZoom3, btnZoom4;
	private Button btnDrag, btnImage, btnGroupList, btnGridView;
	private Button btnViewPager, btnViewPager2;
	private Button btnImageView, btnCircleImageView;
	
	// 菜单测试
	private Button btnMenu, btnMenuPop;
	
	private EditText etAccount, etPassword;
	private String username, password;
	
	private Button btnSetting, btnPreference;
	private Button btnFloat;
	private Button btnNotify;
	private Button btnStartService, btnStopService;
	
	private Button dateTime;
	
	// 拍照上传 
	private Button btnPhotoUpload, btnPhotoUpload2;
	
	// 百度地图 
	private Button btnBaiduMap;
	
	// 日期
	private Button btnDate;
	
	private Button btnListViewTest, btnListViewTest2, btnListViewTest3;
	
	// 异步任务 
	private Button btnAsyncTask, getcode;
	
	// 右上角角标测试 
	private TextView showBadgeTest;
	private LinearLayout showBadgeLayout;
	
	private static int count = 0;
	
	// ShareSDK 
	private Button shareSdk; 
	// Fragment 
	private Button fragment;
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "start 2");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onrestart 22");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "stop 5");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "destroy 6");
	}
	
	@Override
	protected void onResume() {
		super.onResume();;
		JPushInterface.onResume(this);
		Log.d(TAG, "resume 3");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(this);
		Log.d(TAG, "pause 4");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "create 0");
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		this.setContentView(R.layout.activity_back);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_titlebar);
		
		titleLeft  = (TextView) findViewById(R.id.titleLeft);
		title      = (TextView) findViewById(R.id.title);
		titleRight = (TextView) findViewById(R.id.titleRight);
		titleLeft  .setOnClickListener(onClickLstener);
		title      .setOnClickListener(onClickLstener);
		titleRight .setOnClickListener(onClickLstener);
		
		showBadgeTest = (TextView) findViewById(R.id.showBadgeTest);
		showBadgeLayout = (LinearLayout) findViewById(R.id.showBadgeLayout);
		BadgeView badge = new BadgeView(this, showBadgeLayout);
		badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//		Color.parseColor("#A4C639")
//		badge.setBackgroundColor(Color.parseColor("#00000000"));
		badge.setBackgroundResource(R.drawable.shape_badge);
		badge.setTextSize(12);
		badge.setTextColor(Color.RED);
		badge.setText("1");
		badge.show();
		
		dateTime = (Button) findViewById(R.id.dateTime);
		dateTime.setOnClickListener(listener);
		
		btnMenu = (Button) this.findViewById(R.id.btnMenu);
		btnMenu.setOnClickListener(listener);
		btnMenuPop = (Button) this.findViewById(R.id.btnMenuPop);
		btnMenuPop.setOnClickListener(listener);
		
		btnSetting = (Button) this.findViewById(R.id.btnSetting);
		btnSetting.setOnClickListener(listener);
		btnPreference = (Button) this.findViewById(R.id.btnPreference);
		btnPreference.setOnClickListener(listener);
		
		btnFloat = (Button) this.findViewById(R.id.btnFloat);
		btnFloat.setOnClickListener(listener);
		
		btnNotify = (Button) this.findViewById(R.id.btnNotify);
		btnNotify.setOnClickListener(listener);
		
		btnStartService = (Button) this.findViewById(R.id.btnStartService);
		btnStartService.setOnClickListener(listener);
		btnStopService  = (Button) this.findViewById(R.id.btnStopService);
		btnStopService .setOnClickListener(listener);
		
		btnPhotoUpload = (Button) this.findViewById(R.id.btnPhotoUpload);
		btnPhotoUpload.setOnClickListener(listener);
		btnPhotoUpload2 = (Button) this.findViewById(R.id.btnPhotoUpload2);
		btnPhotoUpload2.setOnClickListener(listener);
		
		btnBaiduMap = (Button) this.findViewById(R.id.btnBaiduMap);
		btnBaiduMap.setOnClickListener(listener);
		
		btnDate = (Button) this.findViewById(R.id.btnDate);
		btnDate.setOnClickListener(listener);
		
		btnListViewTest = (Button) this.findViewById(R.id.btnListViewTest);
		btnListViewTest.setOnClickListener(listener);
		btnListViewTest2 = (Button) this.findViewById(R.id.btnListViewTest2);
		btnListViewTest2.setOnClickListener(listener);
		btnListViewTest3 = (Button) this.findViewById(R.id.btnListViewTest3);
		btnListViewTest3.setOnClickListener(listener);
		
		btnAsyncTask = (Button) this.findViewById(R.id.btnAsyncTask);
		btnAsyncTask.setOnClickListener(listener);
		getcode = (Button) findViewById(R.id.getcode);
		getcode.setOnClickListener(listener);
		
		back = (Button) this.findViewById(R.id.buttonBack);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = getIntent();
				intent.putExtra("back", "out");
//				setResult(TestActivity.REQUEST_CODE);
				setResult(TestActivity.REQUEST_CODE, intent);
				
				finish();
				
			}
		});
		
		alert = (Button) this.findViewById(R.id.btnAlert);
		alert.setOnClickListener(listener);
		
		myDefineDialog = (Button) this.findViewById(R.id.btnMyDefineDialog);
		myDefineDialog.setOnClickListener(listener);
		
		alertDialog = (Button) this.findViewById(R.id.btnAlertDialog);
		alertDialog.setOnClickListener(listener);
		
		btnZoom = (Button) findViewById(R.id.btnZoom);
		btnZoom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
//				intent.setClass(BackActivity.this, MySetting.class);
				intent.setClass(BackActivity.this, ZoomActivity.class);
				BackActivity.this.startActivity(intent);
				
			}
		});
		
		btnZoom2 = (Button) findViewById(R.id.btnZoom2);
		btnZoom2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, Zoom2Activity.class);
				intent.putExtra("goto", "in");
				BackActivity.this.startActivity(intent);
				
			}
		});
		
		btnZoom3 = (Button) findViewById(R.id.btnZoom3);
		btnZoom3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, Zoom3Activity.class);
				BackActivity.this.startActivity(intent);
			}
		});
		
		btnZoom4 = (Button) findViewById(R.id.btnZoom4);
		btnZoom4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, Zoom4Activity.class);
				BackActivity.this.startActivity(intent);
			}
		});
		
		btnDrag = (Button) findViewById(R.id.btnDrag);
		btnDrag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, ImageDragDropTest.class);
				startActivity(intent);
			}
		});
		btnGroupList = (Button) findViewById(R.id.btnGroupList);
		btnGroupList.setOnClickListener(listener);
		
		btnGridView = (Button) findViewById(R.id.btnGridView);
		btnGridView.setOnClickListener(listener);
		
		btnViewPager = (Button) findViewById(R.id.btnViewPager);
		btnViewPager.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, VPMainActivity.class);
				startActivity(intent);
			}
		});
		btnViewPager2 = (Button) findViewById(R.id.btnViewPager2);
		btnViewPager2.setOnClickListener(listener);
		
		btnImage = (Button) findViewById(R.id.btnImage);
		btnImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, ImageTwoTest.class);
				startActivity(intent);
			}
		});
		
		btnCircleImageView = (Button) findViewById(R.id.btnCircleImageView);
		btnCircleImageView.setOnClickListener(listener);
		
		btnImageView = (Button) findViewById(R.id.btnImageView);
		btnImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, ImageViewActivity.class);
				startActivity(intent);
			}
		});
		
		// 进度条
		btnProgressBar = (Button) this.findViewById(R.id.btnProgressBar);
		btnProgressBar.setOnClickListener(listener);
		
		// 弹出对话框
		dialog = (Button) this.findViewById(R.id.buttonDialog);
		dialog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(BackActivity.this);
				builder.setTitle("用户信息");				
				
//				builder.setView(new EditText(BackActivity.this));
//				builder.setView(new EditText(BackActivity.this));
				LayoutInflater inflater = getLayoutInflater();
				View dialog = inflater.inflate(R.layout.mydialog, 
						(ViewGroup) findViewById(R.id.dialog));
				builder.setView(dialog);
				
				etAccount = (EditText) dialog.findViewById(R.id.etAccount2);
				etPassword = (EditText) dialog.findViewById(R.id.etPassword2);
				
				builder.setPositiveButton("ok", new AlertDialog.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						username = etAccount.getText().toString();
						password = etPassword.getText().toString();
						Toast.makeText(BackActivity.this, username + "-" + password, 
								Toast.LENGTH_SHORT).show();
					}
					
				});
				builder.setNegativeButton("cancel", new AlertDialog.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						
					}
					
				});
				builder.show();
				
			}
		});
		
		
		this.checkSdcard();
		
		Intent mIntent = new Intent(Intent.ACTION_VIEW);
		mIntent.setDataAndType(Uri.fromParts("file", "", null), "txt");
		ResolveInfo ri = getPackageManager().resolveActivity(mIntent,PackageManager.MATCH_DEFAULT_ONLY);
		Util.printLog(TAG, "resolve info " + ri);
		
		fragment = (Button) findViewById(R.id.fragment);
		fragment.setOnClickListener(listener);
		
		shareSdk = (Button) findViewById(R.id.shareSdk);
		shareSdk.setOnClickListener(listener);
		// ------------------------------------------------
		// -------------------ShareSDK---------------------
		// ------------------------------------------------
		ShareSDK.initSDK(this);
		
		
		
		
		// 初始化极光
		JPushInterface.init(getApplicationContext());
		
		int count = 0;
		while (count < 5) {
			String registerid = JPushInterface.getRegistrationID(getApplicationContext());
			Util.printLog(tag, "注册极光 ### registerid = " + registerid);
			count++;
			
			if (!Util.checkEmptyOrNull(registerid)) {
				break;
			}
			
//			try {
//				Thread.sleep(1000);
//			}
//			catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		
		
		
//		check();
		
		
		// 数据库
		
		SQLiteDatabase db = this.openOrCreateDatabase("test", Context.MODE_PRIVATE, null);
		
		
		/*
		 * datatype = 2 order; datatype = 3 url
		 * status = 2 new; status = 3 read
		 */
		String sql = "create table if not exists t_jpush_2(" +
				"_id integer primary key autoincrement, " +
				"datatype text, " +
				"time text, " +
				"content text, " + 
				"desc text, " + 
				"status integer, " +
				"pid text, " + 
				"alert text, " +
				"nid text, " +
				"title text, " + 
				"mid text" +
				")";
		try {
			db.execSQL(sql);
			
			
			Cursor cursor = db.query("t_jpush_2", null, null, null, null, null, null);
			
			count = cursor.getCount();
			Util.printLog(tag, "记录总数 " + count);
			
			ContentValues val = new ContentValues();
			for (int i=0; i<5; i++) {
//				sql = "insert into t_jpush";
				String time = Util.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
				val.put("datatype", "2" + i);
				val.put("time", time);
				val.put("status", "2");
				db.insert("t_jpush_2", null, val);
			}
			
			cursor = db.query("t_jpush_2", null, null, null, null, null, null);
			
			
			count = cursor.getCount();
			Util.printLog(tag, "记录总数 " + count);
			
			sql = "select datatype, content, status from t_jpush_2 where datatype = ? and status = ? ";
			cursor = db.rawQuery(sql, new String[]{ "20", "0"});
			count = cursor.getCount();
			Util.printLog(tag, "记录总数 2 " + count);
			
		}
		catch (SQLException e) {
			Util.printLog(tag, "创建表异常");
			e.printStackTrace();
		}
		finally {
			if (null != db) {
				db.close();
				Util.printLog(tag, "关闭数据库");
			}
		}
		
	}
	
	private static final String tag = BackActivity.class.getSimpleName();
	
	public void check() {
		Runnable checkRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask(BackActivity.this);
				// 调用查询接口，获取查询结果
				boolean isExist = payTask.checkAccountIfExist();
				Util.printLog(TAG, "支付宝账户 " + isExist);

//				Message msg = new Message();
//				msg.what = SDK_CHECK_FLAG;
//				msg.obj = isExist;
//				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();

	}
	
	void checkSdcard() {
		String state = Environment.getExternalStorageState();
		Util.printLog(TAG, state);
		Toast.makeText(this, state, Toast.LENGTH_SHORT).show();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			Util.printLog(TAG, "mounted");
		}
		else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			Util.printLog(TAG, "mounted_ro");
		}
		else {
			Util.printLog(TAG, "other");
		}
		
		String saveDir = Environment.getExternalStorageDirectory().toString();
		Util.printLog(TAG, saveDir);
		
		String lkoa = saveDir + File.separator + "2lkoa";
		File file = new File(lkoa);
		if (file.exists()) {
			Util.printLog(TAG, lkoa + " 存在");
		}
		else {
			Util.printLog(TAG, "不存在");
		}
		
		String data = Environment.getDataDirectory().toString();
		Util.printLog(TAG, "data " + data);
	}
	
	// 按两下退出（2秒之内）
//	private long exitTime = 0;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Util.printLog(TAG, "key == > back");
			
//			if ((System.currentTimeMillis() - exitTime) > 2000) {
//				Toast.makeText(getApplicationContext(), "再按一次退出程序",
//						Toast.LENGTH_SHORT).show();
//				exitTime = System.currentTimeMillis();
//			}
//			else {
//				finish();
//				System.exit(0);
//			}
//			return true;
		}
		else if (keyCode == KeyEvent.KEYCODE_HOME) {
			Util.printLog(TAG, "key == > home");
			return true;
		}
		else if (keyCode == KeyEvent.KEYCODE_MENU) {
			Util.printLog(TAG, "key == > menu");
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	// 屏蔽 home 
	// android_4_0 后失效
//	@Override
//	public void onAttachedToWindow() {
//		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
//		super.onAttachedToWindow();
//
//	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			final int id = v.getId();
			
			if (btnSetting.getId() == id) {
				Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
				intent.setAction(Settings.ACTION_SETTINGS);
				
//				ComponentName cm = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
//				intent.setComponent(cm);
//				intent.setAction("android.intent.action.VIEW");
//				startActivityForResult( intent , 0);
				
				startActivity(intent);
			}
			else if (fragment.getId() == id) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, FragmentMainActivity.class);
				startActivity(intent);
			}
			else if (shareSdk.getId() == id) {
				OnekeyShare oks = new OnekeyShare();
				 //关闭sso授权
				 oks.disableSSOWhenAuthorize(); 
				 
				// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
				 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
				 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
				 oks.setTitle(getString(R.string.app_name));
				 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
				 oks.setTitleUrl("http://sharesdk.cn");
				 // text是分享文本，所有平台都需要这个字段
				 oks.setText("我是分享文本");
				 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
				 oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
				 // url仅在微信（包括好友和朋友圈）中使用
				 oks.setUrl("http://sharesdk.cn");
				 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
				 oks.setComment("我是测试评论文本");
				 // site是分享此内容的网站名称，仅在QQ空间使用
				 oks.setSite(getString(R.string.app_name));
				 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
				 oks.setSiteUrl("http://sharesdk.cn");
				 
				// 启动分享GUI
				 oks.show(getApplicationContext());
			}
			else if (dateTime.getId() == id) {
				String dateTime = Util.formatDate(new Date(), "yyyy年MM月dd日 HH:mm");
				DateTimeDialog dialog = new DateTimeDialog(BackActivity.this, dateTime, 10);
				dialog.show();
			}
			else if (btnGridView.getId() == id) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, GridTestActivity.class);
				startActivity(intent);
			}
			else if (btnGroupList.getId() == id) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, GroupListActivity.class);
				startActivity(intent);
			}
			else if (btnViewPager2.getId() == id) {
				Intent intent = getIntent();
				intent.setClass(BackActivity.this, ViewPagerActivity.class);
				startActivity(intent);
			}
			else if (btnPreference.getId() == id) {
				Intent intent = getIntent();
				intent.setClass(BackActivity.this, MySetting.class);
				startActivity(intent);
			}
			else if (id == btnMenu.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, MenuActivity.class);
				startActivity(intent);
			}
			else if (id == btnMenuPop.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, Menu2Activity.class);
				startActivity(intent);
			}
			else if (id == btnFloat.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, FloatMainActivity.class);
				startActivity(intent);
			}
			else if (id == btnPhotoUpload.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, PhotoUploadActivity.class);
				startActivity(intent);
			}
			else if (id == btnPhotoUpload2.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, org.hybridsquad.android.photocropper.TestActivity.class);
				startActivity(intent);
			}
			else if (id == btnAsyncTask.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, AsyncTaskActivity.class);
				startActivity(intent);
			}
			else if (id == btnProgressBar.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, ProgressBarActivity.class);
				startActivity(intent);
			}
			else if (id == btnBaiduMap.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, BaiduMapTestActivity.class);
				startActivity(intent);
			}
			else if (id == myDefineDialog.getId()) {
				final DefineDialog dd = new DefineDialog(BackActivity.this);
				dd.show();
				
				final Timer timer = new Timer();  
		        TimerTask tt = new TimerTask() {
		            @Override  
		            public void run() {
		            	 if (null != timer) {
			            	 timer.cancel();
			                 timer.purge();
		            	 }
		            	 dd.dismiss();
		            }  
		        };  
		        timer.schedule(tt, 5000L);
			}
			else if (id == alert.getId()) {
				LayoutInflater inflater = getLayoutInflater();
				View view = inflater.inflate(R.layout.myprogress, (ViewGroup) findViewById(R.id.myprogress));
				
				AlertDialog.Builder b = new AlertDialog.Builder(BackActivity.this);
				b.setTitle(R.string.alertdialog_test);
				b.setView(view);
//				b.setMessage("有新版本是否更新");
				b.setCancelable(false);
				b.create();
				final Dialog dialog = b.show();
				
				final Timer timer = new Timer();  
		        TimerTask tt = new TimerTask() {
		            @Override  
		            public void run() {
		            	 if (null != timer) {
			            	 timer.cancel();
			                 timer.purge();
		            	 }
		            	 dialog.dismiss();
		            }  
		        };  
		        timer.schedule(tt, 5000L);
			}
			else if (id == btnNotify.getId()) {
				int c = ++count;
				Util.printLog(TAG, "notify ... " + c);
				
				NotifyTest nt = new NotifyTest(getApplicationContext(), c);
				nt.testNotify();
				
//				String tickerText = "标题-" + c;
//				String contentTitle = "内容标题-" + c;
//				String contentText = "内容-" + c;
//				NotificationManager nManager = (NotificationManager) getSystemService(
//						Context.NOTIFICATION_SERVICE);
//				Notification notification = new Notification(R.drawable.ic_launcher, 
//						tickerText, System.currentTimeMillis());
//				
//				Context ctx = getApplicationContext();
//				Intent intent = new Intent(BackActivity.this, BackActivity.class);
//				PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
//		        notification.setLatestEventInfo(ctx, contentTitle, contentText, contentIntent);
//		         
//		        //用mNotificationManager的notify方法通知用户生成标题栏消息通知
//		        nManager.notify(c, notification);
			}
			else if (id == btnStartService.getId()) {
				Util.printLog(TAG, "开启服务");
				PollUtils.startPollingService(getApplicationContext(), 5, ServiceTest.class, ServiceTest.ACTION);
			}
			else if (id == btnStopService.getId()) {
				Util.printLog(TAG, "关闭服务");
				PollUtils.stopPollingService(getApplicationContext(), ServiceTest.class, ServiceTest.ACTION);
			}
			else if (id == btnListViewTest.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, ListViewTest.class);
				startActivity(intent);
			}
			else if (id == btnListViewTest2.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, PullToRefreshActivity.class);
				startActivity(intent);
			}
			else if (id == btnListViewTest3.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, PullToRefreshListActivity.class);
				startActivity(intent);
			}
			else if (id == btnDate.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, DateActivity.class);
				startActivity(intent);
			}
			else if (id == alertDialog.getId()) {
				AlertDialog.Builder b2 = new AlertDialog.Builder(
						BackActivity.this)
						.setTitle("提示")
						.setMessage("是否去设置个人信息？")
						.setPositiveButton("否",
								new AlertDialog.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										
									}
								})
						.setNegativeButton("是",
								new AlertDialog.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
//										Intent intent = new Intent(
//												Settings.ACTION_WIFI_SETTINGS);
//										intent.setAction(Settings.ACTION_SETTINGS);
										// startActivity(intent);
									}
								});

//				b2.setCancelable(false);
				b2.create();
				b2.show();
			}
			else if (id == btnCircleImageView.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, CircleImageViewActivity.class);
				startActivity(intent);
			}
			else if (id == getcode.getId()) {
				Intent intent = new Intent();
				intent.setClass(BackActivity.this, GetCodeActivity.class);
				startActivity(intent);
			}
		}
	};
	
	private OnClickListener onClickLstener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.title:
				Util.printLog(tag, "title");
				
				break;
			case R.id.titleLeft:
				Util.printLog(tag, "left");
				int[] location = new int[2];  
				titleLeft.getLocationOnScreen(location);
				int height = titleLeft.getHeight();
				Util.printLog(tag, "left, x = " + location[0] + ", y = " + location[1] + ", height = " + height);
				
				CityChangeWindow city = new CityChangeWindow(BackActivity.this);
				city.showAtLocation(BackActivity.this.findViewById(R.id.container), 
						Gravity.NO_GRAVITY, location[0] + 10, location[1] + height + 10);
//				showAtLocation(MainActivity.this.findViewById(R.id.container), 
//						Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				break;
			case R.id.titleRight:
				Util.printLog(tag, "right");
				
				break;
			default:
				
				break;
			}
		}
	};
	
	
}

