package org.sevenzero.image;

import org.sevenzero.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.GestureDetector.OnDoubleTapListener;
import android.widget.ImageView;

public class Zoom4Activity extends Activity implements OnGestureListener {
	
	private static final String TAG = Zoom4Activity.class.getSimpleName();
	
	private ImageView zoom4img;
	
	private GestureDetector gesture = null;
	private double scale = 1.0;
	
	private Matrix matrix = new Matrix();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zoom4);
		
		zoom4img = (ImageView) findViewById(R.id.zoom4img);
		final Bitmap folder = BitmapFactory.decodeResource(getResources(), R.drawable.file);
		zoom4img.setImageBitmap(folder);
		
		gesture = new GestureDetector(this);
		gesture.setOnDoubleTapListener(new OnDoubleTapListener() {

			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				return false;
			}

			@Override
			public boolean onDoubleTap(MotionEvent e) {
				Log.d(TAG, "in");
				if (scale == 1.0) {
					scale = scale + 0.5;
				}
				else if (scale == 1.5) {
					scale = scale - 0.5;
				}
				matrix.reset();
				matrix.postScale((float) scale, (float) scale);
				Bitmap resize = Bitmap.createBitmap(folder, 0, 0, folder.getWidth(), folder.getHeight(), matrix, true);
				zoom4img.setImageBitmap(resize);
				
				return true;
			}

			@Override
			public boolean onDoubleTapEvent(MotionEvent e) {
				return false;
			}
			
		});
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gesture.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

}
