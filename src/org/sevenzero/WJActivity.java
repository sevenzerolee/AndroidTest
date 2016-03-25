package org.sevenzero;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author sevenzero
 * 
 * 文件目录测试
   *
   *
 */
public class WJActivity extends Activity {
	
	private static final String SDCARD = "/mnt/sdcard/";

	private TextView tvpath;
	private ListView lvFiles;
	private Button btnParent;
	private Button btRoot;

	// 记录当前的父文件夹
	private File currentParent;
	private File root;
	// 记录当前路径下的所有文件夹的文件数组
	private File[] currentFiles;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sdcard_listview);

		lvFiles = (ListView) this.findViewById(R.id.files);
		tvpath = (TextView) this.findViewById(R.id.tvpath);
		btnParent = (Button) this.findViewById(R.id.btnParent);
		btRoot = (Button) this.findViewById(R.id.btRoot);

		// 获取系统的SDCard的目录
		root = new File(SDCARD);
		// 如果SD卡存在的话
		if (root.exists()) {
			currentParent = root;
			currentFiles = root.listFiles();
			// 使用当前目录下的全部文件、文件夹来填充ListView
			inflateListView(currentFiles);
		}

		lvFiles.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				// 如果用户单击了文件，直接返回，不做任何处理
				if (currentFiles[position].isFile()) {
					// 也可自定义扩展打开这个文件等
					Toast.makeText(WJActivity.this, "文件打不开的", Toast.LENGTH_LONG)
							.show();
					return;
				}
				// 获取用户点击的文件夹 下的所有文件
				File[] tem = currentFiles[position].listFiles();
				if (tem == null || tem.length == 0) {
					Toast.makeText(WJActivity.this, "当前路径不可访问或者该路径下没有文件",
							Toast.LENGTH_LONG).show();
				}
				else {
					// 获取用户单击的列表项对应的文件夹，设为当前的父文件夹
					currentParent = currentFiles[position];
					// 保存当前的父文件夹内的全部文件和文件夹
					currentFiles = tem;
					// 再次更新ListView
					inflateListView(currentFiles);
				}

			}
		});

		// 获取上一级目录
		btnParent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					if (!currentParent.getCanonicalPath().equals("/mnt/sdcard")) {
						// 获取上一级目录
						currentParent = currentParent.getParentFile();
						// 列出当前目录下的所有文件
						currentFiles = currentParent.listFiles();
						// 再次更新ListView
						inflateListView(currentFiles);
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// 回到根目录
		btRoot.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				currentParent = root;
				currentFiles = root.listFiles();
				// 使用当前目录下的全部文件、文件夹来填充ListView
				inflateListView(currentFiles);
			}
		});

	}

	/**
	 * 根据文件夹填充ListView
	 * 
	 * @param files
	 */
	private void inflateListView(File[] files) {

		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < files.length; i++) {

			Map<String, Object> listItem = new HashMap<String, Object>();

			if (files[i].isDirectory()) {
				// 如果是文件夹就显示的图片为文件夹的图片
				listItem.put("icon", R.drawable.folder);
			}
			else {
				listItem.put("icon", R.drawable.file);
			}
			// 添加一个文件名称
			listItem.put("filename", files[i].getName());

			File myFile = new File(files[i].getName());

			// 获取文件的最后修改日期
			long modTime = myFile.lastModified();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(dateFormat.format(new Date(modTime)));

			// 添加一个最后修改日期
			listItem.put("modify", "修改日期：" + dateFormat.format(new Date(modTime)));

			listItems.add(listItem);

		}

		// 定义一个SimpleAdapter
		SimpleAdapter adapter = new SimpleAdapter(WJActivity.this, listItems,
				R.layout.sdcard_listview_item, new String[] { "filename", "icon",
						"modify" }, new int[] { R.id.file_name, R.id.icon,
						R.id.file_modify });
		// 填充数据集
		lvFiles.setAdapter(adapter);

		try {
			tvpath.setText("当前路径为:" + currentParent.getCanonicalPath());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}
