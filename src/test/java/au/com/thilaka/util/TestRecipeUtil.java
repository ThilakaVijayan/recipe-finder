package test.java.au.com.thilaka.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.au.com.thilaka.exception.InputException;
import main.java.au.com.thilaka.util.RecipeUtil;
import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Unit;

public class TestRecipeUtil extends TestCase{
	public void testGetIngredientFromCSVString(){
		try {
			final Ingredient ingredient =  RecipeUtil.getIngredientFromCSVString("bread,10,slices,25/12/2014");
			TestCase.assertTrue("bread".equals(ingredient.getItem()));
			TestCase.assertTrue(10 == ingredient.getAmount());
			TestCase.assertTrue(Unit.slices == ingredient.getUnit());
			TestCase.assertTrue(ingredient.getUseBy().toString().equals("Thu Dec 25 00:00:00 AEDT 2014"));

		} catch (final InputException e) {
			TestCase.assertFalse(true);
		}
	}
	public void testGetIngredientFromCSVStringAmount(){
		try {
			RecipeUtil.getIngredientFromCSVString(",abc,xyz,sdf");
			TestCase.assertFalse(true);
		} catch (final InputException e) {
			TestCase.assertTrue("abc-Amount field is not a number".equals(e.getMessage()));
		}
	}
	public void testGetIngredientFromCSVStringEmpty(){
		try {
			RecipeUtil.getIngredientFromCSVString("");
			TestCase.assertFalse(true);
		} catch (final InputException e) {
			TestCase.assertTrue("-This fridge CSV string is not of right format (bread,10,slices,25/12/2014)".equals(e.getMessage()));
		}
	}
	public void testGetIngredientFromCSVStringUnit(){
		try {
			RecipeUtil.getIngredientFromCSVString(",10,xyz,sdf");
			TestCase.assertFalse(true);
		} catch (final InputException e) {
			TestCase.assertTrue("xyz-Unit field is not valid (of,grams,ml,slices)".equals(e.getMessage()));
		}
	}
	public void testGetIngredientListFromCSVString(){
		final List<String> csvStringList =new ArrayList<String>();
		csvStringList.add("bread,10,slices,25/12/2014");
		csvStringList.add("cheese,0,slices,25/12/2014");
		try {
			final List<Ingredient> ingredientList = RecipeUtil.getIngredientListFromCSVString(csvStringList);
			TestCase.assertTrue(2 == ingredientList.size());
			TestCase.assertTrue(0 == ingredientList.get(1).getAmount());
		} catch (final InputException e) {
			TestCase.assertFalse(true);
		}
	}
}
