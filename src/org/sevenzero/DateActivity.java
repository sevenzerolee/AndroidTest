package org.sevenzero;

import java.util.Calendar;
import java.util.Date;

import org.sevenzero.dialog.GenderSelectDialog;
import org.sevenzero.dialog.GenderSelectDialog.Gender;
import org.sevenzero.dialog.GenderSelectDialog.GenderSelectListener;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.foxit.util.Util;

public class DateActivity extends Activity {
	
	private static final String tag = DateActivity.class.getSimpleName();
	
	private TextView gender;
	private TextView date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date);
		
		gender = (TextView) this.findViewById(R.id.gender);
		date   = (TextView) this.findViewById(R.id.date);
		
		gender.setOnClickListener(listener);
		date.setOnClickListener(listener);
		

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.date, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_date, container,
					false);
			return rootView;
		}
	}
	
	OnDateSetListener onDateSetListener = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			if (flag) {
				flag = false;
				Util.printLog(tag, "" + year + ", " + monthOfYear + ", " + dayOfMonth);
				date.setText("" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
				
				Calendar cal = Calendar.getInstance();
				cal.set(year, monthOfYear, dayOfMonth);
				Date date = cal.getTime();
				Util.printLog(tag, date.toString());
				Util.printLog(tag, Util.formatDate(date, "yyyy-MM-dd"));
				
			}
			else {
				Util.printLog(tag, "Second *** ");
			}
		}
	};
	
	GenderSelectListener genderListener = new GenderSelectListener() {

		@Override
		public void onGenderSelectListener(Gender gender) {
			Util.printLog(tag, gender.toString());
			switch (gender) {
			case GENDER_FEMALE:
				DateActivity.this.gender.setText("女");
				break;
			case GENDER_MALE:
				DateActivity.this.gender.setText("男");
				break;
			default:
				break;
			}
		}
	};
	
	boolean flag = false;
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.date:
				flag = true;
				DatePickerDialog dialog = new DatePickerDialog(DateActivity.this, onDateSetListener, 2000, 0, 1);
				dialog.show();
				break;
			case R.id.gender:
				GenderSelectDialog dialog2 = new GenderSelectDialog(DateActivity.this, genderListener);
				dialog2.show();
				break;
			default:
				break;
			}
		}
	};

}
