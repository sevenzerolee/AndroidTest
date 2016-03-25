package org.sevenzero.image;

import org.sevenzero.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * 
 * @author sevenzero
   *
 * @date 2014-5-26
   *
 */
public class ImageShowView extends View implements Runnable {
	
	private Bitmap bmp = null;
	private int bmpWidth = 0;
	private int bmpHeight = 0;
	
	private double scale = 1.0;
	
	private Matrix matrix = new Matrix();

	public ImageShowView(Context context) {
		super(context);
		bmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.folder)).getBitmap();
//		bmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.cat)).getBitmap();
		
		bmpWidth = bmp.getWidth();
		bmpHeight = bmp.getHeight();
		
		DisplayMetrics dm = getResources().getDisplayMetrics();
		width = dm.widthPixels;
		height = dm.heightPixels;
		
		new Thread(this).start();
	}
	
	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		matrix.reset();
		matrix.postScale((float) scale, (float) scale);
		Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
		
		drawImage(canvas, bitmap, (320 - bmpWidth)/2, 10);
		
		bitmap = null;
	}
	
	private static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y) {
		canvas.drawBitmap(bitmap, x, y, null);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
			if (scale > 0.3) {
				scale = scale - 0.1;
			}
		}
		else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
			if (scale < 2) {
				scale = scale + 0.1;
			}
		}
//		return super.onKeyDown(keyCode, event);
		return true;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100L);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
			postInvalidate();
		}

	}
	
	private int width, height;
	private int lastx, lasty;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastx = (int) event.getRawX();
			lasty = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			int dx = (int) event.getRawX() - lastx;
			int dy = (int) event.getRawY() - lasty;
			
			int left = getLeft() + dx;
			int top = getTop() + dy;
			int right = getRight() + dx;
			int bottom = getBottom() + dy;
			
			if (left < 0) {
				left = 0;
				right = left + getWidth();
			}
			if (right > width) {
				right = width;
				left = right - getWidth();
			}
			if (top < 0) {
				top = 0;
				bottom = top + getHeight();
			}
			if (bottom > height) {
				bottom = height;
				top = bottom - getHeight();
			}
			
			layout(left, top, right, bottom);
			
			lastx = (int) event.getRawX();
			lasty = (int) event.getRawY();
			
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		
		return false;
	}

}
