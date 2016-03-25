package org.sevenzero.dialog;

import org.sevenzero.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * 
 * @author linger
 *
 * @since 2015-9-15
 * 
 * 自定义 Dialog
 *
 */
public class DefineDialog extends Dialog {
	
	private static final String tag = DefineDialog.class.getSimpleName();

	public DefineDialog(Context context) {
		super(context, R.style.define_dialog);
		this.setTitle(R.string.alertdialog_test);
		this.setCancelable(false);
		
//		LayoutParams params = getWindow().getAttributes();
//		float density = context.getResources().getDisplayMetrics().density;
//		Util.printLog(tag, "... " + density);
		
//		params.width = (int) (100 * density);
//		params.height = (int) (100 * density);
//		params.x = 20;
//		params.y = 40;
//		params.width = 140;
//		params.height = 80;
//		params.gravity = Gravity.CENTER;
//		getWindow().setAttributes(params);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.definedialog);
	}

}
