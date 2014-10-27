package model;

import java.util.Calendar;

/**
 * TODO Possible refactors depending on growing complexity: -Create CatAttribute
 * wrapper class so we don't have to worry about type conversion -Use a date
 * formatted string (custom wrapper?) instead of/in addition to Calendar -Create
 * a map or some custom structures to hold basic information as a standard for
 * holding model data. I can foresee this class getting very large.
 */
public class Cat {
	private static final String EMPTY_CAT_ID = "NB-00-000";
	private final String name;
	private final Calendar birthdate;
	private final String gender;
	private final String breed;
	private final String hairColor;
	private final Calendar arrivalDate;
	private final Calendar departureDate;
	private final String id;
	private final boolean isFixed;

	public Cat(String id, String name, Calendar birthdate, String gender,
			String breed, String hairColor, boolean isFixed,
			Calendar arrivalDate, Calendar departureDate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
		this.breed = breed;
		this.hairColor = hairColor;
		this.isFixed = isFixed;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	public static Cat emptyCat() {
		return new Cat(EMPTY_CAT_ID, "", Calendar.getInstance(), "", "", "",
				false, Calendar.getInstance(), Calendar.getInstance());
	}

	// pass in the cat that is under scrutiny
	public static boolean isTheEmptyCat(Cat thisCat) {
		return thisCat.getID().equals(EMPTY_CAT_ID);
	}

	public Calendar getBirthdate() {
		return this.birthdate;
	}

	public String getName() {
		return this.name;
	}

	public String getGender() {
		return this.gender;
	}

	public String getBreed() {
		return this.breed;
	}

	public String getHairColor() {
		return this.hairColor;
	}

	public Calendar getArrivalDate() {
		return this.arrivalDate;
	}

	public Calendar getExpectedDepartureDate() {
		return this.departureDate;
	}

	public String getID() {
		return this.id;
	}

	public boolean isFixed() {
		return this.isFixed;
	}

	public String getAge() {
		String age = "%d Year%s, %d Month%s";

		long difference = Calendar.getInstance().getTimeInMillis()
				- this.birthdate.getTimeInMillis();

		long msPerYear = 31557600000l;
		long msPerMonth = 2557440000l;
		long years = difference / msPerYear;
		long months = (difference % msPerYear) / msPerMonth;

		return String.format(age, years, (years == 1l) ? "" : "s", months,
				(months == 1l) ? "" : "s");
	}
}
