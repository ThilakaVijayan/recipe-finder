package test.java.au.com.thilaka;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.java.au.com.thilaka.service.TestRecipeFinderService;
import test.java.au.com.thilaka.util.TestDateUtil;
import test.java.au.com.thilaka.util.TestGsonUtil;
import test.java.au.com.thilaka.util.TestRecipeUtil;
import test.java.au.com.thilaka.util.TestStringUtil;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestRecipeFinderService.class,
	TestDateUtil.class,
	TestGsonUtil.class,
	TestRecipeUtil.class,
	TestStringUtil.class

})
public class TestSuite {
}
