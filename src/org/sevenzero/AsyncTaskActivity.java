package org.sevenzero;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

import com.foxit.util.Util;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-6-28
   *
 */
public class AsyncTaskActivity extends Activity {
	
	private static final String tag = AsyncTaskActivity.class.getSimpleName();
	
	private ProgressBar asyncTaskProgressBar;
	private Button btnAsyncStart, btnAsyncEnd, btnAsync;
	
	private ProgressBar asyncTaskProgressBarh;
	private Button btnAsyncStarth, btnAsyncEndh, btnAsynch;
	
	public static final long TIME = 10 * 1000L;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asynctask_main);
		
		asyncTaskProgressBar = (ProgressBar) this.findViewById(R.id.asyncTaskProgressBar);
		asyncTaskProgressBar.setVisibility(View.GONE);
		
		btnAsyncStart = (Button) this.findViewById(R.id.btnAsyncStart);
		btnAsyncStart.setOnClickListener(listener);
		
		btnAsyncEnd = (Button) this.findViewById(R.id.btnAsyncEnd);
		btnAsyncEnd.setOnClickListener(listener);
		
		btnAsync = (Button) this.findViewById(R.id.btnAsync);
		btnAsync.setOnClickListener(listener);
		
		asyncTaskProgressBarh = (ProgressBar) this.findViewById(R.id.asyncTaskProgressBarh);
		asyncTaskProgressBarh.setVisibility(View.GONE);
		
		btnAsyncStarth = (Button) this.findViewById(R.id.btnAsyncStarth);
		btnAsyncStarth.setOnClickListener(listener);
		
		btnAsyncEndh = (Button) this.findViewById(R.id.btnAsyncEndh);
		btnAsyncEndh.setOnClickListener(listener);
		
		btnAsynch = (Button) this.findViewById(R.id.btnAsynch);
		btnAsynch.setOnClickListener(listener);
		
//		new AsyncTaskTesth("H-22", asyncTaskProgressBarh, btnAsyncStarth, btnAsyncEndh).execute("H-2");	
//		new AsyncTaskTest("22", asyncTaskProgressBar, btnAsyncStart, btnAsyncEnd).execute("2");	
//		new AsyncTaskTest("33").execute("3");
		
		System.out.println("ok");

	}
	
	private AsyncTask asyncTask = null;
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int id = v.getId();
			
			if (id == btnAsyncStart.getId()) {
				btnAsyncStart.setEnabled(false);
				btnAsyncEnd.setEnabled(true);
				asyncTaskProgressBar.setVisibility(View.VISIBLE);
			}
			else if (id == btnAsyncEnd.getId()) {
				btnAsyncStart.setEnabled(true);
				btnAsyncEnd.setEnabled(false);
				asyncTaskProgressBar.setVisibility(View.GONE);
			}
			else if (id == btnAsync.getId()) {
				new AsyncTaskTest("33", asyncTaskProgressBar, btnAsyncStart, btnAsyncEnd).execute("3");
			}
			// ----------------------------------------------------------------
			else if (id == btnAsyncStarth.getId()) {
				btnAsyncStarth.setEnabled(false);
				btnAsyncEndh.setEnabled(true);
//				asyncTaskProgressBarh.setVisibility(View.VISIBLE);
				asyncTask = new AsyncTaskTesth("H-33", asyncTaskProgressBarh, btnAsyncStarth, btnAsyncEndh).execute("H-3");
			}
			else if (id == btnAsyncEndh.getId()) {
				btnAsyncStarth.setEnabled(true);
				btnAsyncEndh.setEnabled(false);
//				asyncTaskProgressBarh.setVisibility(View.GONE);
				if (null != asyncTask && !asyncTask.isCancelled()) {
					asyncTask.cancel(true);
					asyncTaskProgressBarh.setVisibility(View.GONE);
				}
			}
			else if (id == btnAsynch.getId()) {
				new AsyncTaskTesth("H-33", asyncTaskProgressBarh, btnAsyncStarth, btnAsyncEndh).execute("H-3");
			}
		}
	};

}

/**
 * 
 * @author lkimac
 *
 * @since 2016-3-25
 * 
 * 实时进度条
 *
 */
class AsyncTaskTesth extends AsyncTask<String, String, String> {
	
	private static final String TAG = AsyncTaskTesth.class.getSimpleName();
	
	private String str;
	private ProgressBar pb;
	private Button start, end;
	
	AsyncTaskTesth(String str, ProgressBar pb, Button start, Button end) {
		this.str = str;
		this.pb = pb;
		this.start = start;
		this.end = end;
		
		pb.setVisibility(View.VISIBLE);
		pb.setProgress(10);
//		start.setEnabled(false);
//		end.setEnabled(false);
	}

	@Override
	protected String doInBackground(String... params) {
		System.out.println(str + "-" + params[0]);
		
		try {
			System.out.println(str + "-sleep");
			
			while (pb.getProgress() < pb.getMax()) {
				Thread.sleep(1000L);
//				pb.setProgress(pb.getProgress() + 5);
				publishProgress(params[0]);
				Util.printLog(TAG, "... " + pb.getProgress());
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return params[0];
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		System.out.println(str + "#" + result);
		
		pb.setVisibility(View.GONE);
		start.setEnabled(true);
		end.setEnabled(true);
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		System.out.println(str + "@" + values);
		pb.setProgress(pb.getProgress() + 5);
	}
	
}

class AsyncTaskTest extends AsyncTask<String, String, Integer> {
	
	private String str;
	private ProgressBar pb;
	private Button start, end;
	
	AsyncTaskTest(String str, ProgressBar pb, Button start, Button end) {
		this.str = str;
		this.pb = pb;
		this.start = start;
		this.end = end;
		
		pb.setVisibility(View.VISIBLE);
		start.setEnabled(false);
		end.setEnabled(false);
	}

	@Override
	protected Integer doInBackground(String... params) {
		System.out.println(str + "-" + params[0]);
		
		try {
			Thread.sleep(AsyncTaskActivity.TIME);
			publishProgress(params[0]);
			System.out.println(str + "-sleep");
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return Integer.valueOf(params[0]);
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		System.out.println(str + "#" + result);
		
		pb.setVisibility(View.GONE);
		start.setEnabled(true);
		end.setEnabled(true);
	}

	@Override
	protected void onProgressUpdate(String... values) {
		super.onProgressUpdate(values);
		System.out.println(str + "@" + values);
	}
	
}
