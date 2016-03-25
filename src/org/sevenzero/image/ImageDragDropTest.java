package org.sevenzero.image;

import org.sevenzero.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

/**
 * 
 * @author sevenzero
   *
 * @date 2014-5-29
 * 
 * 拖动
 * 
 * 拖动图片腾挪效果
 * 
 * 其中 在onTouch 代码中 如果返回 false 就不能捕捉到ACTION_MOVE 事件。

 对于onTouchEvent 中onTouch返回值

1 、如果return false 说明还没有消费onTouch事件，在执行onTouch里代码后，onTouch事件并没有结束。

2、如果return true 说明消费了onTouch事件 onTouch事件结束了

但在实际操作中 除了ACTION_DOWN事件以外，其余的事件只有返回true的那个方法才能捕捉到。所以 返回false的时候只能捕捉到每次的第一个DOWN事件 后面的MOVE 和UP事件就捕捉不到了。

 

DisplayMetics 类：

Andorid.util 包下的DisplayMetrics 类提供了一种关于显示的通用信息，如显示大小，分辨率和字体。

为了获取DisplayMetrics 成员，首先初始化一个对象如下：

DisplayMetrics metrics ＝ new DisplayMetrics(); 
getWindowManager().getDefaultDisplay().getMetrics; 

//getWindowManager() 获取显示定制窗口的管理器
//getDefaultDisplay() 获取默认显示Display对象
//getMetrics(dm) 通过Display对象的数据来初始化一个DisplayMetrics对象

 v.layout(left, top, right, bottom);

Assign a size and position to a view and all of its descendants

This is the second phase of the layout mechanism. (The first is measuring). In this phase, each parent calls layout on all of its children to position them. This is typically done using the child measurements that were stored in the measure pass(). Derived classes with children should override onLayout. In that method, they should call layout on each of their their children.
Parameters:
    l Left position, relative to parent 
    t Top position, relative to parent 
    r Right position, relative to parent 
    b Bottom position, relative to parent
 * 
   *
 */
public class ImageDragDropTest extends Activity {
	
	private ImageView image;
	
	private int width, height;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_drag);
		
		image = (ImageView) findViewById(R.id.imageDrag);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		width = dm.widthPixels;
		height = dm.heightPixels;
		
		image.setOnTouchListener(listener);
		
	}
	
	private OnTouchListener listener = new OnTouchListener() {
		
		private int lastx, lasty;
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lastx = (int) event.getRawX();
				lasty = (int) event.getRawY();
				break;
			case MotionEvent.ACTION_MOVE:
				int dx = (int) event.getRawX() - lastx;
				int dy = (int) event.getRawY() - lasty;
				
				int left = v.getLeft() + dx;
				int top = v.getTop() + dy;
				int right = v.getRight() + dx;
				int bottom = v.getBottom() + dy;
				
				if (left < 0) {
					left = 0;
					right = left + v.getWidth();
				}
				if (right > width) {
					right = width;
					left = right - v.getWidth();
				}
				if (top < 0) {
					top = 0;
					bottom = top + v.getHeight();
				}
				if (bottom > height) {
					bottom = height;
					top = bottom - v.getHeight();
				}
				
				v.layout(left, top, right, bottom);
				
				lastx = (int) event.getRawX();
				lasty = (int) event.getRawY();
				
				break;
			case MotionEvent.ACTION_UP:
				break;
			}
			
			return true;
		}
		
	};

}
