package org.sevenzero.dialog;

import org.sevenzero.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-10-10
 *
 */
public class GenderSelectDialog extends Dialog {
	
	private static final String tag = GenderSelectDialog.class.getSimpleName();
	
//	public static final int GENDER_MALE = 2;
	
//	public static final int GENDER_FEMALE = 3;
	
	private RadioGroup rGroup;
	private RadioButton female, male;
	private Button done;
	
	private GenderSelectListener listener;

	public GenderSelectDialog(Context context, GenderSelectListener listener) {
		super(context);
		this.listener = listener;
		this.setTitle(R.string.gender);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gender_select);
		
		rGroup = (RadioGroup) this.findViewById(R.id.gender_select);
		
		female = (RadioButton) this.findViewById(R.id.gender_female);
		male   = (RadioButton) this.findViewById(R.id.gender_male);
		
		done = (Button) this.findViewById(R.id.gender_done);
		
		done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int checkedId = rGroup.getCheckedRadioButtonId();
				Util.printLog(tag, "" + checkedId);
				
				switch (checkedId) {
				case R.id.gender_female:
					Util.printLog(tag, "" + female.getText());
					if (null != listener) {
						listener.onGenderSelectListener(Gender.GENDER_FEMALE);
					}
					break;
				case R.id.gender_male:
					Util.printLog(tag, "" + male.getText());
					if (null != listener) {
						listener.onGenderSelectListener(Gender.GENDER_MALE);
					}
					break;
				default:
					break;
				}
				dismiss();
			}
			
		});
		
		rGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				case R.id.gender_female:
					Util.printLog(tag, "" + female.getText());
					break;
				case R.id.gender_male:
					Util.printLog(tag, "" + male.getText());
					break;
				default:
					break;
				}
			}
		});
	}
	
	public enum Gender {
		GENDER_MALE,
		GENDER_FEMALE
	}
	
	public interface GenderSelectListener {
		
		public void onGenderSelectListener(Gender gender);
		
	}

}


