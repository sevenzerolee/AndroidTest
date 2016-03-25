package org.sevenzero.activity;

import java.util.ArrayList;
import java.util.List;

import org.sevenzero.R;
import org.sevenzero.menu.SettingMenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.foxit.util.Util;

public class MenuIconActivity extends Activity {
	
	private static final String tag = MenuIconActivity.class.getSimpleName();

	private TextView menu;
	
	private SettingMenu settingMenu;
	private List<String> menuName;
	
	class PopWindowDismiss implements OnDismissListener {

		@Override
		public void onDismiss() {
			Util.printLog(tag, "### 退出菜单");
			showMenu();
		}
		
	}
	
	class ItemClickEvent implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// 显示点击的是哪个菜单哪个选项。
			String name = menuName.get(arg2);

			// 设置服务地址
			if (menuName.get(0).equals(name)) {
				
			}
			// 设置用户信息
			else if (menuName.get(1).equals(name)) {
				
			}
			// 检查更新
			else if (menuName.get(2).equals(name)) {
				
			}
			
			settingMenu.dismiss();
		}

	}

	List<String> addItems(String[] values) {  
        
        List<String> list = new ArrayList<String>();  
        for (String var : values) {  
            list.add(var);  
        }  
          
        return list;  
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_icon);
		
		menu = (TextView) findViewById(R.id.menu);
		menu.setOnClickListener(listener);
		
		viewX = menu.getX();
		viewY = menu.getY();
		Util.printLog(tag, "onCreate View: " + viewX + ", " + viewY);
		
		menuName = addItems(new String[]{ "服务地址", "用户信息", "检查更新"});
		settingMenu = new SettingMenu(this, menuName, new ItemClickEvent(), new PopWindowDismiss());
		
		
	}
	
	private boolean first = true;
	
	void initViewCoordinate() {
		if (first) {
			viewX = menu.getX();
			viewY = menu.getY();
			Util.printLog(tag, "初始化坐标 View: " + viewX + ", " + viewY);
			
			first = false;
		}
	}
	
	private float viewX, viewY;
	private boolean click = false;
	
	// 显示菜单
	void showMenu() {
		if (click) {
			click = false;
			if (null != menu) {
				menu.setVisibility(View.VISIBLE);
			}
		}
	}
	
	// 隐藏菜单
	void hideMenu() {
		if (!click) {
			click = true;
			if (null != menu) {
				menu.setVisibility(View.GONE);
			}
			if (null != settingMenu) {
				settingMenu.showAtLocation(findViewById(R.id.root_layout), Gravity.BOTTOM, 0,0);
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Util.printLog(tag, "### == > onCreateOptionsMenu");
		
		return true;
	}
	
	public boolean onMenuOpened(int featureId, Menu menu) {
		Util.printLog(tag, "### == > onMenuOpened");
		hideMenu();
		
		return false;
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
//			initViewCoordinate();
			
			switch (v.getId()) {
			case R.id.menu:
				Util.printLog(tag, "" + click);
				if (!click) {
//					click = true;
					hideMenu();
					
//					RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
//							RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//					rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//					rlp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//					menu.setLayoutParams(rlp);
					
//					menu.setX(viewX);
//					menu.setY(viewY - 200);
					
//					LayoutParams lp = (LayoutParams) rootLayout.getLayoutParams();
//					lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//					lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//					addContentView(menu, rlp);
					
				}
//				else {
//					click = false;
					
//					RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
//							RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
//					rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//					rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//					menu.setLayoutParams(rlp);
					
//					menu.setX(viewX);
//					menu.setY(viewY);
					
//				}
				break;
			default:
				
				break;	
			}
			
		}
	};
	
}
