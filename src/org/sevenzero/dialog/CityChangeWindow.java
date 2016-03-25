package org.sevenzero.dialog;

import org.sevenzero.BackActivity;
import org.sevenzero.R;
import org.sevenzero.list.GroupListActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-11-13
 *
 */
public class CityChangeWindow extends PopupWindow {
	
	static final String tag = CityChangeWindow.class.getSimpleName();
	
	private Context context;
	
	private View view;
	private Button change;
	
	public CityChangeWindow(Context ctx) {
		Util.printLog(tag, "init");
		this.context = ctx;
		
		LayoutInflater inflater = LayoutInflater.from(this.context);
		view = inflater.inflate(R.layout.city_change_layout, null);
		
		change = (Button) view.findViewById(R.id.cityChange);
		change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent();
				intent.setClass(context, GroupListActivity.class);
				context.startActivity(intent);
				
				dismiss();
				
			}
		});
		
		//设置SelectPicPopupWindow的View  
        this.setContentView(view);  
        //设置SelectPicPopupWindow弹出窗体的宽  
        this.setWidth(LayoutParams.MATCH_PARENT);  
        //设置SelectPicPopupWindow弹出窗体的高  
        this.setHeight(LayoutParams.WRAP_CONTENT);  
        //设置SelectPicPopupWindow弹出窗体可点击  
        this.setFocusable(true);  
        //设置SelectPicPopupWindow弹出窗体动画效果  
//        this.setAnimationStyle(R.style.AnimBottom);  
        //实例化一个ColorDrawable颜色为半透明  
        ColorDrawable dw = new ColorDrawable(0xb0000000);  
        //设置SelectPicPopupWindow弹出窗体的背景  
        this.setBackgroundDrawable(dw);  
        view.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				
				int height = view.findViewById(R.id.city_list).getTop();
				int y=(int) event.getY();
				if(event.getAction() == MotionEvent.ACTION_UP){
					if(y<height){
						dismiss();
					}
				}				
				return true;
			}
		});
	}

}
