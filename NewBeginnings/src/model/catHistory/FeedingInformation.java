package model.catHistory;

/**
 * FeedingInformation is a container for the feeding information of the cat. It
 * only interacts with the CatHistory object.
 */

public class FeedingInformation {
	private String foodType;
	private String brands;
	private String specialDiet;
	private String eatingFrequency;

	public FeedingInformation(String foodType, String brands,
			String specialDiet, String eatingFrequency) {
		this.foodType = foodType;
		this.brands = brands;
		this.specialDiet = specialDiet;
		this.eatingFrequency = eatingFrequency;
	}

	public void updateFoodType(String foodType) {
		this.foodType = foodType;
	}

	public void updateBrands(String brands) {
		this.brands = brands;
	}

	public void updateSpecialDiet(String specialDiet) {
		this.specialDiet = specialDiet;
	}

	public void updateEatingFrequency(String eatingFrequency) {
		this.eatingFrequency = eatingFrequency;
	}

	public String getFoodType() {
		return this.foodType;
	}

	public String getBrands() {
		return this.brands;
	}

	public String getSpecialDiet() {
		return this.specialDiet;
	}

	public String getEatingFrequency() {
		return this.eatingFrequency;
	}
}
