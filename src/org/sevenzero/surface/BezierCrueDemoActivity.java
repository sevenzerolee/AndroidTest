package org.sevenzero.surface;

import android.app.Activity;
import android.os.Bundle;

public class BezierCrueDemoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MySurfaceView(this));
	}

}
