/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.samples;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.sevenzero.R;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

public final class PullToRefreshListActivity extends ListActivity {
	
//	private static final String tag = PullToRefreshListActivity.class.getSimpleName();

	static final int MENU_MANUAL_REFRESH = 0;
	static final int MENU_DISABLE_SCROLL = 1;
	static final int MENU_SET_MODE = 2;
	static final int MENU_DEMO = 3;

	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
//	private ArrayAdapter<String> mAdapter;
	private MyAdapter adapter;
	
	private TextView empty;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_list);

		empty = (TextView) findViewById(R.id.empty);
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		
		
		
		mPullRefreshListView.setMode(Mode.BOTH);
		mPullRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel(getString(R.string.pull_to_load));  
		mPullRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(getString(R.string.loading));  
		mPullRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel(getString(R.string.release_to_load));

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
//				new GetDataTask().execute();
				
				if (mPullRefreshListView.isHeaderShown()) {
					new GetDataTask().execute();
//					Util.printLog(tag, "header --- ");
				}
				else if (mPullRefreshListView.isFooterShown()) {
					new GetDataTask2().execute();
				}
			}
		});

		// Add an end-of-list listener
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
//				Toast.makeText(PullToRefreshListActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
				// Do work to refresh the list here.
//				new GetDataTask2().execute();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));
		
		if (mListItems.size() > 0) {
			empty.setVisibility(View.GONE);
		}
		else {
			empty.setVisibility(View.VISIBLE);
		}

//		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
		
		adapter = new MyAdapter(this, mListItems);
		

		/**
		 * Add Sound Event Listener
		 */
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		mPullRefreshListView.setOnPullEventListener(soundListener);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
//		actualListView.setAdapter(mAdapter);
		actualListView.setAdapter(adapter);
		actualListView.setSelection(0);
		
	}
	
	static int count = 0;

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh... " + count++);
//			mListItems.addLast("Added after refresh bottom ... " + count++);
//			mAdapter.notifyDataSetChanged();
			adapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	class GetDataTask2 extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addLast("Added after refresh bottom ... " + count++);
//			mAdapter.notifyDataSetChanged();
			adapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_MANUAL_REFRESH, 0, "Manual Refresh");
		menu.add(0, MENU_DISABLE_SCROLL, 1,
				mPullRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
						: "Enable Scrolling while Refreshing");
		menu.add(0, MENU_SET_MODE, 0, mPullRefreshListView.getMode() == Mode.BOTH ? "Change to MODE_PULL_DOWN"
				: "Change to MODE_PULL_BOTH");
		menu.add(0, MENU_DEMO, 0, "Demo");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

		menu.setHeaderTitle("Item: " + getListView().getItemAtPosition(info.position));
		menu.add("Item 1");
		menu.add("Item 2");
		menu.add("Item 3");
		menu.add("Item 4");

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem disableItem = menu.findItem(MENU_DISABLE_SCROLL);
		disableItem
				.setTitle(mPullRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
						: "Enable Scrolling while Refreshing");

		MenuItem setModeItem = menu.findItem(MENU_SET_MODE);
		setModeItem.setTitle(mPullRefreshListView.getMode() == Mode.BOTH ? "Change to MODE_FROM_START"
				: "Change to MODE_PULL_BOTH");

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case MENU_MANUAL_REFRESH:
				new GetDataTask().execute();
				mPullRefreshListView.setRefreshing(false);
				break;
			case MENU_DISABLE_SCROLL:
				mPullRefreshListView.setScrollingWhileRefreshingEnabled(!mPullRefreshListView
						.isScrollingWhileRefreshingEnabled());
				break;
			case MENU_SET_MODE:
				mPullRefreshListView.setMode(mPullRefreshListView.getMode() == Mode.BOTH ? Mode.PULL_FROM_START
						: Mode.BOTH);
				break;
			case MENU_DEMO:
				mPullRefreshListView.demo();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private String[] mStrings = { "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler" };
	
//	private String[] mStrings = {};
}

class MyAdapter extends BaseAdapter {
	
	private Logger log = Logger.getLogger(MyAdapter.class.getSimpleName());
	
	private Context context;
	private List<String> list;
	private LayoutInflater inflater;
	
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
//		if (list.size() == 0) {
//			HoldView view = null;
//			if (null == convertView) {
//				convertView = inflater.inflate(R.layout.listview_test_item32, null);
//				view.tv = (TextView) convertView.findViewById(R.id.listview_item_item32);
//				convertView.setTag(view);
//			}
//			return convertView;
//		}
		
		HoldView view = null;
		if (null == convertView) {
			view = new HoldView();
			convertView = inflater.inflate(R.layout.listview_test_item3, null);
			view.tv = (TextView) convertView.findViewById(R.id.listview_item_3);
			
			view.detail = (LinearLayout) convertView.findViewById(R.id.detail_layout);
			view.detailInfo = (TextView) convertView.findViewById(R.id.detail_info);
			
			convertView.setTag(view);
		}
		else {
			view = (HoldView) convertView.getTag();
		}
		
		view.tv.setText(list.get(position));
		
		convertView.setOnClickListener(new ViewClick(view.detail));
//		convertView.setOnTouchListener(new ViewTouch(convertView));
		
		return convertView;
	}
	
	private LinearLayout first;
	
	class ViewClick implements OnClickListener {
		
		private LinearLayout layout;
		
		ViewClick(LinearLayout layout) {
			this.layout = layout;
		}

		@Override
		public void onClick(View v) {
			log.info("ViewClick ... ");
			
			if (null != first && first != layout) {
				first.setVisibility(View.GONE);
			}
			
			if (layout.getVisibility() == View.GONE) {
				layout.setVisibility(View.VISIBLE);
				first = layout;
			}
			else {
				layout.setVisibility(View.GONE);
			}
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
				v.setBackgroundResource(R.color.selected);
				log.info("Down");
			}
			else if (MotionEvent.ACTION_UP == action) {
				v.setBackgroundResource(R.color.unselected);
				log.info("Up");
			}
			
			return false;
		}
		
	}
	
	class HoldView {
		
		TextView tv;
		
		LinearLayout detail;
		TextView detailInfo;
		
		HoldView() {}
		
	}
	
}
