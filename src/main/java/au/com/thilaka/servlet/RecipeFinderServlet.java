package main.java.au.com.thilaka.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.au.com.thilaka.exception.InputException;
import main.java.au.com.thilaka.service.RecipeFinderService;
import main.java.au.com.thilaka.service.RecipeFinderServiceImpl;
import main.java.au.com.thilaka.util.GsonUtil;
import main.java.au.com.thilaka.util.RecipeUtil;
import main.java.au.com.thilaka.util.StringUtil;
import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Recipe;
import main.java.au.com.thilaka.vo.Response;

/**
 * Servlet implementation class RecipeFinderServlet
 */
public class RecipeFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public RecipeFinderServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final PrintWriter out = response.getWriter();
		response.setContentType("json");
		try {
			final String fridgeStr = request.getParameter("fridgeList");
			final String recipeJson = request.getParameter("recipeJson");
			if ((null == fridgeStr) || fridgeStr.isEmpty()
					|| (null == recipeJson) || recipeJson.isEmpty()) {
				throw new InputException("Please enter both the input fields");
			}
			final List<String> ingredientStrList = StringUtil
					.getStringList(fridgeStr);
			final List<Ingredient> ingredientList = RecipeUtil
					.getIngredientListFromCSVString(ingredientStrList);
			final List<Recipe> recipeList = GsonUtil.getRecipeList(recipeJson);
			final RecipeFinderService recipeFinderService = new RecipeFinderServiceImpl();
			final Response jsonResponse = recipeFinderService
					.getRecommendation(ingredientList, recipeList);

			out.println(GsonUtil.convertToJson(jsonResponse));
			out.close();
		} catch (final InputException e) {
			final Response jsonResponse = new Response();
			jsonResponse.setSuccess(false);
			jsonResponse.setErrorMessage(e.getMessage());
			out.println(GsonUtil.convertToJson(jsonResponse));
			out.close();
		}
	}
}
