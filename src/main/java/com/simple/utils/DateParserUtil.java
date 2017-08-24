package com.simple.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * 时间解析工具类
 * @author renweidong
 *
 */
public class DateParserUtil {
	
	
	/**
	 * 获取当前的日期
	 * @return 当前日期（String）
	 */
	public static String getToday() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateStr = dateFormat.format(calendar.getTime());
		return dateStr;
	}
	
	/**
	 * 获取前一天的日期
	 * @return 昨天日期（String）
	 */
	public static String getYestoday() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
		String dateStr = dateFormat.format(calendar.getTime());
		return dateStr;
	}
	
	/**
	 * 计算前N天的日期
	 * @return 结果日期（String）
	 */
	public static String getNDaysAgo(String date,int nDaysAgo) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-nDaysAgo);
		String dateStr = dateFormat.format(calendar.getTime());
		return dateStr;
	}
}
