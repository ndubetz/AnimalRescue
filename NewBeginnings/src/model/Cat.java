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
	
	public Cat(String name, int age, String gender, String breed, String hairColor, GregorianCalendar arrivalDate, GregorianCalendar departureDate){
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.breed = breed;
		this.hairColor = hairColor;
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
		System.out.println(this.arrivalDate.getTime());
		return this.arrivalDate;
	}

	public GregorianCalendar getExpectedDepartureDate() {
		System.out.println(this.departureDate.getTime());
		return this.departureDate;
	}
}
