package test.java.au.com.thilaka.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import main.java.au.com.thilaka.service.RecipeFinderService;
import main.java.au.com.thilaka.service.RecipeFinderServiceImpl;
import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Recipe;
import main.java.au.com.thilaka.vo.Response;
import main.java.au.com.thilaka.vo.Unit;

public class TestRecipeFinderService extends TestCase {

	public void testExpiredItem() {
		try {
			final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Ingredient ingredient = new Ingredient("bread", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2015"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("cheese", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2015"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("25/06/2015"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("peanut butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("02/06/2015"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("mixed salad", 150, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("26/06/2014"));
			ingredientList.add(ingredient);

			final List<Recipe> recipeList = new ArrayList<Recipe>();
			Recipe recipe = new Recipe();
			recipe.setName("grilled cheese on toast");
			List<Ingredient> recipeIngredientList = new ArrayList<Ingredient>();
			ingredient = new Ingredient("bread", 2, Unit.slices);
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

			final RecipeFinderService recipeFinderService = new RecipeFinderServiceImpl();
			final Response jsonResponse = recipeFinderService.getRecommendation(
					ingredientList, recipeList);
			TestCase.assertTrue(jsonResponse.getMessage().equals(
					"grilled cheese on toast"));
		} catch (final Exception e) {
			TestCase.assertFalse(true);
		}
	}

	public void testExpiredItems() {
		try {
			final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Ingredient ingredient = new Ingredient("bread", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("cheese", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("25/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("peanut butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("02/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("mixed salad", 150, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("26/06/2014"));
			ingredientList.add(ingredient);

			final List<Recipe> recipeList = new ArrayList<Recipe>();
			Recipe recipe = new Recipe();
			recipe.setName("grilled cheese on toast");
			List<Ingredient> recipeIngredientList = new ArrayList<Ingredient>();
			ingredient = new Ingredient("bread", 2, Unit.slices);
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

			final RecipeFinderService recipeFinderService = new RecipeFinderServiceImpl();
			final Response jsonResponse = recipeFinderService.getRecommendation(
					ingredientList, recipeList);
			TestCase.assertTrue(jsonResponse.getMessage().equals("Order Takeout"));
		} catch (final Exception e) {
			TestCase.assertFalse(true);
		}
	}

	public void testNoMatch() {
		try {
			final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Ingredient ingredient = new Ingredient("bread", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("cheese", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("25/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("peanut butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("02/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("mixed salad", 150, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("26/06/2015"));
			ingredientList.add(ingredient);

			final List<Recipe> recipeList = new ArrayList<Recipe>();
			Recipe recipe = new Recipe();
			recipe.setName("grilled cheese on toast");
			List<Ingredient> recipeIngredientList = new ArrayList<Ingredient>();
			ingredient = new Ingredient("bread1", 2, Unit.slices);
			recipeIngredientList.add(ingredient);
			ingredient = new Ingredient("cheese1", 2, Unit.slices);
			recipeIngredientList.add(ingredient);
			recipe.setIngredients(recipeIngredientList);
			recipeList.add(recipe);

			recipe = new Recipe();
			recipe.setName("salad sandwich");
			recipeIngredientList = new ArrayList<Ingredient>();
			ingredient = new Ingredient("bread1", 2, Unit.slices);
			recipeIngredientList.add(ingredient);
			ingredient = new Ingredient("mixed salad1", 100, Unit.grams);
			recipeIngredientList.add(ingredient);
			recipe.setIngredients(recipeIngredientList);
			recipeList.add(recipe);

			final RecipeFinderService recipeFinderService = new RecipeFinderServiceImpl();
			final Response jsonResponse = recipeFinderService.getRecommendation(
					ingredientList, recipeList);
			TestCase.assertTrue(jsonResponse.getMessage().equals("Order Takeout"));
		} catch (final Exception e) {
			TestCase.assertFalse(true);
		}
	}

	public void testNormalScenario() {
		try {
			final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Ingredient ingredient = new Ingredient("bread", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("cheese", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("25/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("peanut butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("02/06/2016"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("mixed salad", 150, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("26/06/2015"));
			ingredientList.add(ingredient);

			final List<Recipe> recipeList = new ArrayList<Recipe>();
			Recipe recipe = new Recipe();
			recipe.setName("grilled cheese on toast");
			List<Ingredient> recipeIngredientList = new ArrayList<Ingredient>();
			ingredient = new Ingredient("bread", 2, Unit.slices);
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

			final RecipeFinderService recipeFinderService = new RecipeFinderServiceImpl();
			final Response jsonResponse = recipeFinderService.getRecommendation(
					ingredientList, recipeList);
			TestCase.assertTrue(jsonResponse.getMessage().equals("salad sandwich"));
		} catch (final Exception e) {
			TestCase.assertFalse(true);
		}
	}

	public void testRecipeWithNoIngredient() {
		try {
			final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Ingredient ingredient = new Ingredient("bread", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("cheese", 10, Unit.slices);
			ingredient.setUseBy(dateFormat.parse("25/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("25/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("peanut butter", 250, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("02/06/2014"));
			ingredientList.add(ingredient);

			ingredient = new Ingredient("mixed salad", 150, Unit.grams);
			ingredient.setUseBy(dateFormat.parse("26/06/2014"));
			ingredientList.add(ingredient);

			final List<Recipe> recipeList = new ArrayList<Recipe>();
			Recipe recipe = new Recipe();
			recipe.setName("grilled cheese on toast");
			recipeList.add(recipe);

			recipe = new Recipe();
			recipe.setName("salad sandwich");
			recipeList.add(recipe);

			final RecipeFinderService recipeFinderService = new RecipeFinderServiceImpl();
			final Response jsonResponse = recipeFinderService.getRecommendation(
					ingredientList, recipeList);
			TestCase.assertTrue(jsonResponse.getMessage().equals(
					"grilled cheese on toast"));
		} catch (final Exception e) {
			TestCase.assertFalse(true);
		}
	}
	/*
	 * String fridgeStr =
	 * "bread,10,slices,25/12/2015"+System.getProperty("line.separator")+
	 * "cheese,10,slices,25/12/2015"+System.getProperty("line.separator")+
	 * "butter,250,grams,25/12/2015"+System.getProperty("line.separator")+
	 * "peanut butter,250,grams,2/12/2015"+System.getProperty("line.separator")+
	 * "mixed salad,150,grams,26/12/2015"; String recipeJson =
	 * "[{\"name\": \"grilled cheese on toast\", \"ingredients\": [{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}, { \"item\":\"cheese\", \"amount\":\"2\", \"unit\":\"slices\"}]},{\"name\": \"salad sandwich\", \"ingredients\": [{ \"item\":\"bread\", \"amount\":\"2\", \"unit\":\"slices\"}, { \"item\":\"mixed salad\", \"amount\":\"100\", \"unit\":\"grams\"}]}]"
	 * ;
	 *
	 * List<String> ingredientStrList = StringUtil.getStringList(fridgeStr);
	 * List<Ingredient> ingredientList =
	 * RecipeUtil.getIngredientListFromCSVString(ingredientStrList);
	 *
	 * List<Recipe> recipeList = GsonUtil.getRecipeList(recipeJson);
	 */
}
