package org.sevenzero.dialog;

import org.sevenzero.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 
 * @author linger
 *
 * @since 2015-9-23
 *
 */
public class RealProgressDialog extends Dialog {

	private int len;
	private int start;
	
	public RealProgressDialog(Context context, int len, int start) {
		super(context, R.style.define_dialog_2);
		
		this.setTitle("上传文件");
		this.setCancelable(false);
		this.len = len;
		this.start = start;
	}
	
	private TextView numberProgress;
	private ProgressBar realProgressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.dialog_real_progress_layout);
		
		numberProgress = (TextView) findViewById(R.id.numberProgress);
		numberProgress.setText("0%");
		realProgressBar = (ProgressBar) findViewById(R.id.dialogRealProgressBarh);
		realProgressBar.setMax(len);
		realProgressBar.setProgress(start);
	}

	public ProgressBar getRealProgressBar() {
		return realProgressBar;
	}

	public void setRealProgressBar(ProgressBar realProgressBar) {
		this.realProgressBar = realProgressBar;
	}

	public TextView getNumberProgress() {
		return numberProgress;
	}

	public void setNumberProgress(TextView numberProgress) {
		this.numberProgress = numberProgress;
	}

}
