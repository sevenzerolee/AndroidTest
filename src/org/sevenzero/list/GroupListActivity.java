package org.sevenzero.list;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.sevenzero.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import com.foxit.util.Util;

/**
 * 
 * @author sevenzero
   *
 * @since 2015-11-12
   *
 */
public class GroupListActivity extends Activity {
	
	static final String tag = GroupListActivity.class.getSimpleName();
	
	private SearchView search;
	private ListView listview;
	private LinearLayout right, empty;
	private TextView label;
	private MyAdapter adapter;
	
	private String[] tags = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	private String[] datas = { "android", "java", "news", "baidu", "oberser",
			"mary", "next", "ruby", "money", "lucy", "very", "thunder",
			"object", "lily", "jay", "answer", "layout", "demos", "com",
			"collect", "custom", "blog", "round", "redirect", "ground", "gray",
			"blue", "zone", "james", "zhang", "location" };
	
	private List<String> list;
	private List<String> init;
	// 存放含有索引字母的位置
	private HashMap<String, Integer> selector;
	
	
	void initData() {
		list.addAll(Arrays.asList(tags));
		list.addAll(Arrays.asList(datas));
		
		StringComparator comparator = new StringComparator();
		Collections.sort(list, comparator);
	}
	
	OnQueryTextListener queryTextListener = new OnQueryTextListener() {
		
		@Override
		public boolean onQueryTextSubmit(String query) {
			Util.printLog(tag, "onQueryTextSubmit " + query);
			
			return true;
		}
		
		@Override
		public boolean onQueryTextChange(String newText) {
			Util.printLog(tag, "搜索内容" + newText);
			
			List<String> newValues = new ArrayList<String>();
			String s = newText.trim().toLowerCase();
			if (Util.checkEmptyOrNull(s)) {
				right.setVisibility(View.VISIBLE);
//				listview.clearTextFilter();
				
				newValues = init;
				
//				adapter = new MyAdapter(GroupListActivity.this, list, init, tags, selector);
//				listview.setAdapter(adapter);
			}
			else {
				right.setVisibility(View.GONE);
//				listview.setFilterText(newText);
				
				for (String str : init) {
					if (str.trim().toLowerCase().contains(s) && str.trim().length() != 1) {
						newValues.add(str);
					}
				}
				
				if (newValues.size() == 0) {
					empty.setVisibility(View.VISIBLE);
					listview.setVisibility(View.GONE);
					return true;
				}
			}
			
			empty.setVisibility(View.GONE);
			listview.setVisibility(View.VISIBLE);
			
			adapter.setList(newValues);
			adapter.notifyDataSetChanged();
			
			return true;
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		this.setContentView(R.layout.activity_group_list);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_titlebar);
		
		
		right = (LinearLayout) this.findViewById(R.id.right);
		empty = (LinearLayout) this.findViewById(R.id.layout_content_empty);
		empty.setVisibility(View.GONE);
		label = (TextView) this.findViewById(R.id.label);
		
		list = new ArrayList<String>();
		initData();
		init = list;
		
		search = (SearchView) this.findViewById(R.id.searchView);
		search.setIconifiedByDefault(false);
		search.setQueryHint("城市");
		search.setFocusable(false);
		search.setIconified(false);
		search.clearFocus();
		
		listview = (ListView) this.findViewById(R.id.grouplistview);
		selector = new HashMap<String, Integer>();
		adapter = new MyAdapter(this, list, init, tags, selector);
		listview.setAdapter(adapter);
		
		
//		listview.setTextFilterEnabled(true);
		
		
		search.setOnQueryTextListener(queryTextListener);
		search.setSubmitButtonEnabled(false);
		
		
		
		// 无效果
		search.setOnCloseListener(new OnCloseListener() {
			
			@Override
			public boolean onClose() {
				Util.printLog(tag, "onClose");
				return true;
			}
		});
		
//		setSearchViewBackground(search);
		
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
////			private View first = null;
//			
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Util.printLog(tag, "click" + position);
//				
//				adapter.setSeletcItem(position);
//				adapter.notifyDataSetChanged();
//				
////				adapter.notifyDataSetInvalidated();
//
////				if (null != first) {
////					first.setBackgroundResource(R.color.unselected_x);
////				}
////				
////				view.setBackgroundResource(R.color.selected_x);
////				first = view;
//			}
//		});
//		listview.setSelection(0);
//		listview.setSelector(new ColorDrawable(Color.BLUE));
	}
	
