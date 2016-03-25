package org.sevenzero;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * 
 * @author sevenzero
   *
 * @since 2013-1-17
 * 
 * 左右滑动测试
   *
 */
public class GestureActivity extends Activity implements OnGestureListener {
	
	private static final String TAG = GestureActivity.class.getName();
	
	private ViewFlipper flipper;
	private GestureDetector detector;
	
	private int[] ids = new int[] {R.drawable.cat, R.drawable.file, R.drawable.cat2, R.drawable.folder};
	private int curPos = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gesture);
		Log.d(TAG, "GestureActivity");
		
		flipper = (ViewFlipper) this.findViewById(R.id.viewFlipper);		
		detector = new GestureDetector(this);
		
		setView(curPos, 0);
		
//		flipper.addView(addImageView(R.drawable.cat));
//		flipper.addView(addImageView(R.drawable.file));
//		flipper.addView(addImageView(R.drawable.cat2));
//		flipper.addView(addImageView(R.drawable.folder));
		
		
//		flipper.setAutoStart(true);
//		flipper.setFlipInterval(3000);
//		flipper.startFlipping();
		
	}

	View addImageView(final int id) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(id);
		return iv;
	}
	
	void setView(int cur, int next) {
		if (cur < next && next > ids.length - 1) {
			next = 0;
		}
		else if (cur > next && next < 0) {
			next = ids.length - 1;
		}
		
		if (flipper.getChildCount() > 1) {
			flipper.removeViewAt(0);
		}
		
		View v = addImageView(ids[next]);
		flipper.addView(v, flipper.getChildCount());
		curPos = next;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "event");
//		flipper.stopFlipping();
//		flipper.setAutoStart(false);
		return this.detector.onTouchEvent(event);
	}
	
	@Override
	public boolean onDown(MotionEvent e) {
//		Log.d(TAG, "down");
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
//		Log.d(TAG, "showpress");

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
//		Log.d(TAG, "singletapup");
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
//		Log.d(TAG, "scroll");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
//		Log.d(TAG, "longpress");

	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		Log.d(TAG, "fling: " + e1.getX() + ", " + e1.getY() );
		Log.d(TAG, "fling: " + e2.getX() + ", " + e2.getY() );
		
		if (e1.getX() - e2.getX() > 120) {
			setView(curPos, curPos - 1);
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,	R.anim.push_left_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
			Log.d(TAG, "left before count = " + flipper.getChildCount());
			this.flipper.showPrevious();
			Log.d(TAG, "left after count = " + flipper.getChildCount());

			return true;
		}
		else if (e1.getX() - e2.getX() < -120) {
			setView(curPos, curPos + 1);
			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,	R.anim.push_right_in));
			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
			Log.d(TAG, "right before count = " + flipper.getChildCount());
			this.flipper.showNext();
			Log.d(TAG, "right after count = " + flipper.getChildCount());
			return true;
		}
		
		// 上下滑动
//		if (e1.getY() - e2.getY() > 120) {
//			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,	R.anim.push_left_in));
//			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
//			this.flipper.showNext();
//			return true;
//		}
//		else if (e1.getY() - e2.getY() < -120) {
//			this.flipper.setInAnimation(AnimationUtils.loadAnimation(this,	R.anim.push_right_in));
//			this.flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
//			this.flipper.showPrevious();
//			return true;
//		}
		
		return false;
	}

}
