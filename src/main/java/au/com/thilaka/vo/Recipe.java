package main.java.au.com.thilaka.vo;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Recipe implements Comparable<Recipe> {
	private List<Ingredient> ingredients;
	private String name;

	@Override
	public int compareTo(final Recipe o) {
		return this.getLeastUseBy().compareTo(o.getLeastUseBy());
	}

	public List<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public Date getLeastUseBy() {
		if ((null != this.ingredients) && !this.ingredients.isEmpty()) {
			Collections.sort(this.ingredients);
			return this.ingredients.get(0).getUseBy();
		} else {
			return new Date();
		}
	}

	public String getName() {
		return this.name;
	}

	public boolean isAllIngredientsAvailable() {
		if (this.ingredients != null) {
			for (final Ingredient ingredient : this.ingredients) {
				if (!ingredient.isFound()) {
					return false;
				}
			}
		}
		return true;
	}

	public void setIngredients(final List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Recipe [name=" + this.name + ", ingredients="
				+ this.ingredients + ", leastUseBy=" + this.getLeastUseBy()
				+ ", isAllIngredientsAvailable="
				+ this.isAllIngredientsAvailable() + "]";
	}

}
