package main.java.au.com.thilaka.vo;

import java.util.Date;

import main.java.au.com.thilaka.util.DateUtil;

public class Ingredient implements Comparable<Ingredient> {
	private int amount;
	private boolean found;
	private String item;
	private Unit unit;
	private Date useBy;

	private Ingredient() {
	}

	public Ingredient(final String item, final int amount, final Unit unit) {
		super();
		this.item = item;
		this.amount = amount;
		this.unit = unit;
	}

	public Ingredient(final String item, final int amount, final Unit unit,
			final Date useBy) {
		super();
		this.item = item;
		this.amount = amount;
		this.unit = unit;
		this.useBy = useBy;
	}

	@Override
	public int compareTo(final Ingredient o) {
		if((null != this.useBy) && (null != o.getUseBy())){
			return this.useBy.compareTo(o.getUseBy());
		}
		return 0;
	}

	public int getAmount() {
		return this.amount;
	}

	public String getItem() {
		return this.item;
	}

	public Unit getUnit() {
		return this.unit;
	}

	public Date getUseBy() {
		return this.useBy;
	}

	public boolean isFound() {
		return this.found;
	}

	public boolean matches(final Ingredient availableItem) {
		if ((null != availableItem)
				&& availableItem.getItem().equalsIgnoreCase(this.item)
				&& (this.amount <= availableItem.amount)
				&& (this.unit == availableItem.unit)
				&& !DateUtil.isExpired(availableItem.useBy)) {
			this.useBy = availableItem.useBy;
			return true;
		}
		return false;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}

	public void setFound(final boolean found) {
		this.found = found;
	}

	public void setItem(final String item) {
		this.item = item;
	}

	public void setUnit(final Unit unit) {
		this.unit = unit;
	}

	public void setUseBy(final Date useBy) {
		this.useBy = useBy;
	}

	@Override
	public String toString() {
		return "Ingredient [item=" + this.item + ", amount=" + this.amount
				+ ", unit=" + this.unit + ", useBy=" + this.useBy + ", found="
				+ this.found + "]";
	}

}
