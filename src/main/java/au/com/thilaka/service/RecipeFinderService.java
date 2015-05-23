package main.java.au.com.thilaka.service;

import java.util.List;

import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Recipe;
import main.java.au.com.thilaka.vo.Response;

public interface RecipeFinderService {

	public Response getRecommendation(List<Ingredient> ingredientList,
			List<Recipe> recipeList);

}
