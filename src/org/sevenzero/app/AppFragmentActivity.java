package org.sevenzero.app;

import org.sevenzero.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2016-1-22
 * 
 * 程序入口 
 *
 */
public class AppFragmentActivity extends Activity {
	
	private static final String tag = AppFragmentActivity.class.getSimpleName();
	
	private TextView menuFirst, menuThird, menuSecond;
	
	private FirstFragment firstFragment;
	private ThirdFragment secondFragment;
	private SecondFragment thirdFragment;
	
	/** 
     * 用于对Fragment进行管理 
     */  
    private FragmentManager fragmentManager;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_fragment);
		
		menuFirst = (TextView) findViewById(R.id.menuLeft);
		menuThird = (TextView) findViewById(R.id.menuRight);
		menuSecond = (TextView) findViewById(R.id.menuCenter);
		menuFirst.setOnClickListener(listener);
		menuThird.setOnClickListener(listener);
		menuSecond.setOnClickListener(listener);
		
//		menuLeftFragment = new MenuLeftFragment();
//		menuRightFragment = new MenuRightFragment();
		
		fragmentManager = getFragmentManager();
		showMenu(menuFirst);
		
//		FragmentTransaction ft = fragmentManager.beginTransaction();
//		ft.add(R.id.fragment_id, menuLeftFragment);
//		ft.add(R.id.fragment_id, menuRightFragment);
//		ft.show(menuLeftFragment);
//		ft.commit();
	}
	
	void hideAllMenu(FragmentTransaction ft) {
		if (null != firstFragment) {
			ft.hide(firstFragment);
		}
		if (null != secondFragment) {
			ft.hide(secondFragment);
		}
		if (null != thirdFragment) {
			ft.hide(thirdFragment);
		}
	}
	
	void resetMenu() {
		int colorId = this.getResources().getColor(R.color.bg_white);
		menuFirst.setBackgroundColor(colorId);
		menuThird.setBackgroundColor(colorId);
		menuSecond.setBackgroundColor(colorId);
	}
	
	void showMenu(View v) {
		Util.printLog(tag, "" + v.getId());
		
		int colorId = this.getResources().getColor(R.color.bg_grey);
		resetMenu();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		hideAllMenu(ft);
		switch(v.getId()) {
		case R.id.menuLeft:
			menuFirst.setBackgroundColor(colorId);
			
			if (null == firstFragment) {
				firstFragment = new FirstFragment();
				ft.add(R.id.app_fragment_id, firstFragment);
			}
			else {
				ft.show(firstFragment);
			}
			break;
		case R.id.menuRight:
			menuThird.setBackgroundColor(colorId);
			
			if (null == secondFragment) {
				secondFragment = new ThirdFragment();
				ft.add(R.id.app_fragment_id, secondFragment);
			}
			else {
				ft.show(secondFragment);
			}
			break;
		case R.id.menuCenter:
			menuSecond.setBackgroundColor(colorId);
			
			if (null == thirdFragment) {
				thirdFragment = new SecondFragment();
				ft.add(R.id.app_fragment_id, thirdFragment);
			}
			else {
				ft.show(thirdFragment);
			}
			
			break;
		default:
			
			break;
		}
		ft.commit();
	}
	
	// 按两下退出（2秒之内）
	private long exitTime = 0;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Util.printLog(tag, "key == > back");
			
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			}
			else {
				finish();
				System.exit(0);
			}
			
			// true 表示自己来处理这个事件 
			return true;
		}
		else if (keyCode == KeyEvent.KEYCODE_HOME) {
			Util.printLog(tag, "key == > home");
			
			return true;
		}
		else if (keyCode == KeyEvent.KEYCODE_MENU) {
			Util.printLog(tag, "key == > menu");
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.menuLeft:
				showMenu(menuFirst);
				break;
			case R.id.menuRight:
				showMenu(menuThird);
				break;
			case R.id.menuCenter:
				showMenu(menuSecond);
				break;
			default:
				
				break;
			}
			
		}
	};

}
