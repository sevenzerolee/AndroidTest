package org.sevenzero;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.foxit.util.Util;

/**
 * 
 * @author sevenzero
   *
 * @since 2012-7-17
   *
 */
public class ListViewTest extends Activity {
	
	static final String tag = ListViewTest.class.getSimpleName();
	
	private LinearLayout header, footer;
	private TextView header2, header3, header4, header5;
	
	private ListView listview;
	private MyAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.listviewtest);
		
		header = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.listviewtest_header, null);
		header2 = (TextView) header.findViewById(R.id.listview_header);
		header2.setOnClickListener(listener);
		footer = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.listviewtest_footer, null);
		
		listview = (ListView) this.findViewById(R.id.listview_test);
		listview.addHeaderView(header);
		listview.addFooterView(footer);
		
		adapter = new MyAdapter(this, getData());
		listview.setAdapter(adapter);
//		listview.setItemChecked(0, true);
		listview.setOnItemClickListener(new OnItemClickListener() {

//			private View first = null;
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Util.printLog(tag, "click" + position);
				
				adapter.setSeletcItem(position);
//				adapter.notifyDataSetInvalidated();
				adapter.notifyDataSetChanged();

//				if (null != first) {
//					first.setBackgroundResource(R.color.unselected_x);
//				}
//				
//				view.setBackgroundResource(R.color.selected_x);
//				first = view;
			}
		});
//		listview.setSelection(0);
//		listview.setSelector(new ColorDrawable(Color.BLUE));
	}
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.listview_header:
				Util.printLog(tag, "header2");
				
				break;
			default:
				break;
			}
			
		}
	};
	
	private List<String> getData() {
		List<String> list = new ArrayList<String>();
		for (int i=0; i<20; i++) {
			list.add("Test-" + i);
		}
		
		return list;
	}

}

class MyAdapter extends BaseAdapter {
	
	private Logger log = Logger.getLogger(MyAdapter.class.getSimpleName());
	
	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	
	private int seletcItem = 0;
	
	public void setSeletcItem(int seletcItem) {
		this.seletcItem = seletcItem;
	}

	MyAdapter(Context context, List<String> list) {
		this.context = context;
		this.list = list;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HoldView view = null;
		if (null == convertView) {
			view = new HoldView();
			convertView = inflater.inflate(R.layout.listviewtest_item, null);
			view.tv = (TextView) convertView.findViewById(R.id.listview_item);
			
			convertView.setTag(view);
		}
		else {
			view = (HoldView) convertView.getTag();
		}
		
		view.tv.setText(list.get(position));
		if (seletcItem == position) {
			view.tv.setBackgroundResource(R.color.selected_x);
		}
		else {
			view.tv.setBackgroundResource(R.color.unselected_x);
		}
		
//		if (position == 0) {
//			view.tv.setBackgroundResource(R.color.selected);
//		}
		
		convertView.setOnClickListener(new ViewClick(position));
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
			log.info("ViewClick Click " + id);
			setSeletcItem(id);
//			notifyDataSetInvalidated();
			notifyDataSetChanged();
			
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
				log.info("Down");
			}
			else if (MotionEvent.ACTION_UP == action) {
//				v.setBackgroundResource(R.color.unselected);
				log.info("Up");
			}
			
			return false;
		}
		
	}
	
	class HoldView {
		
		TextView tv;
		
		HoldView() {}
		
	}
	
}
