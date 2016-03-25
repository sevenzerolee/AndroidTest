package org.sevenzero;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.util.Log;

/**
 * 
 * @author linger
 *
 * @since 2015-6-1
 *
 */
public class MySetting extends PreferenceActivity {
	
	private static final String TAG = PreferenceActivity.class.getSimpleName();
	
	private static final String CHECK_BOX_PREFERENCE_2 = "kSet2";
	private CheckBoxPreference checkBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.preference);
		
		checkBox = (CheckBoxPreference) this.findPreference(CHECK_BOX_PREFERENCE_2);
	}
	
	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
			Preference preference) {
		Log.d(TAG, preference.getKey() + ", " + preferenceScreen.getKey());
		if (preference == checkBox) {
			if (checkBox.isEnabled()) {
				checkBox.setEnabled(false);
			}
			else {
				checkBox.setEnabled(true);
			}
			return true;
		}
		
		
		return false;
	}

}
