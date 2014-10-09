package model;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

public class CatTest {

	@Test
	public void testConstructor() {
		String expectedGender = "Male";
		String expectedName = "Mittens";
		String expectedBreed = "Tabby";
		String expectedHairColor = "Orange";
		int expectedAge = 7;
		GregorianCalendar expectedArrivalDate = new GregorianCalendar(2011, 0, 11);
		GregorianCalendar expectedDepartureDate = new GregorianCalendar(2013, 4, 12);
		Cat cat = new Cat(expectedName, expectedAge, expectedGender, expectedBreed, expectedHairColor, expectedArrivalDate, expectedDepartureDate);
		assertSame(expectedName, cat.getName());
		assertSame(expectedAge, cat.getAge());
		assertSame(expectedGender, cat.getGender());
		assertSame(expectedBreed, cat.getBreed());
		assertSame(expectedHairColor, cat.getHairColor());
		assertSame(expectedArrivalDate, cat.getArrivalDate());
		assertSame(expectedDepartureDate, cat.getExpectedDepartureDate());
	}
	

}
