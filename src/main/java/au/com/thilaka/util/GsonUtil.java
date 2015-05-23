package main.java.au.com.thilaka.util;

import java.lang.reflect.Type;
import java.util.List;

import main.java.au.com.thilaka.exception.InputException;
import main.java.au.com.thilaka.vo.Recipe;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	public static String convertToJson(final Object obj) {
		final Gson gson = new Gson();
		return gson.toJson(obj);
	}

	public static List<Recipe> getRecipeList(final String jsonString)
			throws InputException {
		try {
			final Gson gson = new Gson();
			final Type type = new TypeToken<List<Recipe>>() {
			}.getType();
			return gson.fromJson(jsonString, type);
		} catch (final JsonParseException e) {
			throw new InputException("Recipe JSON string parsing exception-"
					+ e.getMessage());
		}
	}
}
