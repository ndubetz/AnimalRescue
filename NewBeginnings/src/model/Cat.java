package model;

import java.util.GregorianCalendar;

public class Cat{
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
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.breed = breed;
		this.hairColor = hairColor;
		this.id = id;
		this.isFixed = isFixed;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
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
