package model;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class CatTest {

	@Test
	public void testConstructor() {
		String expectedGender = "Male";
		String expectedName = "Mittens";
		String expectedBreed = "Tabby";
		String expectedHairColor = "Orange";
		
		Calendar expectedBirthdate = Calendar.getInstance();
		expectedBirthdate.set(Calendar.YEAR, 2011);
		expectedBirthdate.set(Calendar.MONTH, Calendar.FEBRUARY);
		expectedBirthdate.set(Calendar.DAY_OF_MONTH, 3);
		
		String expectedID = "NB-14-324";
		boolean expectedIsFixed = true;
		
		Calendar expectedArrivalDate = Calendar.getInstance();
		expectedArrivalDate.set(Calendar.YEAR, 2011);
		expectedArrivalDate.set(Calendar.MONTH, Calendar.MARCH);
		expectedArrivalDate.set(Calendar.DAY_OF_MONTH, 5);
		
		Calendar expectedDepartureDate = Calendar.getInstance();
		expectedDepartureDate.set(Calendar.YEAR, 2013);
		expectedDepartureDate.set(Calendar.MONTH, Calendar.DECEMBER);
		expectedDepartureDate.set(Calendar.DAY_OF_MONTH, 7);
		
		Cat cat = new Cat(expectedID, expectedName, expectedBirthdate, expectedGender, expectedBreed, expectedHairColor, expectedIsFixed, expectedArrivalDate, expectedDepartureDate);
		
		assertSame(expectedName, cat.getName());
		assertSame(expectedBirthdate, cat.getBirthdate());
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
		//Possible that time may tick over during the test itself
		assertTrue(cat.getBirthdate().getTimeInMillis() <= Calendar.getInstance().getTimeInMillis());
		assertEquals("", cat.getGender());
		assertEquals("", cat.getBreed());
		assertEquals("", cat.getHairColor());
		assertEquals(false, cat.isFixed());
		assertTrue(cat.getArrivalDate().getTimeInMillis() <= Calendar.getInstance().getTimeInMillis());
		assertTrue(cat.getExpectedDepartureDate().getTimeInMillis() <= Calendar.getInstance().getTimeInMillis());
	}
	
	@Test
	public void testIsTheEmptyCat() throws Exception {
		assertTrue(Cat.isTheEmptyCat(Cat.emptyCat()));
		
		Cat catThatIsEmptyExceptForID = new Cat("skroob", "", Calendar.getInstance(), "", "", "", 
			false, Calendar.getInstance(), Calendar.getInstance());
		
		assertFalse(Cat.isTheEmptyCat(catThatIsEmptyExceptForID));		
	}
	
	@Test
	public void getAgeReturnsProperlyPlural() throws Exception{
		//make sure the cat is a known age
		long twoYearsTwoMonthsMs = 68299200000l;
		
		Calendar now = Calendar.getInstance();
		
		long birthdateMs = now.getTimeInMillis() - twoYearsTwoMonthsMs;
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTimeInMillis(birthdateMs);
		
		Cat cat = new Cat("skroob", "", birthdate, "", "", "", 
				false, Calendar.getInstance(), Calendar.getInstance());
		
		assertEquals("2 Years, 2 Months", cat.getAge());
	}
	
	@Test
	public void getAgeReturnsProperlySingular() throws Exception{
		//make sure the cat is a known age
		long oneYearOneMonthMs = 34149600000l;
		
		Calendar now = Calendar.getInstance();
		
		long birthdateMs = now.getTimeInMillis() - oneYearOneMonthMs;
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTimeInMillis(birthdateMs);
		
		Cat cat = new Cat("skroob", "", birthdate, "", "", "", 
				false, Calendar.getInstance(), Calendar.getInstance());
		
		assertEquals("1 Year, 1 Month", cat.getAge());
	}
}
