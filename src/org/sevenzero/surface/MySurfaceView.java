package org.sevenzero.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * 
 * 被塞尔曲线演示
 * 
 * @author sevenzero
 * 
 * @since 2013-1-21
 * 
 */
public class MySurfaceView extends SurfaceView implements Callback, Runnable {

	private SurfaceHolder sfh;
	private Paint paint;
	private Canvas canvas;
	private Thread th;
	private boolean flag = true;

	private int startX, startY, controlX, controlY, endX, endY;
	private Path path;
	private Paint paintQ;

	public MySurfaceView(Context context) {
		super(context);
		sfh = getHolder();
		sfh.addCallback(this);

		path = new Path();
		paintQ = new Paint();
		paintQ.setAntiAlias(true);
		paintQ.setStyle(Style.STROKE);
		paintQ.setStrokeWidth(5);
		paintQ.setColor(Color.WHITE);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		paint = new Paint();
		paint.setAntiAlias(true);
		th = new Thread(this);
		flag = true;
		th.start();
	}

	private void myDraw() {
		canvas = sfh.lockCanvas();
		canvas.drawColor(Color.BLACK);
		drawQpath(canvas);
		sfh.unlockCanvasAndPost(canvas);
	}

	// 绘制贝塞尔曲线
	public void drawQpath(Canvas canvas) {
		path.reset();
		path.moveTo(startX, startY);
		path.quadTo(controlX, controlY, endX, endY);
		canvas.drawPath(path, paintQ);
	}

	// 响应触摸屏事件，通过手指的位置取得两个重要的点，起始点，和终止点
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			startX = (int) event.getX();
			startY = (int) event.getY();
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			endX = (int) event.getX();
			endY = (int) event.getY();
		}

		return true;
	}

	// 通过起始点和终止点构造控制点
	private void logic() {
		if (endX != 0 && endY != 0) {
			controlX = ((endX - startX) / 2);
			controlY = ((endY + startY) / 2);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		flag = false;
	}

	// 线程run，刷屏得到贝塞尔曲线
	@Override
	public void run() {
		while (flag) {
			long startTime = System.currentTimeMillis();
			myDraw();
			logic();
			long endTime = System.currentTimeMillis();
			if ((endTime - startTime < 50)) {
				try {
					Thread.sleep(50 - (endTime - startTime));
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

}
