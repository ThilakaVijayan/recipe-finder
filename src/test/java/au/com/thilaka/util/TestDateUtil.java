package test.java.au.com.thilaka.util;

import java.util.Date;

import junit.framework.TestCase;
import main.java.au.com.thilaka.exception.InputException;
import main.java.au.com.thilaka.util.DateUtil;

public class TestDateUtil extends TestCase {
	public void testConvertToDate() {
		try {
			final Date date = DateUtil.convertToDate("25/12/2015");
			TestCase.assertTrue(date.toString().equals("Fri Dec 25 00:00:00 AEDT 2015"));
		} catch (final InputException e) {
			TestCase.assertFalse(true);
		}
	}

	public void testConvertToDateException() {
		try {
			DateUtil.convertToDate("12/25/2015");
		} catch (final InputException e) {
			TestCase.assertTrue(e.getMessage().equals(
					"12/25/2015-Date is not matching dd/MM/yyyy format."));
		}
	}

	public void testIsExpired() {
		try {
			final Date date = DateUtil.convertToDate("23/05/2015");
			TestCase.assertTrue(DateUtil.isExpired(date));
		} catch (final InputException e) {
			TestCase.assertFalse(true);
		}
	}

	public void testIsExpiredToday() {
		final Date date = new Date();
		TestCase.assertTrue(!DateUtil.isExpired(date));
	}
}
