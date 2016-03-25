package org.sevenzero.menu;

import org.sevenzero.R;
import org.sevenzero.dialog.CallbackImpl;
import org.sevenzero.dialog.Function;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-6-5
 *
 */
public class MenuActivity extends Activity {
	
	private static final String TAG = MenuActivity.class.getSimpleName();
	
	private MyMenu.MenuBodyAdapter adapter = new MyMenu.MenuBodyAdapter(this, 
			new int[]{R.drawable.file, R.drawable.folder});
	private MyMenu myMenu;
	
	private Button passwordBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		myMenu = new MyMenu(this, new BodyClickEvent(), R.drawable.cat);
		myMenu.update();
		myMenu.SetBodyAdapter(adapter);
		
		passwordBtn = (Button) findViewById(R.id.passwordBtn);
		passwordBtn.setOnClickListener(listener);
		

		

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == passwordBtn.getId()) {
				// 测试密码对话框
//				PasswordDialog dialog = new PasswordDialog(MenuActivity.this);
//				if (dialog.showDialog() == PasswordDialog.DIALOG_RESULT_OK) {
//					Util.printLog(TAG, "ok");
//				}
//				else if (dialog.showDialog() == PasswordDialog.DIALOG_RESULT_CANCEL) {
//					Util.printLog(TAG, "cancel");
//				}
//				else {
//					Util.printLog(TAG, "other");
//				}
//				Util.printLog(TAG, "end dialog");
				
				// 测试回调
				Function fun = new Function();
				fun.setCall(new CallbackImpl(MenuActivity.this));
				String value = fun.doSomething("测试回调");
				Util.printLog(TAG, value);
			}
		}
	};
	
	class BodyClickEvent implements OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            myMenu.SetBodySelect(arg2, Color.GRAY);
            Util.printLog(TAG, " BodyClickEvent implements OnItemClickListener "
                    + arg2);
        }
 
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Util.printLog(TAG, "key ==> back");
			break;
		case KeyEvent.KEYCODE_MENU:
			Toast.makeText(this, "key ==> Menu " + keyCode, Toast.LENGTH_SHORT).show();
			Util.printLog(TAG, "key ==> menu");
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Toast.makeText(this, "Menu onCreateOptionsMenu", Toast.LENGTH_SHORT).show();
		Util.printLog(TAG, "onCreateOptionsMenu");
		menu.add("menu");
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Util.printLog(TAG, "onOptionsItemSelected");
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(this, "Menu " + id, Toast.LENGTH_SHORT).show();
			Util.printLog(TAG, "menu item");
			
			return true;
		}
		else if (id == R.id.action_settings_2) {
			Toast.makeText(this, "Menu " + id, Toast.LENGTH_SHORT).show();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		Util.printLog(TAG, "onMenuOpened");
		if (null != myMenu) {
			if (myMenu.isShowing()) {
				myMenu.dismiss();
			}
			else {
				myMenu.showAtLocation(this.findViewById(R.id.container), Gravity.BOTTOM, 0, 0);
			}
		}
//		return super.onMenuOpened(featureId, menu);
		return false;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Util.printLog(TAG, "onMenuItemSelected");
		return super.onMenuItemSelected(featureId, item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_menu, container,
					false);
			return rootView;
		}
	}

}
