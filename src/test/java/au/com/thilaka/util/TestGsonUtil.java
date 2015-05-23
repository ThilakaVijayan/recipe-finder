package test.java.au.com.thilaka.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.au.com.thilaka.exception.InputException;
import main.java.au.com.thilaka.util.GsonUtil;
import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Recipe;
import main.java.au.com.thilaka.vo.Unit;

public class TestGsonUtil extends TestCase {
	public void testConvertToJson() {
		final List<Recipe> recipeList = new ArrayList<Recipe>();
		Recipe recipe = new Recipe();
		recipe.setName("grilled cheese on toast");
		List<Ingredient> recipeIngredientList = new ArrayList<Ingredient>();
		Ingredient ingredient = new Ingredient("bread", 2, Unit.slices);
		recipeIngredientList.add(ingredient);
		ingredient = new Ingredient("cheese", 2, Unit.slices);
		recipeIngredientList.add(ingredient);
		recipe.setIngredients(recipeIngredientList);
		recipeList.add(recipe);

		recipe = new Recipe();
		recipe.setName("salad sandwich");
		recipeIngredientList = new ArrayList<Ingredient>();
		ingredient = new Ingredient("bread", 2, Unit.slices);
		recipeIngredientList.add(ingredient);
		ingredient = new Ingredient("mixed salad", 100, Unit.grams);
		recipeIngredientList.add(ingredient);
		recipe.setIngredients(recipeIngredientList);
		recipeList.add(recipe);
		TestCase.assertTrue("[{\"ingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":2,\"found\":false,\"item\":\"cheese\",\"unit\":\"slices\"}],\"name\":\"grilled cheese on toast\"},{\"ingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":100,\"found\":false,\"item\":\"mixed salad\",\"unit\":\"grams\"}],\"name\":\"salad sandwich\"}]"
				.equals(GsonUtil.convertToJson(recipeList)));
	}
	public void testGetRecipeList(){
		List<Recipe> recipeList;
		try {
			recipeList = GsonUtil.getRecipeList("[{\"ingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":2,\"found\":false,\"item\":\"cheese\",\"unit\":\"slices\"}],\"name\":\"grilled cheese on toast\"},{\"ingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":100,\"found\":false,\"item\":\"mixed salad\",\"unit\":\"grams\"}],\"name\":\"salad sandwich\"}]");
			TestCase.assertTrue(recipeList.size()==2);
			TestCase.assertTrue("grilled cheese on toast".equals(recipeList.get(0).getName()));
			TestCase.assertTrue(2 == recipeList.get(0).getIngredients().get(0).getAmount());
			TestCase.assertTrue(Unit.slices == recipeList.get(0).getIngredients().get(0).getUnit());
		} catch (final InputException e) {
			TestCase.assertFalse(true);
		}
	}

	public void testGetRecipeListException(){
		try {
			GsonUtil.getRecipeList("Happy birthday");
			TestCase.assertFalse(true);
		} catch (final InputException e) {
			TestCase.assertTrue(true);
		}
	}
	public void testGetRecipeListFormatException(){
		try {
			GsonUtil.getRecipeList("[{\"ingredients\":[{\"amount\":XYZ,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":2,\"found\":false,\"item\":\"cheese\",\"unit\":\"slices\"}],\"name\":\"grilled cheese on toast\"},{\"ingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":100,\"found\":false,\"item\":\"mixed salad\",\"unit\":\"grams\"}],\"name\":\"salad sandwich\"}]");
			TestCase.assertFalse(true);
		} catch (final InputException e) {
			TestCase.assertTrue(true);
		}
	}
	public void testGetRecipeListMissFormed(){
		try {
			final List<Recipe> recipeList= GsonUtil.getRecipeList("[{\"Xingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":2,\"found\":false,\"item\":\"cheese\",\"unit\":\"slices\"}],\"name\":\"grilled cheese on toast\"},{\"ingredients\":[{\"amount\":2,\"found\":false,\"item\":\"bread\",\"unit\":\"slices\"},{\"amount\":100,\"found\":false,\"item\":\"mixed salad\",\"unit\":\"grams\"}],\"name\":\"salad sandwich\"}]");
			TestCase.assertTrue(recipeList.size()==2);
			TestCase.assertTrue("grilled cheese on toast".equals(recipeList.get(0).getName()));
			TestCase.assertTrue(null == recipeList.get(0).getIngredients());
		} catch (final InputException e) {
			TestCase.assertFalse(true);
		}
	}
}
