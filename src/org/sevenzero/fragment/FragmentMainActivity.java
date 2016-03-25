package org.sevenzero.fragment;

import org.sevenzero.R;

import com.foxit.util.Util;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FragmentMainActivity extends Activity {
	
	private static final String tag = FragmentMainActivity.class.getSimpleName();
	
	private TextView menuLeft, menuRight, menuCenter;
	
	private MenuLeftFragment menuLeftFragment;
	private MenuRightFragment menuRightFragment;
	private MenuCenterFragment menuCenterFragment;
	
	/** 
     * 用于对Fragment进行管理 
     */  
    private FragmentManager fragmentManager;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_main);
		
		menuLeft = (TextView) findViewById(R.id.menuLeft);
		menuRight = (TextView) findViewById(R.id.menuRight);
		menuCenter = (TextView) findViewById(R.id.menuCenter);
		menuLeft.setOnClickListener(listener);
		menuRight.setOnClickListener(listener);
		menuCenter.setOnClickListener(listener);
		
//		menuLeftFragment = new MenuLeftFragment();
//		menuRightFragment = new MenuRightFragment();
		
		fragmentManager = getFragmentManager();
		showMenu(menuLeft);
		
//		FragmentTransaction ft = fragmentManager.beginTransaction();
//		ft.add(R.id.fragment_id, menuLeftFragment);
//		ft.add(R.id.fragment_id, menuRightFragment);
//		ft.show(menuLeftFragment);
//		ft.commit();
	}
	
	void hideAllMenu(FragmentTransaction ft) {
		if (null != menuLeftFragment) {
			ft.hide(menuLeftFragment);
		}
		if (null != menuRightFragment) {
			ft.hide(menuRightFragment);
		}
		if (null != menuCenterFragment) {
			ft.hide(menuCenterFragment);
		}
	}
	
	void resetMenu() {
		int colorId = this.getResources().getColor(R.color.bg_white);
		menuLeft.setBackgroundColor(colorId);
		menuRight.setBackgroundColor(colorId);
		menuCenter.setBackgroundColor(colorId);
	}
	
	void showMenu(View v) {
		Util.printLog(tag, "" + v.getId());
		
		int colorId = this.getResources().getColor(R.color.bg_grey);
		resetMenu();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		hideAllMenu(ft);
		switch(v.getId()) {
		case R.id.menuLeft:
			menuLeft.setBackgroundColor(colorId);
			
			if (null == menuLeftFragment) {
				menuLeftFragment = new MenuLeftFragment();
				ft.add(R.id.fragment_id, menuLeftFragment);
			}
			else {
				ft.show(menuLeftFragment);
			}
			break;
		case R.id.menuRight:
			menuRight.setBackgroundColor(colorId);
			
			if (null == menuRightFragment) {
				menuRightFragment = new MenuRightFragment();
				ft.add(R.id.fragment_id, menuRightFragment);
			}
			else {
				ft.show(menuRightFragment);
			}
			break;
		case R.id.menuCenter:
			menuCenter.setBackgroundColor(colorId);
			
			if (null == menuCenterFragment) {
				menuCenterFragment = new MenuCenterFragment();
				ft.add(R.id.fragment_id, menuCenterFragment);
			}
			else {
				ft.show(menuCenterFragment);
			}
			
			break;
		default:
			
			break;
		}
		ft.commit();
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()) {
			case R.id.menuLeft:
				showMenu(menuLeft);
				break;
			case R.id.menuRight:
				showMenu(menuRight);
				break;
			case R.id.menuCenter:
				showMenu(menuCenter);
				break;
			default:
				
				break;
			}
			
		}
	};

}
