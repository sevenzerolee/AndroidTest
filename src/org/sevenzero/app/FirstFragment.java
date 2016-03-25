package org.sevenzero.app;

import java.util.ArrayList;
import java.util.List;

import org.sevenzero.BackActivity;
import org.sevenzero.R;
import org.sevenzero.SensorActivity;
import org.sevenzero.activity.ActionBarActivity;
import org.sevenzero.activity.MenuIconActivity;
import org.sevenzero.dialog.SettingDialog;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

import com.foxit.util.Util;

public class FirstFragment extends Fragment {
	
	private static final String tag = FirstFragment.class.getSimpleName();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Util.printLog(tag, "### onCreateView");
		
		View view = inflater.inflate(R.layout.app_first_fragment, container, false);
		
		test = (Button) view.findViewById(R.id.test);
		test.setOnClickListener(listener);
		
		actionBar = (Button) view.findViewById(R.id.action_bar);
		actionBar.setOnClickListener(listener);
		
		menuIcon = (Button) view.findViewById(R.id.menu_icon);
		menuIcon.setOnClickListener(listener);
		
		sensor = (Button) view.findViewById(R.id.sensor);
		sensor.setOnClickListener(listener);
		
		setDialog = (Button) view.findViewById(R.id.setting_dialog);
		setDialog.setOnClickListener(listener);
		
//		return super.onCreateView(inflater, container, savedInstanceState);
//		return inflater.inflate(R.layout.app_first_fragment, container, false);
		return view;
	}
	
	private Button test, actionBar;
	private Button menuIcon;
	private Button sensor;
	private Button setDialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		Util.printLog(tag, "### onCreate");
		super.onCreate(savedInstanceState);
		
//		test = (Button) getActivity().findViewById(R.id.test);
//		test.setOnClickListener(listener);
	}
	


	List<String> addItems(String[] values) {  
        
        List<String> list = new ArrayList<String>();  
        for (String var : values) {  
            list.add(var);  
        }  
          
        return list;  
    }
	
	private SettingDialog sDialog;
	private List<String> menuName;
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int id = v.getId();
			switch (id) {
			case R.id.setting_dialog:
				menuName = addItems(new String[]{ "服务地址", "用户信息", "检查更新"});
				
			    sDialog = new SettingDialog(getActivity(), menuName, new ItemClickEvent());
				sDialog.show();
				
				break;
			case R.id.test:
				Intent back = new Intent();
				back.setClass(getActivity(), BackActivity.class);
				startActivity(back);
				
				break;
			case R.id.sensor:
				Intent iSensor = new Intent();
				iSensor.setClass(getActivity(), SensorActivity.class);
				startActivity(iSensor);
				
				break;
			case R.id.menu_icon:
				Intent menu = new Intent();
				menu.setClass(getActivity(), MenuIconActivity.class);
				startActivity(menu);
				
				break;
			case R.id.action_bar:
				Intent action = new Intent();
				action.setClass(getActivity(), ActionBarActivity.class);
				startActivity(action);
				
				break;
			default:
				break;
			}
		}
	};
	
	/**
	 * 菜单选项点击事件
	 * 
	 */
	class ItemClickEvent implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// 显示点击的是哪个菜单哪个选项。
			String name = menuName.get(arg2);
//			Toast.makeText(MainActivity.this,
//					"Menu: " + menuName.get(settingMenu.getNameIndex()), Toast.LENGTH_SHORT).show();
//			Toast.makeText(MainActivity.this,
//					"Menu: " + name, Toast.LENGTH_SHORT).show();
			
			// 设置服务地址
			if (menuName.get(0).equals(name)) {
				Util.printLog(tag, "### 0 " + name);
				
			}
			// 设置用户信息
			else if (menuName.get(1).equals(name)) {
				Util.printLog(tag, "### 1 " + name);

			}
			// 检查更新
			else if (menuName.get(2).equals(name)) {
				Util.printLog(tag, "### 2 " + name);
				
			}
			// 隐藏菜单 
			else if (menuName.get(3).equals(name)) {
				
			}
			
			sDialog.dismiss();
		}

	}

}
