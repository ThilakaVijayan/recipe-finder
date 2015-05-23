package main.java.au.com.thilaka.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.java.au.com.thilaka.constant.ApplicationConstants;
import main.java.au.com.thilaka.exception.InputException;
import main.java.au.com.thilaka.vo.Ingredient;
import main.java.au.com.thilaka.vo.Unit;

public class RecipeUtil {
	public static Ingredient getIngredientFromCSVString(final String csvString) throws InputException{
		final String[] strArr = RecipeUtil.splitCSVString(csvString);
		RecipeUtil.validateCSVString(strArr);

		final String item = strArr[0];
		final String amountStr = strArr[1];
		final int amount = Integer.valueOf(amountStr);
		final String unitStr = strArr[2];
		final Unit unit = Unit.valueOf(unitStr);
		final String useByStr = strArr[3];
		final Date useBy = DateUtil.convertToDate(useByStr);
		return new Ingredient(item,amount,unit,useBy);
	}

	public static List<Ingredient> getIngredientListFromCSVString(final List<String> csvStringList) throws InputException{
		final List<Ingredient> ingredientList = new ArrayList<Ingredient>();
		for(final String csvString : csvStringList){
			ingredientList.add(RecipeUtil.getIngredientFromCSVString(csvString));
		}
		return ingredientList;
	}
	private static String[] splitCSVString(final String csvString) throws InputException{
		final String[] strArr = csvString.split(ApplicationConstants.COMMA);
		if((strArr == null) || (strArr.length<4)){
			throw new InputException(csvString +"-This fridge CSV string is not of right format (bread,10,slices,25/12/2014)");
		}
		return strArr;
	}

	private static void validateCSVString(final String[] strArr) throws InputException{
		if(!StringUtil.isNumeric(strArr[1])){
			throw new InputException(strArr[1] +"-Amount field is not a number");
		}
		if(!StringUtil.unitContains(strArr[2])){
			throw new InputException(strArr[2] +"-Unit field is not valid (of,grams,ml,slices)");
		}
	}
}
