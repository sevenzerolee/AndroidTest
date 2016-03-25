package org.sevenzero;

import java.util.ArrayList;
import java.util.List;

import org.sevenzero.view.WrapContentHeightViewPager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-11-20
 *
 */
public class GridTestActivity extends Activity {
	
	private static final String tag = GridTestActivity.class.getSimpleName();

//	private ViewPager viewPager;
	private WrapContentHeightViewPager viewPager;
	private List<View> mGridViews;
	private MyViewPagerAdapter myViewPagerAdapter;
	
	private List<String> gList;
	private List<GridAdapter> mGridViewAdapters;
//	private GridAdapter gAdapter;
//	private GridView grid;
	
	void initData() {
		if (null == gList) {
			gList = new ArrayList<String>();
		}
		for (int i=0; i<5; i++) {
			gList.add("Test-" + i);
		}
		
		if (null == mGridViews) {
			mGridViews = new ArrayList<View>();
		}
		
		if (null == mGridViewAdapters) {
			mGridViewAdapters = new ArrayList<GridAdapter>();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_layout_test);
		
		initData();
		
//		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager = (WrapContentHeightViewPager) findViewById(R.id.viewPager);
		
//		grid = (GridView) this.findViewById(R.id.gridView);
//		gAdapter = new GridAdapter(this, gList);
//		grid.setAdapter(gAdapter);
		
		loadView();
		
	}
	
	public static final int GRIDVIEW_COLUMN = 3;
	public static final int GRIDVIEW_ROW    = 2; 
	
	void loadView() {
		if (null != gList && gList.size() > 0) {
			Util.printLog(tag, "gList: " + gList.size());
			int pageCount = (gList.size() + GRIDVIEW_COLUMN * GRIDVIEW_ROW - 1) / (GRIDVIEW_COLUMN * GRIDVIEW_ROW);
			Util.printLog(tag, "pageCount: " + pageCount);
			
			mGridViewAdapters.clear();
			mGridViews.clear();
			
			LayoutInflater inflater = LayoutInflater.from(this);
			for (int i = 0; i < pageCount; i++) {
				View mView = inflater.inflate(R.layout.layout_gridview, null);
				GridView mGridView = (GridView) mView.findViewById(R.id.gridView);
				GridAdapter adapter = new GridAdapter(this, gList, i);
				
				mGridView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				
				mGridViewAdapters.add(adapter);
				mGridViews.add(mView);
			}
	
			myViewPagerAdapter = new MyViewPagerAdapter(mGridViews, viewPager);
			viewPager.setAdapter(myViewPagerAdapter);
			myViewPagerAdapter.notifyDataSetChanged();
			
			LayoutParams params = (LayoutParams) viewPager.getLayoutParams();
			Util.printLog(tag, "###" + params.height);
			
		}
	}
	
}

class MyViewPagerAdapter extends PagerAdapter {
	
	private ViewPager mViewPager;
	private List<View> mGridViews;
	
	public MyViewPagerAdapter(List<View> mGridViews, ViewPager mViewPager) {
		this.mViewPager = mViewPager;
		this.mGridViews = mGridViews;
	}

	@Override
	public int getCount() {
		return mGridViews.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		mViewPager.removeView((View) object);
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(mGridViews.get(position),
				new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
		return mGridViews.get(position);
	}
	
}

class GridAdapter extends BaseAdapter {
	
	private static final String tag = GridAdapter.class.getSimpleName();
	
	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	private int pagePosition;
	
	private static int count = GridTestActivity.GRIDVIEW_COLUMN * GridTestActivity.GRIDVIEW_ROW;
	
//	private int seletcItem = 0;
//	
//	public void setSeletcItem(int seletcItem) {
//		this.seletcItem = seletcItem;
//	}

	GridAdapter(Context context, List<String> list, int pagePosition) {
		this.context = context;
		this.list = list;
		this.pagePosition = pagePosition;
		Util.printLog(tag, "list " + list.size() + ", " + pagePosition);
		
		inflater = LayoutInflater.from(this.context);
	}

	@Override
	public int getCount() {
//		return list.size();
		int size = (list == null ? 0 : list.size() - count * pagePosition);
		Util.printLog(tag, "size: " + size);
		if (size > count) {
			return count;
		}
		else {
			return size > 0 ? size : 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return list.get(count * pagePosition + position);
	}

	@Override
	public long getItemId(int position) {
		return count * pagePosition + position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int nowPosition = count * pagePosition + position;
		HoldView view = null;
		if (null == convertView) {
			view = new HoldView();
			convertView = inflater.inflate(R.layout.grid_layout, null);
			view.tv = (TextView) convertView.findViewById(R.id.grid_item);
			
			convertView.setTag(view);
		}
		else {
			view = (HoldView) convertView.getTag();
		}
		
		view.tv.setText(list.get(nowPosition));
//		if (seletcItem == position) {
//			view.tv.setBackgroundResource(R.color.selected_x);
//		}
//		else {
//			view.tv.setBackgroundResource(R.color.unselected_x);
//		}
		
//		if (position == 0) {
//			view.tv.setBackgroundResource(R.color.selected);
//		}
		
		convertView.setOnClickListener(new ViewClick(nowPosition));
//		convertView.setOnTouchListener(new ViewTouch(convertView));
		
		return convertView;
	}
	
//	private View first = null;
	
	class ViewClick implements OnClickListener {

		private int id;
		
		public ViewClick(int id) {
			this.id = id;
		}
		
		@Override
		public void onClick(View v) {
			Util.printLog(tag, "ViewClick Click " + id);
//			setSeletcItem(id);
//			notifyDataSetInvalidated();
//			notifyDataSetChanged();
			
//			if (null != first) {
//				first.setBackgroundResource(R.color.unselected_x);
//			}
//			
//			v.setBackgroundResource(R.color.selected_x);
//			first = v;
		}
		
	}
	
	class ViewTouch implements OnTouchListener {
		
		View view;
		
		ViewTouch(View view) {
			this.view = view;
		}

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			if (MotionEvent.ACTION_DOWN == action) {
//				v.setBackgroundResource(R.color.selected);
//				log.info("Down");
			}
			else if (MotionEvent.ACTION_UP == action) {
//				v.setBackgroundResource(R.color.unselected);
//				log.info("Up");
			}
			
			return false;
		}
		
	}
	
	class HoldView {
		
		TextView tv;
		
		HoldView() {}
		
	}
	
}
