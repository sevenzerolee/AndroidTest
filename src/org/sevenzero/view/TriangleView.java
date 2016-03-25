package org.sevenzero.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 
 * @author lkimac
 *
 * @since 2016-3-8
 * 
 * 自定义三角形View
 *
 */
public class TriangleView extends View {
	
	public TriangleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		paint = new Paint();
	}

	private Paint paint;

//	public TriangleView(Context context) {
//		super(context);
//		
//		paint = new Paint();
//	}
	
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		paint.setAntiAlias(true);
		
		paint.setColor(Color.BLUE);
		Path path = new Path();
		path.moveTo(0, 0);
		path.lineTo(50, 0);
		path.lineTo(0, 40);
		path.close();
		
		canvas.drawPath(path, paint);
		
	}

}
