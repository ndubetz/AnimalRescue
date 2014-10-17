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
		String expectedID = "NB-14-324";
		boolean expectedIsFixed = true;
		GregorianCalendar expectedArrivalDate = new GregorianCalendar(2011, 0, 11);
		GregorianCalendar expectedDepartureDate = new GregorianCalendar(2013, 4, 12);
		
		Cat cat = new Cat(expectedID, expectedName, expectedAge, expectedGender, expectedBreed, expectedHairColor, expectedIsFixed, expectedArrivalDate, expectedDepartureDate);
		
		assertSame(expectedName, cat.getName());
		assertSame(expectedAge, cat.getAge());
		assertSame(expectedGender, cat.getGender());
		assertSame(expectedBreed, cat.getBreed());
		assertSame(expectedHairColor, cat.getHairColor());
		assertSame(expectedID, cat.getID());
		assertSame(expectedIsFixed, cat.isFixed());
		assertSame(expectedArrivalDate, cat.getArrivalDate());
		assertSame(expectedDepartureDate, cat.getExpectedDepartureDate());
	}
	
	@Test
	public void testEmptyCatIsEmpty() throws Exception {
		Cat cat = Cat.emptyCat();
		String blankID = "NB-XX-YYY";
		assertEquals(blankID, cat.getID());
		assertEquals("", cat.getName());
		assertEquals(-1, cat.getAge());
		assertEquals("", cat.getGender());
		assertEquals("", cat.getBreed());
		assertEquals("", cat.getHairColor());
		assertEquals(false, cat.isFixed());
		assertEquals(new GregorianCalendar(0,0,0), cat.getArrivalDate());
		assertEquals(new GregorianCalendar(0,0,0), cat.getExpectedDepartureDate());
	}
	
	@Test
	public void testIsTheEmptyCat() throws Exception {
		assertTrue(Cat.isTheEmptyCat(Cat.emptyCat()));
		
		Cat catThatIsEmptyExceptForID = new Cat("skroob", "", 0, "", "", "", 
			false, new GregorianCalendar(), new GregorianCalendar());
		
		assertFalse(Cat.isTheEmptyCat(catThatIsEmptyExceptForID));		
	}
}