package org.sevenzero.image;

import org.sevenzero.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * 
 * @author sevenzero
   *
 * @date 2014-6-3
 * 
 * 
 * 说明：
 * 
 * 触控拖动屏幕事件一般情况下是重载onTouchEvent函数
 * 
 * 处理控件拖动事件跟上边类似，可添加一个OnTouchListener到控件上
 * 
 * 拖动事件主要就是在touch事件的envent中处理按下 移动 和 松开
 * 
 * 
   *
 */
public class ImageTwoTest extends Activity implements OnTouchListener {
	
	private static final String TAG = ImageTwoTest.class.getSimpleName();
	
	private ImageView view;
	
	private int width, height;
	
	void log(String msg) {
		Log.d(TAG, "" + msg);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.image_two);
		
		view = (ImageView) findViewById(R.id.imagetwo);
		view.setOnTouchListener(this);
		
		DisplayMetrics dm = getResources().getDisplayMetrics();
		width = dm.widthPixels;
		height = dm.heightPixels;
	}
	
	private float x, y;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		log("3");
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			log("32");
			this.x = x;
			this.y = y;
			break;
		case MotionEvent.ACTION_MOVE:
			log("33");
//			view.layout((int)(x - this.x), (int)(y - this.y), (int)(x + view.getWidth()
//                    - this.x), (int)(y - this.y + view.getHeight()));
			
//			this.x = event.getRawX();
//			this.y = event.getRawY();
			
			int dx = (int) (event.getRawX() - this.x);
			int dy = (int) (event.getRawY() - this.y);
			
			int left = view.getLeft() + dx;
			int top = view.getTop() + dy;
			int right = view.getRight() + dx;
			int bottom = view.getBottom() + dy;
			view.layout(left, top, right, bottom);
			
			this.x = (int) event.getRawX();
			this.y = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_UP:
			log("34");
			if (Math.abs(this.y - y) < 10) {
				if (x - this.x > 30) {
					Log.d(">> ", "right");
				}
				else if (x - this.x < -30) {
					Log.d("<< ", "left");
				}
			}
			break;
		}
		
		return super.onTouchEvent(event);
	}
	
	private int lastx, lasty;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		log("222");
		int e = event.getAction();
//		int x = (int) event.getRawX();
//		int y = (int) event.getRawY();
		switch (e) {
		case MotionEvent.ACTION_DOWN:
			log("222222");
			lastx = (int) event.getX();
			lasty = (int) event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			log("222333");
			int dx = (int) event.getRawX() - lastx;
			int dy = (int) event.getRawY() - lasty;
			
			int left = v.getLeft() + dx;
			int top = v.getTop() + dy;
			int right = v.getRight() + dx;
			int bottom = v.getBottom() + dy;
			if (left < 0) {
				left = 0;
				right = left + v.getWidth();
			}
			if (right > 480) {
				right = 480;
				left = right - v.getWidth();
			}
			if (top < 0) {
				top = 0;
				bottom = top + v.getHeight();
			}
			if (bottom > 640) {
				bottom = 640;
				top = bottom - v.getHeight();
			}
			v.layout(left, top, right, bottom);
//			v.postInvalidate();
			
			lastx = (int) event.getX();
			lasty = (int) event.getY();
			
			break;
		case MotionEvent.ACTION_UP:
			log("222444");
			break;
		}
		
		return true;
	}
	
	

}
