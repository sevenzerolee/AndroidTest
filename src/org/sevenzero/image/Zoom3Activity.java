package org.sevenzero.image;

import org.sevenzero.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 
 * @author sevenzero
   *
 * @date 2014-5-27
   *
 */
public class Zoom3Activity extends Activity implements OnTouchListener, OnClickListener {
	
	private static final String TAG = Zoom3Activity.class.getSimpleName(); 
	
	private Button big, small;
	private Bitmap newbitmap;
	private GestureDetector mGestureDetector;
	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	private ImageView bmp;
	private PointF first = new PointF();
	private PointF start = new PointF();
	private PointF mid = new PointF();
	private float oldDist;
	
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	private int mode = NONE;
	private long beginTime, endTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zoom3);
		
		big = (Button) findViewById(R.id.btnBig);
		small = (Button) findViewById(R.id.btnSmall);
		
		big.setOnClickListener(this);
		small.setOnClickListener(this);
		
		// 获取屏幕的宽和高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		int width = dm.widthPixels;
		int height = dm.heightPixels;
		
		// 获取图片的宽和高
		Bitmap mybitmap = BitmapFactory.decodeResource(getResources(), R.drawable.folder);
		
		int widthOrg = mybitmap.getWidth();
		int heightOrg = mybitmap.getHeight();
		
		// 宽高的比例
		float scaleWidth = (float) (width / widthOrg);
		float scaleHeight = (float) (height / heightOrg);
		float scale;
		
		bmp = (ImageView) findViewById(R.id.bmp);
		
		// 如果宽的比例大于高的比例，则使用高的比例，否则使用宽的比例
		if (scaleWidth > scaleHeight) {
			scale = scaleHeight;
		}
		else {
			scale = scaleWidth;
		}
		
		bmp.setImageBitmap(mybitmap);
		matrix.postScale(scale, scale);
		bmp.setImageMatrix(matrix);
		
		bmp.setOnTouchListener(this);
		bmp.setLongClickable(true);
		
		savedMatrix.set(matrix);
	}

	@Override
	public void onClick(View v) {
		Log.d(TAG, "in");
		if (v == small) {
			matrix.postScale(0.5f, 0.5f, 0, 0);
			bmp.setImageMatrix(matrix);
		}
		else {
			matrix.postScale(2f, 2f);
			bmp.setImageMatrix(matrix);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Log.d(TAG, "action " + event.getAction());
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			beginTime = System.currentTimeMillis();
			mode = DRAG;			
			Log.d(TAG, "down");
			first.set(event.getX(), event.getY());
			start.set(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
			endTime = System.currentTimeMillis();
			Log.d(TAG, "endTime " + (endTime - beginTime));
			float x = event.getX(0) - first.x;
			float y = event.getY(0) - first.y;
			
			float move = FloatMath.sqrt(x*x + y*y);
			Log.d(TAG, "move " + move);
			
			// 计算时间和移动的距离，来判断想要的操作，经过测试90%情况能满足
			if (endTime - beginTime < 500 && move > 20) {
				Toast.makeText(this, "Do what you want", 1000).show();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d(TAG, "move");
			if (mode == DRAG) {
				matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
				start.set(event.getX(), event.getY());
			}
			else {
				float newDist = spacing(event);
				if (newDist > 10F) {
					float scale = newDist / oldDist;
					Log.d(TAG, "scale " + scale);
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
				oldDist = newDist;
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			if (oldDist > 10F) {
				midPoint(mid, event);
				mode = ZOOM;
			}
			Log.d(TAG, "ACTION_POINTER_DOWN");
			break;
		case MotionEvent.ACTION_POINTER_UP:
			Log.d(TAG, "ACTION_POINTER_UP");
			break;
		}
		bmp.setImageMatrix(matrix);
		return false;
	}
	
	/**
	 * 计算拖动的距离
	 * @param event
	 * @return
	 */
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x*x + y*y);
	}
	
	/**
	 * 计算两点之间的中间点
	 * @param point
	 * @param event
	 */
	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x/2, y/2);
	}

}
