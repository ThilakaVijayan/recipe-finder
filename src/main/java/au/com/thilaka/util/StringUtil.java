package main.java.au.com.thilaka.util;

import java.util.Arrays;
import java.util.List;

import main.java.au.com.thilaka.vo.Unit;

public class StringUtil {

	public static List<String> getStringList(final String inputString) {
		final String newLine = System.getProperty("line.separator");
		return Arrays.asList(inputString.split(newLine));
	}

	public static boolean isNumeric(final String inputString) {
		for (final char c : inputString.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static boolean unitContains(final String inputString) {
		for (final Unit unit : Unit.values()) {
			if (unit.name().equals(inputString)) {
				return true;
			}
		}
		return false;
	}
}
