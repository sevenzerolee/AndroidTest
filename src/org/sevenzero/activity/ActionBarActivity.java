package org.sevenzero.activity;

import java.lang.reflect.Field;

import org.sevenzero.R;

import com.foxit.util.Util;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

public class ActionBarActivity extends Activity {
	
	private static final String tag = ActionBarActivity.class.getSimpleName();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar);
		
		// 与主题有关 theme 
		ActionBar ab = getActionBar();
		if (null != ab) {
			ab.setDisplayHomeAsUpEnabled(true);  
	    	ab.setHomeButtonEnabled(true);
		}
	}
	
	@Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        
        return true;  
    } 
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Util.printLog(tag, "" + item.getItemId());
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.add:
	        	
	            return true;
	        case R.id.delete:
	            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
    void forceShowOverflowMenu() {  
        try {  
            ViewConfiguration config = ViewConfiguration.get(this);  
            Field menuKeyField = ViewConfiguration.class  
                    .getDeclaredField("sHasPermanentMenuKey");  
            if (menuKeyField != null) {  
                menuKeyField.setAccessible(true);  
                menuKeyField.setBoolean(config, false);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	
}