	public void setSearchViewBackground(SearchView searchView) {  
        try {  
            Class<?> argClass = searchView.getClass();  
            // 指定某个私有属性  
            Field ownField = argClass.getDeclaredField("mSearchPlate"); // 注意mSearchPlate的背景是stateListDrawable(不同状态不同的图片)  
                                                                        // 所以不能用BitmapDrawable  
            // setAccessible 它是用来设置是否有权限访问反射类中的私有属性的，只有设置为true时才可以访问，默认为false  
            ownField.setAccessible(true);  
            View mView = (View) ownField.get(searchView);  
            mView.setBackgroundDrawable(getResources().getDrawable(  
                    R.drawable.ic_launcher));  

            // 指定某个私有属性  
            Field mQueryTextView = argClass.getDeclaredField("mQueryTextView");  
            mQueryTextView.setAccessible(true);  
            Class<?> mTextViewClass = mQueryTextView.get(searchView).getClass()  
                    .getSuperclass().getSuperclass().getSuperclass();  

            // mCursorDrawableRes光标图片Id的属性  
            // 这个属性是TextView的属性，所以要用mQueryTextView（SearchAutoComplete）的父类（AutoCompleteTextView）的父  
            // 类( EditText）的父类(TextView)  
            Field mCursorDrawableRes = mTextViewClass  
                    .getDeclaredField("mCursorDrawableRes");  

            // setAccessible 它是用来设置是否有权限访问反射类中的私有属性的，只有设置为true时才可以访问，默认为false  
            mCursorDrawableRes.setAccessible(true);  
            mCursorDrawableRes.set(mQueryTextView.get(searchView),  
                    R.drawable.ic_launcher);// 注意第一个参数持有这个属性(mQueryTextView)的对象(mSearchView)  
                                                    // 光标必须是一张图片不能是颜色，因为光标有两张图片，一张是第一次获得焦点的时候的闪烁的图片，一张是后边有内容时候的图片，如果用颜色填充的话，就会失去闪烁的那张图片，颜色填充的会缩短文字和光标的距离（某些字母会背光标覆盖一部分）。  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		return true;
	}
	
	private int height;
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0
		Util.printLog(tag, "layoutIndex.getHeight() = " + right.getHeight());
		
		height = right.getHeight() / tags.length;
		getIndexView();
	}
	
	/** 绘制索引列表 */
	void getIndexView() {
		LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, height);
		// params.setMargins(10, 5, 10, 0);
		final int len = tags.length;
		for (int i = 0; i < len; i++) {
			final TextView tv = new TextView(this);
			tv.setLayoutParams(params);
			tv.setText(tags[i]);
			// tv.setTextColor(Color.parseColor("#606060"));
			// tv.setTextSize(16);
			tv.setPadding(10, 0, 10, 0);
			right.addView(tv);
			right.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event)

				{
					float y = event.getY();
					int index = (int) (y / height);
					if (index > -1 && index < len) {// 防止越界
						String key = tags[index];
						if (selector.containsKey(key)) {
							int pos = selector.get(key);
							if (listview.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
								listview.setSelectionFromTop(
										pos + listview.getHeaderViewsCount(), 0);
							} 
							else {
								listview.setSelectionFromTop(pos, 0);// 滑动到第一项
							}
							label.setVisibility(View.VISIBLE);
							label.setText(tags[index]);
						}
					}
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						right.setBackgroundColor(Color.parseColor("#606060"));
						break;

					case MotionEvent.ACTION_MOVE:

						break;
					case MotionEvent.ACTION_UP:
						right.setBackgroundColor(Color
								.parseColor("#00ffffff"));
						label.setVisibility(View.GONE);
						break;
					}
					return true;
				}
			});
		}
	}
	
	List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<200; i++) {
			list.add("Test-" + i);
		}
		
		return list;
	}

}

class StringComparator implements Comparator<String> {

