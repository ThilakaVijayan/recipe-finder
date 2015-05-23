package test.java.au.com.thilaka.util;

import java.util.List;

import junit.framework.TestCase;
import main.java.au.com.thilaka.util.StringUtil;

public class TestStringUtil extends TestCase {
	public void testGetStringList(){
		final String fridgeStr ="bread,10,slices,25/12/2015"+System.getProperty("line.separator")+"cheese,10,slices,25/12/2015"+System.getProperty("line.separator")+
				"butter,250,grams,25/12/2015"+System.getProperty("line.separator")+
				"peanut butter,250,grams,2/12/2015"+System.getProperty("line.separator")+
				"mixed salad,150,grams,26/12/2015";
		final List<String> strList = StringUtil.getStringList(fridgeStr);
		TestCase.assertTrue(5 == strList.size());
	}
	public void testIsNumericFalse(){
		final String s = "a";
		TestCase.assertFalse(StringUtil.isNumeric(s));
	}
	public void testIsNumericTrue(){
		final String s = "2";
		TestCase.assertTrue(StringUtil.isNumeric(s));
	}
	public void testUnitContainsFalse(){
		final String s = "xyz";
		TestCase.assertFalse(StringUtil.unitContains(s));
	}
	public void testUnitContainsTrue(){
		final String s = "grams";
		TestCase.assertTrue(StringUtil.unitContains(s));
	}
}
