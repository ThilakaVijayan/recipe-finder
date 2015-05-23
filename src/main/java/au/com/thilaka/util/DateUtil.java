package main.java.au.com.thilaka.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.java.au.com.thilaka.exception.InputException;

public class DateUtil {
	public static Date convertToDate(final String dateStr) throws InputException{
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dateFormat.parse(dateStr);
		} catch (final ParseException e) {
			throw new InputException(dateStr + "-Date is not matching dd/MM/yyyy format.");
		}
	}
	public static boolean isExpired(final Date useBy){
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = null;
		try {
			today = dateFormat.parse(dateFormat.format(new Date()));
		} catch (final ParseException e) {
			//this will never occur
		}
		if (useBy.before(today)) {
			return true;
		}
		return false;
	}
}
