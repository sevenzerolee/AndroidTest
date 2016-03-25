package org.sevenzero.floattest;

import org.sevenzero.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * 
 * 
 * @author linger
 * 
 * @since 2015-7-6
 * 
 */
public class FloatMainActivity extends Activity {
	
	private WindowManager wm = null;  
    private WindowManager.LayoutParams wmParams = null;  
  
    private FloatFrame myFV = null;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_float_main);

		// 创建悬浮窗口  
        createFloatView();  
        this.finish(); // 显示悬浮窗口后，Activity自动退出  
	}

	private void createFloatView() {  
        Point windowSize = new Point();  
        this.getWindowManager().getDefaultDisplay().getSize(windowSize);  
        myFV = new FloatFrame(getApplicationContext());  
        // 获取WindowManager  
        wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);  
        // 设置LayoutParams(全局变量）相关参数  
        wmParams = ((FloatApplication) getApplication()).getWmParams();  
  
        /** 
         * 以下都是WindowManager.LayoutParams的相关属性 具体用途可参考SDK文档 
         */  
        wmParams.type =  2003;//LayoutParams.TYPE_PHONE; // 设置window type  
        wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明  
  
        // 设置Window flag  
        wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL  
                | LayoutParams.FLAG_NOT_FOCUSABLE;  
  
        /* 
         * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。 
         * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL | 
         * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE; 
         */  
  
        wmParams.gravity = Gravity.LEFT | Gravity.TOP; // 调整悬浮窗口至左上角  
//      wmParams.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT; // 调整悬浮窗口至左上角  
        //设置默认显示位置  
//      wmParams.x = 0;<span style="font-family:Arial, Helvetica, sans-serif;">// 以屏幕左上角为原点，设置x、y初始值</span>  
//      wmParams.y = 0;  
        wmParams.x = windowSize.x;// 以屏幕右边， 距中</span>  
        wmParams.y = windowSize.y / 2;  
  
        // 设置悬浮窗口长宽数据  
        wmParams.width =  android.view.ViewGroup.LayoutParams.WRAP_CONTENT;// 40;  
        wmParams.height = android.app.ActionBar.LayoutParams.WRAP_CONTENT;// 40;  
  
        // 显示myFloatView图像  
        wm.addView(myFV, wmParams);  
  
  
    }	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.float_main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_float_main,
					container, false);
			return rootView;
		}
	}

}
