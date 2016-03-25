package org.sevenzero.image;

import org.sevenzero.R;
import org.sevenzero.R.drawable;
import org.sevenzero.R.id;
import org.sevenzero.R.layout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 测试放大和缩小
 * 
 * @author sevenzero
   *
 * @date 2014-5-26
   *
 */
public class ZoomActivity extends Activity {
	
	private ImageView mImageView;
	private Button btnZoomIn, btnZoomOut;
	private AbsoluteLayout layout;
	private Bitmap bmp;
	private int id = 0;
	private int displayWidth, displayHeight;
	private float scaleWidth = 1.0f, scaleHeight = 1.0f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zoom);
		
		// 取得屏幕分辨率
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		displayWidth = dm.widthPixels;
		displayHeight = dm.heightPixels - 80;
		
		bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.folder);
		layout = (AbsoluteLayout) findViewById(R.id.layout);
		mImageView = (ImageView) findViewById(R.id.imageView);
		
		btnZoomIn = (Button) findViewById(R.id.btnZoomIn);
		btnZoomIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				zoomin();
			}
		});
		
		btnZoomOut = (Button) findViewById(R.id.btnZoomOut);
		btnZoomOut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				zoomout();
			}
		});
	}

	private void zoomin() {
		// 获得Bitmap的宽和高
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		
		// 缩放比例
		double scale = 1.20;
		scaleWidth = (float) (scale * scaleWidth);
		scaleHeight = (float) (scale * scaleHeight);
		
		// resize 后的Bitmap对象
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);		
		Bitmap bm = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
		if (id == 0) {
			layout.removeView(mImageView);
		}
		else {
			layout.removeView((ImageView) findViewById(id));
		}
		id++;
		ImageView imageView = new ImageView(this);
		imageView.setId(id);
		imageView.setImageBitmap(bm);
		layout.addView(imageView);
		setContentView(layout);
		
		if (scaleWidth*scale*bmpWidth > displayWidth || scaleHeight*scale*bmpHeight > displayHeight) {
			btnZoomIn.setEnabled(false);
		}
	}

	private void zoomout() {
		// 获得Bitmap的宽和高
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		
		// 缩放比例
		double scale = 0.8;
		scaleWidth = (float) (scale * scaleWidth);
		scaleHeight = (float) (scale * scaleHeight);
		
		// resize 后的Bitmap对象
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);		
		Bitmap bm = Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeight, matrix, true);
		if (id == 0) {
			layout.removeView(mImageView);
		}
		else {
			layout.removeView((ImageView) findViewById(id));
		}
		id++;
		ImageView imageView = new ImageView(this);
		imageView.setId(id);
		imageView.setImageBitmap(bm);
		layout.addView(imageView);
		setContentView(layout);
		
		btnZoomIn.setEnabled(true);
		
//		if (scaleWidth*scale*bmpWidth > displayWidth || scaleHeight*scale*bmpHeight > displayHeight) {
//			btnZoomIn.setEnabled(false);
//		}
	}

}
