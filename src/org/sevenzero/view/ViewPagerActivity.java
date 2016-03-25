package org.sevenzero.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sevenzero.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devsmart.android.ui.HorizontalListView;
import com.foxit.util.Util;

public class ViewPagerActivity extends Activity {
	
//	private static final String tag = ViewPagerActivity.class.getSimpleName();
	
	
	private int[] imgIds;
	
	private List<ImageView> list;
	private ViewPager mViewPager;
	private ImageView images1, images2, images3, images4;
	private static final int INIT_POS = Integer.MAX_VALUE / 2;

//	private int oldPos = 0, curPos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager);
		
		imgIds = new int[]{R.drawable.anquan, R.drawable.bingli, R.drawable.yinhangka, R.drawable.shezhi};
		
		images1 = (ImageView) findViewById(R.id.imageView1);
		images2 = (ImageView) findViewById(R.id.imageView2);
		images3 = (ImageView) findViewById(R.id.imageView3);
		images4 = (ImageView) findViewById(R.id.imageView4);
		
		setImage(0);
		
		list = new ArrayList<ImageView>();
		for (int i=0; i<imgIds.length; i++) {
			ImageView img = new ImageView(this);
//			img.setBackgroundResource(imgIds[i]);
			img.setImageResource(imgIds[i]);
//			img.setScaleType(ScaleType.CENTER_CROP);
			
			list.add(img);
		}
		
		mViewPager = (ViewPager) findViewById(R.id.vptest);
		mViewPager.setAdapter(new ImageAdapter(list));
		
		setViewPagerCurrentItem(INIT_POS);  
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				setImage(pos % list.size());
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
//				Util.printLog(tag, "onPageScrolled " + arg0 + ", " + arg2);
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
//				Util.printLog(tag, "onPageScrollStateChanged " + arg0);
				
			}
			
			
		});
		
		// 设置viewPager轮播
		runnable = new Runnable() {
			@Override
			public void run() {
				int index = mViewPager.getCurrentItem();
				index++;
				mViewPager.setCurrentItem(index, true);
				handler.postDelayed(runnable, 2000);
			}
		};
		
		// 点击屏幕中的图片时，取消轮播
//		mViewPager.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				handler.removeCallbacks(runnable);
//				return false;
//			}
//		});
		
		HorizontalListView listview = (HorizontalListView) findViewById(R.id.listview);
		listview.setAdapter(new HorizontalListViewAdapter(this, Arrays.asList(dataObjects)));
		

//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}
	
	static String[] dataObjects = new String[] { "TextTest1", "TextTest2",
			"TextTest3", "TextTest4", "TextTest5", "TextTest6", "TextTest7", 
			"TextTest8", "TextTest9" };
	
	private Handler handler = new Handler();  
    private Runnable runnable;  
	
	/** 
     * 设置滑动的初始位置, 以保证viewpager初始化后可以顺利向左滑动 
     *  
     */  
    void setViewPagerCurrentItem(int itemPosition) {  
        int index = 0;  
        while (true) {  
            index = itemPosition % list.size();  
            if (index == 0)  
                break;  
            itemPosition++;  
        }  
        mViewPager.setCurrentItem(itemPosition);  
    }  
	
	void setImage(int pos) {
		images1.setImageResource(R.drawable.huiyuan);
		images2.setImageResource(R.drawable.huiyuan);
		images3.setImageResource(R.drawable.huiyuan);
		images4.setImageResource(R.drawable.huiyuan);
		
		switch (pos) {
		case 0:
			images1.setImageResource(R.drawable.heiyuan);
			break;
		case 1:
			images2.setImageResource(R.drawable.heiyuan);
			break;
		case 2:
			images3.setImageResource(R.drawable.heiyuan);
			break;
		case 3:
			images4.setImageResource(R.drawable.heiyuan);
			break;
		default:
			break;
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	@Override  
    protected void onResume() {  
        super.onResume();  
        //在这里开始viewPager轮播  
        handler.postDelayed(runnable, 2000);  
    }  
	
	@Override  
    protected void onPause() {  
        super.onPause();  
        // 在这里取消轮播  
        handler.removeCallbacks(runnable);  
    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pager, menu);
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
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_view_pager,
//					container, false);
//			return rootView;
//		}
//	}

}

/**
 * 
 * @author linger
 *
 * @since 2015-11-4
 * 
 * 横向列表
 *
 */
class HorizontalListViewAdapter extends BaseAdapter {
	
	static final String tag = HorizontalListViewAdapter.class.getSimpleName();
	
	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	
	public HorizontalListViewAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(this.context);
//		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
//	private boolean isFirst = true;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HoldView view = null;
		if (null == convertView) {
			view = new HoldView();
			convertView = inflater.inflate(R.layout.listview_test_item_h, null);
//			view.layout = (LinearLayout) convertView.findViewById(R.id.listview_item_layout);
			view.text  = (TextView) convertView.findViewById(R.id.listview_item_h);
			
			convertView.setTag(view);
		}
		else {
			view = (HoldView) convertView.getTag();
		}
		
		view.text.setText(list.get(position));
		
//		if (isFirst && position == 0) {
//			isFirst = false;
//			view.text.setBackgroundResource(R.drawable.shape_yellow);
//			int colorId = context.getResources().getColor(R.color.white);
//			view.text.setTextColor(colorId);
//			first = view.text;
//		}
		
		convertView.setOnClickListener(new ViewClick(position));
		
		return convertView;
	}
	
	private TextView first;
	
	class ViewClick implements OnClickListener {
		
		private int id;
		
		ViewClick(int id) {
			this.id = id;
		}

		@Override
		public void onClick(View v) {
			Util.printLog(tag, list.get(id));
			HoldView view = (HoldView) v.getTag();
			
			if (null != first) {
				int colorId = context.getResources().getColor(R.color.black);
				first.setTextColor(colorId);
//				first.setBackground(null);
				first.setBackgroundResource(R.drawable.shape);
				
			}
			
			int colorId = context.getResources().getColor(R.color.white);
			view.text.setTextColor(colorId);
			view.text.setBackgroundResource(R.drawable.shape_yellow);
			first = view.text;
		}
		
	}
	
	class HoldView {
		
		TextView text;
		LinearLayout layout;
		
		HoldView() {}
		
	}
	
}

/**
 * 
 * @author linger
 *
 * @since 2015-11-4
 * 
 * 图片循环
 *
 */
class ImageAdapter extends PagerAdapter {
	
	private static final String tag = ImageAdapter.class.getSimpleName();
	
	private List<ImageView> list;
	
	public ImageAdapter(List<ImageView> list) {
		this.list = list;
	}

	@Override
	public int getCount() {
//		Util.printLog(tag, "getcount");
//		return list.size();
		return Integer.MAX_VALUE;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//		Util.printLog(tag, "destroyItem");
		container.removeView(list.get(position % list.size()));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
//		Util.printLog(tag, "isViewFromObject");
		return arg0 == arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
//		Util.printLog(tag, "instantiateItem");
		ImageView view = list.get(position % list.size());
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Util.printLog(tag, "position " + position);
				
			}
		});
		
		container.addView(view);
		return list.get(position % list.size());
	}
	
}
