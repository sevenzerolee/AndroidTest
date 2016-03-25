package org.sevenzero;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * 
 * 进度条
 * 
 * @author sevenzero
   *
 * @since 2012-6-29
   *
 */
public class ProgressBarActivity extends Activity {
	
	private ProgressBar progressBar;
	private Button btnPbStart, btnPbEnd;
	
	private ProgressBar progressBarHorizontal;
	private Button btnPbhStart, btnPbhEnd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progressbar_layout);
		
		progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
		progressBar.setVisibility(View.GONE);
		
		progressBarHorizontal = (ProgressBar) this.findViewById(R.id.progressBarHorizontal);
		progressBarHorizontal.setVisibility(View.VISIBLE);
		
		btnPbStart = (Button) this.findViewById(R.id.btnPbStart);
		btnPbStart.setOnClickListener(listener);
		btnPbEnd = (Button) this.findViewById(R.id.btnPbEnd);
		btnPbEnd.setOnClickListener(listener);
		
		btnPbhStart = (Button) this.findViewById(R.id.btnPbhStart);
		btnPbhStart.setOnClickListener(listener);
		btnPbhEnd = (Button) this.findViewById(R.id.btnPbhEnd);
		btnPbhEnd.setOnClickListener(listener);
		
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == btnPbStart.getId()) {
				progressBar.setVisibility(View.VISIBLE);
			}
			else if (id == btnPbEnd.getId()) {
				progressBar.setVisibility(View.GONE);
			}
			else if (id == btnPbhStart.getId()) {
				progressBarHorizontal.setProgress(progressBarHorizontal.getProgress() + 10);
			}
			else if (id == btnPbhEnd.getId()) {
				progressBarHorizontal.setProgress(progressBarHorizontal.getProgress() - 10);
			}
		}
	};

}
