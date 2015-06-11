package com.icoding.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * 时间日期工具类
 * @author TOY
 * 创建于2014/06/23
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {
    
    private final static String TAG = "TimeUtils";
    
    private final static String[] DAY_WEEK = new String[]{"一","二","三","四","五","六","天"};
    
    public static String formatToWeek(final String date, final String pattern){
        String dayOfWeek = "";
        if(StringUtils.isEmpty(date) || StringUtils.isEmpty(pattern)){
            Log.w(TAG, "日期和格式符不能为空");
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date src = null;
        try {
            src = sdf.parse(date.toString());
        } catch (ParseException e) {
            Log.e(TAG, String.format("字符串  %d 转日期时  %d 出错.", date,pattern));
            return dayOfWeek;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(src);
        int _intDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        dayOfWeek = "星期" + DAY_WEEK[_intDayOfWeek - 1];
        return dayOfWeek;
    }
    
    /**
	 * 格式化当前时间,返回字符串
	 * @param formater
	 * @return
	 */
    public static String formateCurrentTime(String formater){
	       return new SimpleDateFormat(formater).format(new Date());
	}
    
    /**
	 * 格式化时间,返回字符串
	 * @param formater
	 * @return
	 */
    public static String formateTime(long time,String formater){
	       return new SimpleDateFormat(formater).format(new Date(time));
	}
    
    /**
     * 根据日期算星期几
     * @param date
     * @return
     */
    public static int formateWeekNumber(Date date){
    	int[] weeks = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;  
        if(week_index<0){  
            week_index = 0;
        }   
        return weeks[week_index];  
    }
    
    /**
     * 获取下个周几的日期
     * @param week
     * @return
     */
    public static final String getNextDateFromWeek(String week){
		String sdate = "";
		Date today = new Date();
		int intParamterWeek = Integer.valueOf(week);
		int intTodayWeek = formateWeekNumber(today);
		int dif = intParamterWeek - intTodayWeek;
		if(dif < 0){
			dif = 7 + dif;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DAY_OF_MONTH, dif);
		sdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return sdate;
	}
}
