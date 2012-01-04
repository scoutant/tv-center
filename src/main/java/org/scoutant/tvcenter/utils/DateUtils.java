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
	public static long parse(String s) throws ParseException {
		Date date = df.parse(s);
		return date.getTime()/1000/60;
	}
}
