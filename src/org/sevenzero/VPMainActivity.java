package org.sevenzero;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class VPMainActivity extends Activity {
	
	private ViewPager vp = null;
	private Context ctx = null;
	private VPPagerAdapter adapter = null;
	private LayoutInflater inflater = null;
	private List<View> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vp_activity_main);
		ctx = this;
		adapter = new VPPagerAdapter();
		vp = (ViewPager) findViewById(R.id.viewpager);
		
		
		inflater = getLayoutInflater();
		list = new ArrayList<View>();
		
		list.add(addImageView(R.drawable.cat));
		list.add(addImageView(R.drawable.file));
		list.add(addImageView(R.drawable.cat2));
		list.add(addImageView(R.drawable.folder));
		
		vp.setAdapter(adapter);
		
	}
	
	View addImageView(final int id) {
		ImageView iv = new ImageView(this);
		iv.setImageResource(id);
		return iv;
	}
	
	class VPPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			((ViewPager) container).addView(list.get(position), 0);
			return list.get(position);
//			return super.instantiateItem(container, position);
		}
		
		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			((ViewPager) container).removeView(list.get(position));
//			super.destroyItem(container, position, object);
		}
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.vpmain, menu);
//		return true;
//	}

}
