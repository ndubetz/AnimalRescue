package model.catHistory;

import java.util.LinkedList;

/**
 * FeedingInformation is a container for the feeding information of the cat. It
 * is package protected and only interacts with the CatHistory object.
 */

class FeedingInformation {
	private final String foodType;
	private final LinkedList<String> brands;
	private final LinkedList<String> specialDiet;
	private final String eatingFrequency;

	protected FeedingInformation(String foodType, LinkedList<String> brands,
			LinkedList<String> specialDiet, String eatingFrequency) {
		this.foodType = foodType;
		this.brands = brands;
		this.specialDiet = specialDiet;
		this.eatingFrequency = eatingFrequency;
	}

	protected String getFoodType() {
		return this.foodType;
	}

	protected LinkedList<String> getBrands() {
		return this.brands;
	}

	protected LinkedList<String> getSpecialDiet() {
		return this.specialDiet;
	}

	protected String getEatingFrequency() {
		return this.eatingFrequency;
	}
}
