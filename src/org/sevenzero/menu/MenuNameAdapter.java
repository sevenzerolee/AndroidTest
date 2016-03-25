package org.sevenzero.menu;

import java.util.List;

import org.sevenzero.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MenuNameAdapter extends BaseAdapter {

	private List<String> menuName;
	private Context context;
	private final TextView[] menu;

	public MenuNameAdapter(Context context, List<String> menuName) {
		this.context = context;
		this.menuName = menuName;
		menu = new TextView[menuName.size()];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return menuName.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/**
	 * 选中后，改变菜单颜色。
	 * 
	 * @param position
	 */
	public void setFocus(int position) {
		for (int i = 0; i < menuName.size(); i++) {
			menu[i].setBackgroundColor(Color.WHITE);
		}
		menu[position].setBackgroundColor(Color.BLUE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 菜单栏文字项
		menu[position] = new TextView(context);
		menu[position].setGravity(Gravity.CENTER);
		menu[position].setText(menuName.get(position));
		menu[position].setTextSize(18);
		menu[position].setLayoutParams(new GridView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		menu[position].setBackgroundResource(R.drawable.shape_dialog_menu);

		return menu[position];
	}

}
