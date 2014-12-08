package model;

import java.util.Calendar;

/**
 * Cat is a model object responsible for holding all the data belonging to a
 * cat.
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
	private String id;
	private final String isFixed;
	private final String rabies;
	private final String feLeuk;
	private final String distemper;
	private final String[] medicalHistory;
	private String catPictureFilePath;
	private String commentsAboutCat;

	public Cat(String id, String name, Calendar birthdate, String gender,
			String breed, String hairColor, String isFixed,
			Calendar arrivalDate, Calendar departureDate, String rabies,
			String feLeuk, String distemper, String[] medicalHistory,
			String catPictureFilePath, String commentsAboutCat) {

		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
		this.gender = gender;
		this.breed = breed;
		this.hairColor = hairColor;
		this.isFixed = isFixed;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.rabies = rabies;
		this.feLeuk = feLeuk;
		this.distemper = distemper;
		this.medicalHistory = medicalHistory;
		this.catPictureFilePath = catPictureFilePath;
		this.commentsAboutCat = commentsAboutCat;
	}

	public static Cat emptyCat() {
		return new Cat(EMPTY_CAT_ID, "", Calendar.getInstance(), "", "", "",
				"", Calendar.getInstance(), Calendar.getInstance(), "", "", "",

				new String[] { "" }, "", "Comments");

	}

	// pass in the cat that is under scrutiny
	public static boolean isTheEmptyCat(Cat thisCat) {
		return thisCat.getID().equals(EMPTY_CAT_ID);
	}

	public void setBirthdate() {
	}

	public Calendar getBirthdate() {
		return this.birthdate;
	}

	public String getBirthdateAsDateFormattedString() {
		return getCalendarAsDateFormattedString(this.birthdate);
	}

	public String getArrivalDateAsDateFormattedString() {
		return getCalendarAsDateFormattedString(this.arrivalDate);
	}

	public String getDepartureDateAsDateFormattedString() {
		if (this.departureDate != null) {
			return getCalendarAsDateFormattedString(this.departureDate);
		} else {
			return "";
		}
	}

	private String getCalendarAsDateFormattedString(Calendar calendar) {
		String dayOfMonth = Integer.toString(calendar
				.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		String year = Integer.toString(calendar.get(Calendar.YEAR));

		if (dayOfMonth.length() == 1) {
			dayOfMonth = "0" + dayOfMonth;
		}
		if (month.length() == 1) {
			month = "0" + month;
		}

		String formattedDate = month + "/" + dayOfMonth + "/" + year;

		return formattedDate;
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

	public void setID(String newID) {
		this.id = newID;
	}

	public String getFixed() {
		return this.isFixed;
	}

	public String getRabies() {
		return this.rabies;
	}

	public String getFeLeuk() {
		return this.feLeuk;
	}

	public String getDistemper() {
		return this.distemper;
	}

	public String[] getMedicalHistory() {
		return this.medicalHistory;
	}

	public String getCatPictureFilePath() {
		return this.catPictureFilePath;
	}

	public String getCommentsAboutCat() {
		return this.commentsAboutCat;
	}

	public String setCommentsAboutCat(String comments) {
		return this.commentsAboutCat = comments;
	}

	public String getAge(Calendar birthdate) {
		String age = "%d Year%s, %d Month%s";

		long difference = Calendar.getInstance().getTimeInMillis()
				- birthdate.getTimeInMillis();

		long msPerYear = 31557600000l;
		long msPerMonth = 2557440000l;
		long years = difference / msPerYear;
		long months = (difference % msPerYear) / msPerMonth;

		return String.format(age, years, (years == 1l) ? "" : "s", months,
				(months == 1l) ? "" : "s");
	}

	public void setCatPictureFilePath(String string) {
		this.catPictureFilePath = string;
	}
}
