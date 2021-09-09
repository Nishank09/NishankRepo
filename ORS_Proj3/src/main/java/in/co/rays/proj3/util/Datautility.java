package in.co.rays.proj3.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


/**
 * The Class DataUtility.
 * @author Iterator
 * @version 1.0
 */
public class Datautility {

	/**
	 * Application Date Format
	 */

	public static final String APP_DATE_FORMAT = "MM-dd-yyyy";
	
	/** The Constant APP_TIME_FORMAT. */
	public static final String APP_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";

	/**
	 * Date formatter
	 */

	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);

	/** The Constant timeFormat. */
	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

	
	/**
	 * Trims and trailing and leading spaces of a String.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String getstring(String val) {
		if (Datavalidator.isnotnull(val)) {
			val.trim();
			return val;
		} else {
			return "";
		}
	}

	/**
	 * Converts Object into String.
	 *
	 * @param val the val
	 * @return the string data
	 * @return: string
	 */
	public static String getstringdata(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	/**
	 * Converts String into Integer
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String val) {
		if (Datavalidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	public static int getIntvalue(int val) {
		if (val != 0) {
			return val;
		} else {
			return 0;
		}
	}

	/**
	 * Converts String into Long
	 * 
	 * @param val
	 * @return
	 */
	public static long getLong(String val) {
		if (Datavalidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}

	/**
	 * Converts String into Time.
	 *
	 * @param val the val
	 * @return the timestamp
	 * @return: timestamp
	 */
	public static Timestamp gettimestamp(String val) {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(timeFormatter.parse(val).getTime());
		} catch (Exception e) {
			return null;
		}
		return timestamp;
	}

	/**
	 * Converts long value to timestamp.
	 *
	 * @param l the l
	 * @return the timestamp
	 */
	public static Timestamp gettimestamp(Long l) {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timestamp;

	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @param tm the tm
	 * @return the timestamp
	 */
	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * return the current timestamp.
	 *
	 * @return the current timestamp
	 */
	public static Timestamp currenttimestamp() {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
			return null;
		}
		return timestamp;
	}

	/**
	 * Converts String into Date.
	 *
	 * @param val the val
	 * @return the date
	 */
	public static Date getdate(String val) {
		System.out.println(",,,,,,,,,,,,,,,,,,,,,,,"+val);
		Date date = null;
		try {
			date = formatter.parse(val);
			System.out.println("date at data utility is =" + date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;

	}

	/**
	 * Converts Date into String.
	 *
	 * @param date
	 *            the date
	 * @return the date string
	 * @return: string
	 */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}
}
