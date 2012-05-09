package org.scoutant.tvcenter.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss Z");
	
	/**
	 * start="20100110175500 +0100"
	 * @param date with format : 20100110175500 +0100
	 * @return corresponding time, not in milliseconds but in minutes, origin at 1970/01/01
	 */
	public static int parse(String s) throws ParseException {
		return (int) (df.parse(s).getTime()/1000/60);
	}
	
	
	private static final DateFormat df2 = new SimpleDateFormat("MM/dd-HH:mm");
	public static String format(long l) {
		return df2.format( new Date(l*60*1000));
	}
	private static final DateFormat dfHour = new SimpleDateFormat("HH:mm");
	public static String formatAsHour(long l) {
		return dfHour.format( new Date(l*60*1000));
	}
}
