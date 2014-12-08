package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
		String expectedIsFixed = "please spay and neuter your pets";

		Calendar expectedArrivalDate = Calendar.getInstance();
		expectedArrivalDate.set(Calendar.YEAR, 2011);
		expectedArrivalDate.set(Calendar.MONTH, Calendar.MARCH);
		expectedArrivalDate.set(Calendar.DAY_OF_MONTH, 5);

		Calendar expectedDepartureDate = Calendar.getInstance();
		expectedDepartureDate.set(Calendar.YEAR, 2013);
		expectedDepartureDate.set(Calendar.MONTH, Calendar.DECEMBER);
		expectedDepartureDate.set(Calendar.DAY_OF_MONTH, 7);

		String expectedRabies = "gross";
		String expectedFeLeuk = "ew";
		String expectedDistemper = "bratty catty";
		String[] expectedMedicalHistory = new String[] {};
		String expectedCatImageFilePath = "C:/Cat";
		String expectedCatComments = "Comment";

		Cat cat = new Cat(expectedID, expectedName, expectedBirthdate,
				expectedGender, expectedBreed, expectedHairColor,
				expectedIsFixed, expectedArrivalDate, expectedDepartureDate,
				expectedRabies, expectedFeLeuk, expectedDistemper,
				expectedMedicalHistory, expectedCatImageFilePath, expectedCatComments);

		assertSame(expectedName, cat.getName());
		assertSame(expectedBirthdate, cat.getBirthdate());
		assertSame(expectedGender, cat.getGender());
		assertSame(expectedBreed, cat.getBreed());
		assertSame(expectedHairColor, cat.getHairColor());
		assertSame(expectedID, cat.getID());
		assertSame(expectedIsFixed, cat.getFixed());
		assertSame(expectedArrivalDate, cat.getArrivalDate());
		assertSame(expectedDepartureDate, cat.getExpectedDepartureDate());
		assertSame(expectedRabies, cat.getRabies());
		assertSame(expectedFeLeuk, cat.getFeLeuk());
		assertSame(expectedDistemper, cat.getDistemper());
		assertSame(expectedMedicalHistory, cat.getMedicalHistory());
		assertSame(expectedCatImageFilePath, cat.getCatPictureFilePath());
		assertSame(expectedCatComments, cat.getCommentsAboutCat());
	}

	@Test
	public void testEmptyCatIsEmpty() throws Exception {
		Cat cat = Cat.emptyCat();
		String blankID = "NB-00-000";
		assertEquals(blankID, cat.getID());
		assertEquals("", cat.getName());
		// Possible that time may tick over during the test itself
		assertTrue(cat.getBirthdate().getTimeInMillis() <= Calendar
				.getInstance().getTimeInMillis());
		assertEquals("", cat.getGender());
		assertEquals("", cat.getBreed());
		assertEquals("", cat.getHairColor());
		assertEquals("", cat.getFixed());
		assertEquals("", cat.getRabies());
		assertEquals("", cat.getFeLeuk());
		assertEquals("", cat.getDistemper());
		assertTrue(cat.getArrivalDate().getTimeInMillis() <= Calendar
				.getInstance().getTimeInMillis());
		assertTrue(cat.getExpectedDepartureDate().getTimeInMillis() <= Calendar
				.getInstance().getTimeInMillis());
	}

	@Test
	public void testIsTheEmptyCat() throws Exception {
		assertTrue(Cat.isTheEmptyCat(Cat.emptyCat()));

		Cat catThatIsEmptyExceptForID = new Cat("skroob", "",
				Calendar.getInstance(), "", "", "", "", Calendar.getInstance(),
				Calendar.getInstance(), "", "", "", new String[] {}, "src/resources/Images/TestImage.jpg", "Comments");

		assertFalse(Cat.isTheEmptyCat(catThatIsEmptyExceptForID));
	}

	@Test
	public void getAgeReturnsProperlyPlural() throws Exception {
		// make sure the cat is a known age
		long twoYearsTwoMonthsMs = 68299200000l;

		Calendar now = Calendar.getInstance();

		long birthdateMs = now.getTimeInMillis() - twoYearsTwoMonthsMs;
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTimeInMillis(birthdateMs);

		Cat cat = new Cat("skroob", "", birthdate, "", "", "", "",
				Calendar.getInstance(), Calendar.getInstance(), "", "", "",
				new String[] {}, "src/resources/Images/TestImage.jpg", "");

		assertEquals("2 Years, 2 Months", cat.getAge(birthdate));
	}

	@Test
	public void getAgeReturnsProperlySingular() throws Exception {
		// make sure the cat is a known age
		long oneYearOneMonthMs = 34149600000l;

		Calendar now = Calendar.getInstance();

		long birthdateMs = now.getTimeInMillis() - oneYearOneMonthMs;
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTimeInMillis(birthdateMs);

		Cat cat = new Cat("skroob", "", birthdate, "", "", "", "",
				Calendar.getInstance(), Calendar.getInstance(), "", "", "",
				new String[] {}, "src/resources/Images/TestImage.jpg", "");

		assertEquals("1 Year, 1 Month", cat.getAge(birthdate));
	}
	
	@Test
	public void testCalendarGetsFormattedAsString() throws Exception {
		String expectedBirthdate = "10/10/2010";
		String expectedArrivalDate = "05/16/2012";
		String expectedDepartureDate = "08/16/2012";
		
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(Calendar.YEAR, 2010);
		birthdate.set(Calendar.MONTH, 9);
		birthdate.set(Calendar.DAY_OF_MONTH, 10);
		
		Calendar arrivaldate = Calendar.getInstance();
		arrivaldate.set(Calendar.YEAR, 2012);
		arrivaldate.set(Calendar.MONTH, 4);
		arrivaldate.set(Calendar.DAY_OF_MONTH, 16);
		
		Calendar departureDate = Calendar.getInstance();
		departureDate.set(Calendar.YEAR, 2012);
		departureDate.set(Calendar.MONTH, 7);
		departureDate.set(Calendar.DAY_OF_MONTH, 16);
		
		Cat cat = new Cat("skroob", "", birthdate, "", "", "", "",
				arrivaldate, departureDate, "", "", "",
				new String[] {}, "", "");
		
		assertEquals(expectedBirthdate, cat.getBirthdateAsDateFormattedString());
		assertEquals(expectedArrivalDate, cat.getArrivalDateAsDateFormattedString());
		assertEquals(expectedDepartureDate, cat.getDepartureDateAsDateFormattedString());		
	}
}