	/*
	 *  如果要按照升序排序，
		则o1 小于o2，返回-1（负数），相等返回0，01大于02返回1（正数）
		如果要按照降序排序
 		则o1 小于o2，返回1（正数），相等返回0，01大于02返回-1（负数）
 		
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String lhs, String rhs) {
		return lhs.compareToIgnoreCase(rhs);
	}
	
}

class MyAdapter extends BaseAdapter implements Filterable {
	
	private static final String tag = MyAdapter.class.getSimpleName();
	
	private Context context;
	private List<String> list;
	private List<String> init;
	private LayoutInflater inflater;
//	private List<String> searchList;
	
	private int seletcItem = 0;
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public void setSeletcItem(int seletcItem) {
		this.seletcItem = seletcItem;
	}

	MyAdapter(Context context, List<String> list, List<String> init, String[] tags, 
			HashMap<String, Integer> selector) {
		this.context = context;
		this.list = list;
		this.init = init;
		
//		this.searchList = new ArrayList<String>();
		
		if (null != tags && null != selector) {
			final int len = tags.length;
			final int size = list.size();
			for (int j = 0; j < len; j++) {// 循环字母表，找出nData中对应字母的位置
				for (int i = 0; i < size; i++) {
					if (list.get(i).equals(tags[j])) {
						selector.put(tags[j], i);
					}
				}
	
			}
		}
		
		inflater = LayoutInflater.from(this.context);
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
	
	// 搜索过滤数据
	class MyFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Util.printLog(tag, "performFiltering " + constraint.toString());
			FilterResults results = new FilterResults();
			List<String> newValues = new ArrayList<String>();
			
			String s = constraint.toString().trim().toLowerCase();
			if (Util.checkEmptyOrNull(s)) {
				newValues = init;
			}
			else {
				for (String str : init) {
					if (str.toLowerCase().contains(s) && str.length() != 1) {
						newValues.add(str);
					}
				}
			}
			results.values = newValues;
			results.count = newValues.size();
			
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			Util.printLog(tag, "publishResults");
			if (null != results) {
				list = (List<String>) results.values;
				if (results.count > 0) {
					// 通知数据发生了改变
					notifyDataSetChanged();
				}
				else {
					// 通知数据失效
					notifyDataSetInvalidated();
				}
			}
		}
		
	}
	
	private Filter filter = null;

	@Override
	public Filter getFilter() {
		Util.printLog(tag, "getFilter");
		if (null == filter) {
			filter = new MyFilter();
		}
		return filter;
	}
	
	@Override
	public boolean isEnabled(int position) {
		// 分组本身不能点击
		if (list.get(position).length() == 1) {
			return false;
		}
		
		return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		HoldView view = null;
		String item = list.get(position);
		// 分组
		if (item.length() == 1) {
			if (null == convertView) {
				view = new HoldView();
				convertView = inflater.inflate(R.layout.grouplist_item_tag, null);
				view.tv = (TextView) convertView.findViewById(R.id.grouplist_item_tag);
				view.layout = (LinearLayout) convertView.findViewById(R.id.grouplist_item_tag_layout);
				
				convertView.setTag(view);
			}
			else {
				view = (HoldView) convertView.getTag();
			}
//			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
//					LinearLayout.LayoutParams.WRAP_CONTENT);  
//			lp.setMargins(10, 0, 0, 0);
//			view.tv.setLayoutParams(lp);
			view.layout.setBackgroundColor(Color.parseColor("#FF9c9c9c"));
			
			convertView.setClickable(false);
		}
		// 分组数据
		else {
			if (null == convertView) {
				view = new HoldView();
				convertView = inflater.inflate(R.layout.grouplist_item, null);
				view.tv = (TextView) convertView.findViewById(R.id.grouplist_item);
				view.layout = (LinearLayout) convertView.findViewById(R.id.grouplist_item_layout);
//				view.line = (View) convertView.findViewById(R.id.grouplist_item_line);
				
				convertView.setTag(view);
			}
			else {
				view = (HoldView) convertView.getTag();
			}
//			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
//					LinearLayout.LayoutParams.WRAP_CONTENT);  
//			lp.setMargins(20, 0, 0, 0);
//			view.tv.setLayoutParams(lp);
			view.layout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
			
			convertView.setOnClickListener(new ViewClick(position));
		}
		
		
		
		view.tv.setText(list.get(position));
		
//		if (seletcItem == position) {
//			view.tv.setBackgroundResource(R.color.selected_x);
//		}
//		else {
//			view.tv.setBackgroundResource(R.color.unselected_x);
//		}
		
//		if (position == 0) {
//			view.tv.setBackgroundResource(R.color.selected);
//		}
		
//		convertView.setOnClickListener(new ViewClick(position));
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
			Util.printLog(tag, "ViewClick Click " + id + ", " + list.get(id));
			
//			setSeletcItem(id);
//			notifyDataSetChanged();
			
//			notifyDataSetInvalidated();
			
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
			}
			else if (MotionEvent.ACTION_UP == action) {
//				v.setBackgroundResource(R.color.unselected);
			}
			
			return false;
		}
		
	}
	
	class HoldView {
		
		TextView tv;
		View line;
		LinearLayout layout;
		
		HoldView() {}
		
	}
	
}
