package org.sevenzero.baidu;

import org.sevenzero.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.foxit.util.Util;

/**
 * 
 * @author linger
 *
 * @since 2015-9-11
 *
 */
public class BaiduMapTestActivity extends Activity {
	
	private static final String tag = BaiduMapTestActivity.class.getSimpleName();
	
	/**
	 * MapView 是地图主控件
	 */
	private MapView mMapView = null;
	
	private BaiduMap mBaiduMap = null;
	
	boolean isFirstLoc = true;// 是否首次定位
	private boolean isRequest = false; // 是否手动定位 
	
	/**
	 * 定位核心类
	 */
	private LocationClient mLocClient;
	
	private Button customRequest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.activity_baidu_map_test);
		
		//获取地图控件引用  
        mMapView = (MapView) findViewById(R.id.bmapView);
        
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMaxAndMinZoomLevel(12, 17);
        mBaiduMap.setMyLocationEnabled(true);
        //  BitmapDescriptorFactory.fromResource(R.drawable.icon_geo)
        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(
        		MyLocationConfiguration.LocationMode.NORMAL, true, null));
        
        MapStatusUpdate u = MapStatusUpdateFactory.zoomTo(16);
		mBaiduMap.animateMapStatus(u);
        
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(new MyLocationListener());
        
        LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(2000); // 2s
		mLocClient.setLocOption(option);
		mLocClient.start();
		
		customRequest = (Button) this.findViewById(R.id.custom_request);
		customRequest.setOnClickListener(listener);
        
//        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        List<String> list = lm.getProviders(true);
//        
//        String provider = null;
//        if (list.contains(LocationManager.NETWORK_PROVIDER)) {
//        	provider = LocationManager.NETWORK_PROVIDER;
//        }
//        else if (list.contains(LocationManager.GPS_PROVIDER)) {
//        	provider = LocationManager.GPS_PROVIDER;
//        }
//        else {
//        	Util.printLog(TAG, "没有提供位置服务设备");
//        	return;
//        }
//        
//        Location loc = lm.getLastKnownLocation(provider);
//        if (null != loc) {
//        	
//        	
//        }
        
//		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction()
//					.add(R.id.container, new PlaceholderFragment()).commit();
//		}
	}
	
	class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			Util.printLog(tag, "... " + location.getLatitude() * 1E6);
			MyLocationData locData = new MyLocationData.Builder()
					.accuracy(location.getRadius())
					// 此处设置开发者获取到的方向信息，顺时针0-360
					.direction(100).latitude(location.getLatitude())
					.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc || isRequest) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude(),
						location.getLongitude());
				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
				
				isRequest = false;
			}
			
		}
		
	}
	
	@Override
	protected void onDestroy() {
		Util.printLog(tag, "ondestroy");
		super.onDestroy();
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		Util.printLog(tag, "onresume");
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		Util.printLog(tag, "onpause");
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.baidu_map_test, menu);
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_baidu_map_test,
					container, false);
			return rootView;
		}
	}
	
	/** 
     * 手动请求定位的方法 
     */  
    void requestLocation() {  
        isRequest = true;  
          
        if(mLocClient != null && mLocClient.isStarted()){  
            Util.printLog(tag, "正在定位......");  
            mLocClient.requestLocation();  
        }
        else{  
            Util.printLog(tag, "locClient is null or not started"); 
        }  
    }  
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.custom_request:
				requestLocation();
				break;
			}
			
		}
	};

}
