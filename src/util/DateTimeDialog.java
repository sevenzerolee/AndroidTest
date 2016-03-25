package util;

import java.util.Calendar;

import org.sevenzero.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.foxit.util.Util;

/**
 * 
 * @author linger
 * 
 * @since 2015-12-8
 * 
 *        日期和时间
 * 
 */
public class DateTimeDialog extends Dialog implements OnDateChangedListener,
		OnTimeChangedListener {
	
	private static final String tag = DateTimeDialog.class.getSimpleName();

	private Context context;
	private String initDateTimer;

	private DatePicker datePicker;
	private TimePicker timePicker;
	private String dateTime;
	
	private Calendar date;
	private NumberPicker numberPickerDate, numberPickerTime;
	private String[] dateDisplayValues;
	private int day;
	
	private Button ok, cancel;

	public DateTimeDialog(Context context, String initDateTimer, int day) {
		super(context);
		this.context = context;
		this.initDateTimer = initDateTimer;
		this.day = day;
		Util.printLog(tag, initDateTimer);
		
		date = Calendar.getInstance();
		dateDisplayValues = new String[day];
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_date_time);

		setTitle("预约时间");
		
		datePicker = (DatePicker) findViewById(R.id.datePicker1);
		timePicker = (TimePicker) findViewById(R.id.timePicker1);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);
		
		numberPickerDate = (NumberPicker) findViewById(R.id.numberPickerDate);
		numberPickerDate.setMinValue(0);
		numberPickerDate.setMaxValue(day - 1);
		updateDateControl();
		
		numberPickerTime = (NumberPicker) findViewById(R.id.numberPickerTime);
		numberPickerTime.setMinValue(0);
		numberPickerTime.setMaxValue(23);
		
		
		
		
		
		
		// ----------------------------
		// ----------------------------
		Calendar cal = Calendar.getInstance();;
//		if (!Util.checkEmptyOrNull(initDateTimer)) {
//			cal = getCalendarByInintData(initDateTimer);
//		}
//		else {
//			cal = Calendar.getInstance();
//		}
		datePicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 
				cal.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(cal.get(Calendar.MINUTE));
		
		ok = (Button) findViewById(R.id.set_datetime);
		cancel = (Button) findViewById(R.id.cancel);
		
		ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Util.printLog(tag, "设置时间 " + dateTime);
				dismiss();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				
			}
		});
		
	}
	
	void updateDateControl() {  
        /** 
         * 星期几算法 
         */  
        Calendar cal = Calendar.getInstance();  
        cal.setTimeInMillis(date.getTimeInMillis());  
//        cal.add(Calendar.DAY_OF_YEAR, -7 / 2 - 1);
        cal.add(Calendar.DAY_OF_YEAR, -1);  
        numberPickerDate.setDisplayedValues(null);  
        for (int i = 0; i < day; i++) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            dateDisplayValues[i] = (String) DateFormat.format("yyyy-MM-dd EEEE", cal);  
        }  
        numberPickerDate.setDisplayedValues(dateDisplayValues);  
        numberPickerDate.setValue(0);  
        numberPickerDate.invalidate();  
    }  

	Calendar getCalendarByInintData(String initDateTime) {
		Calendar calendar = Calendar.getInstance();

		// 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
		String date = spliteString(initDateTime, "日", "index", "front"); // 日期
		String time = spliteString(initDateTime, "日", "index", "back"); // 时间

		String yearStr = spliteString(date, "年", "index", "front"); // 年份
		String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

		String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
		String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

		String hourStr = spliteString(time, ":", "index", "front"); // 时
		String minuteStr = spliteString(time, ":", "index", "back"); // 分

		int currentYear = Integer.valueOf(yearStr.trim()).intValue();
		int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
		int currentDay = Integer.valueOf(dayStr.trim()).intValue();
		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

		calendar.set(currentYear, currentMonth, currentDay, currentHour,
				currentMinute);
		return calendar;
	}

	/**
	 * 截取子串
	 * 
	 * @param srcStr
	 *            源串
	 * @param pattern
	 *            匹配模式
	 * @param indexOrLast
	 * @param frontOrBack
	 * @return
	 */
	public static String spliteString(String srcStr, String pattern,
			String indexOrLast, String frontOrBack) {
		String result = "";
		int loc = -1;
		if (indexOrLast.equalsIgnoreCase("index")) {
			loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
		}
		else {
			loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
		}
		if (frontOrBack.equalsIgnoreCase("front")) {
			if (loc != -1)
				result = srcStr.substring(0, loc); // 截取子串
		}
		else {
			if (loc != -1)
				result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
		}
		return result;
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		onDateChanged(null, 0, 0, 0);
	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// 获得日历实例  
        Calendar calendar = Calendar.getInstance();  
  
        calendar.set(datePicker.getYear(), datePicker.getMonth(),  
                datePicker.getDayOfMonth(), timePicker.getCurrentHour(),  
                timePicker.getCurrentMinute());  
        
        dateTime = Util.formatDate(calendar.getTime(), "yyyy年MM月dd日 HH:mm");
        Util.printLog(tag, "修改时间 " + dateTime);  
	}

}
