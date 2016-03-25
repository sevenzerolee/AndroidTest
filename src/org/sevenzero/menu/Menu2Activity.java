package org.sevenzero.menu;

import org.sevenzero.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

import com.foxit.util.Util;

public class Menu2Activity extends Activity {
	
	private static final String TAG = Menu2Activity.class.getSimpleName();
	
	private PopupWindow popup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu2);
		
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		View view = mLayoutInflater.inflate(R.layout.menu2, null);
		view.setFocusableInTouchMode(true);
		popup = new PopupWindow(view, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popup.setOutsideTouchable(true);
		popup.setTouchInterceptor(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
					popup.dismiss();
					return true;
				}
				return false;
			}

		});
		

		

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Util.printLog(TAG, "key ==> back");
			break;
		case KeyEvent.KEYCODE_MENU:
//			Toast.makeText(this, "key ==> Menu " + keyCode, Toast.LENGTH_SHORT).show();
			Util.printLog(TAG, "key ==> menu");
			
			return false;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		Util.printLog(TAG, "onMenuOpened");
//		if (popup.isShowing()) {
//			popup.dismiss();
//		}
//		else {
//			popup.showAtLocation(this.findViewById(R.id.container2), Gravity.BOTTOM, 0, 0);
//		}
		popup.showAtLocation(this.findViewById(R.id.container2), Gravity.BOTTOM, 0, 0);
//		return super.onMenuOpened(featureId, menu);
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_menu2,
					container, false);
			return rootView;
		}
	}

}
