package model;

import java.util.GregorianCalendar;
/**
 *	TODO Possible refactors depending on growing complexity:
 *	-Create CatAttribute wrapper class so we don't have to worry about type conversion
 *	-Use a date formatted string (custom wrapper?) instead of Gregorian Calendar
 *	-Create a map or some custom structures to hold basic information as a standard
 *	 for holding model data. I can foresee this class getting very large.
 */
public class Cat{
	private static final String EMPTY_CAT_ID = "NB-XX-YYY";
	private String name;
	private int age;
	private String gender;
	private String breed;
	private String hairColor;
	private GregorianCalendar arrivalDate;
	private GregorianCalendar departureDate;
	private String id;
	private boolean isFixed;
	
	public Cat(String id, String name, int age, String gender, String breed, String hairColor, boolean isFixed, GregorianCalendar arrivalDate, GregorianCalendar departureDate){
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.breed = breed;
		this.hairColor = hairColor;
		this.isFixed = isFixed;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}
	
	public static Cat emptyCat() {
		return new Cat(EMPTY_CAT_ID, "", -1, "", "", "", false, new GregorianCalendar(0,0,0), new GregorianCalendar(0,0,0));
	}

	//pass in the cat that is under scrutiny
	public static boolean isTheEmptyCat(Cat thisCat){
		return thisCat.getID().equals(EMPTY_CAT_ID);
	}
	
	public int getAge() {
		return this.age;
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

	public GregorianCalendar getArrivalDate() {
		return this.arrivalDate;
	}

	public GregorianCalendar getExpectedDepartureDate() {
		return this.departureDate;
	}

	public String getID() {
		return this.id;
	}

	public boolean isFixed() {
		return this.isFixed;
	}

}
