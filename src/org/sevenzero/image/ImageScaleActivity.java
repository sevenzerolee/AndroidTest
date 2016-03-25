package org.sevenzero.image;

import org.sevenzero.R;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * 
 * 图片缩放测试
 * 
 * @author sevenzero
   *
 * @since 2013-1-22
   *
 */
public class ImageScaleActivity extends Activity implements OnTouchListener {
	
	private ImageView image;
	
	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	
	static final int NONE = 0, DRAG = 2, ZOOM = 3;
	private int mode = NONE;
	
	private PointF start = new PointF();
	private PointF mid = new PointF();
	private float oldDist = 1.0F;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar_layout);
		
		image = (ImageView) this.findViewById(R.id.cat);
		image.setOnTouchListener(this);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		image.setScaleType(ScaleType.MATRIX);
		
		ImageView view = (ImageView) v;
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			matrix.set(view.getImageMatrix());
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			mode = DRAG;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = this.spacing(event);
			if (oldDist > 10.0F) {
				savedMatrix.set(matrix);
				this.midPoint(mid, event);
				mode = ZOOM;
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode = NONE;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode == DRAG) {
				matrix.set(savedMatrix);
				matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
			}
			else if (mode == ZOOM) {
				float newDist = this.spacing(event);
				if (newDist > 10F) {
					matrix.set(savedMatrix);
					float scale = newDist/oldDist;
					matrix.postScale(scale, scale, mid.x, mid.y);
				}
			}
			break;
		}
		view.setImageMatrix(matrix);
		
		return true;
	}
	
	void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		point.set(x/2, y/2);
	}
	
	float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		
		return FloatMath.sqrt(x*x + y*y);
	}

}
