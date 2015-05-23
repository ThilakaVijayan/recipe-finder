package main.java.au.com.thilaka.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.au.com.thilaka.constant.ApplicationConstants;
import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Recipe;
import main.java.au.com.thilaka.vo.Response;

public class RecipeFinderServiceImpl implements RecipeFinderService {
	@Override
	public Response getRecommendation(final List<Ingredient> availableItemList,
			final List<Recipe> recipeList) {
		final Response response = new Response();
		final List<Recipe> possibleRecipe = new ArrayList<Recipe>();
		for (final Recipe recipe : recipeList) {
			final List<Ingredient> recipeIngredientList = recipe.getIngredients();
			if(null != recipeIngredientList){
				for (final Ingredient recipeIngredient : recipeIngredientList) {
					for (final Ingredient availableItem : availableItemList) {
						if (recipeIngredient.matches(availableItem)) {
							recipeIngredient.setFound(true);
							break;
						}
					}
				}
			}
			if (recipe.isAllIngredientsAvailable()) {
				possibleRecipe.add(recipe);
			}
		}
		if (!possibleRecipe.isEmpty()) {
			Collections.sort(possibleRecipe);
			response.setMessage(possibleRecipe.get(0).getName());
		} else {
			response.setMessage(ApplicationConstants.ORDER_TAKEOUT);
		}
		response.setSuccess(true);
		return response;
	}
}
