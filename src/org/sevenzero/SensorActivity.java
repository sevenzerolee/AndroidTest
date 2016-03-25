package org.sevenzero;

import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;

import com.foxit.util.Util;

/**
 * 
 * @author lkimac
 *
 * @since 2016-2-19
 * 
 * 测试传感器
 *
 */
public class SensorActivity extends Activity implements SensorEventListener {
	
	private static final String tag = SensorActivity.class.getSimpleName();
	
	private SensorManager sensorManager;
	private Vibrator vibrator;
//	private ActivityManager aManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		if (Util.checkNull(sensors)) {
			Util.printLog(tag, "" + null);
		}
		else {
			if (sensors.size() == 0) {
				Util.printLog(tag, "没有感应器");
			}
			else {
				Util.printLog(tag, "支持感应器 " + sensors.size());
				
				vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
				
				sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), 
						SensorManager.SENSOR_DELAY_NORMAL);
			}
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
//		Util.printLog(tag, "onSensorChanged ");
		
		float[] xyz = event.values;
//		for (float f : xyz) {
//			Util.printLog(tag, "" + f);
//		}
		
		int value = 10;
		int sensorType = event.sensor.getType();
		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
			if (Math.abs(xyz[0]) > value
					|| Math.abs(xyz[1]) > value
					|| Math.abs(xyz[2]) > value) {
				vibrator.vibrate(500L);
				Util.printLog(tag, "###");
			}
		}
        
//		// 读取摇一摇敏感值
//		int shakeSenseValue = Integer.parseInt(getResources().getString(
//				R.string.shakeSenseValue));
//		;
//		// values[0]:X轴，values[1]：Y轴，values[2]：Z轴
//		float[] values = event.values;
//
//		if (sensorType == Sensor.TYPE_ACCELEROMETER) {
//			if ((Math.abs(values[0]) > shakeSenseValue
//					|| Math.abs(values[1]) > shakeSenseValue || Math
//					.abs(values[2]) > shakeSenseValue)) {
//				// 触发事件，执行打开应用行为
//				vibrator.vibrate(500);
//			}
//		}               
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		Util.printLog(tag, "onAccuracyChanged");
		
	}
}
