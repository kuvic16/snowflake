package com.bracits.snowflake.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
public class AppDate extends GregorianCalendar implements Comparable<Calendar> {

	public AppDate() {
		setTime(new Date());
	}

	public AppDate(int month, int day) {
		set(MONTH, month - 1);
		set(DAY_OF_MONTH, day);
	}

	public static AppDate now() {
		return new AppDate();
	}

	public static AppDate now(TimeZone zone) {
		AppDate now = new AppDate();
		now.setTimeZone(zone);
		return now;
	}
}