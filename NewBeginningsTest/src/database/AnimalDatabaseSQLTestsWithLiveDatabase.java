package database;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import model.Cat;

import org.junit.Before;
import org.junit.Test;

/* This is a separate set of database tests that allow testing
 * with a live database. Without a real database, we would need
 * to fully wrap up most of the SQLite library to make full
 * mocks to test returns from the database connections.
 * 
 */
public class AnimalDatabaseSQLTestsWithLiveDatabase 
{
	IAnimalDatabase _animalDB;
	
	@Before
	public void setUp() throws Exception 
	{
		_animalDB = new FakeAnimalDatabase();
	}

	@Test
	public void getFilteredCatsGetsCorrectResult() 
	{
		String filter = "G";
		List<Cat> filteredCats = _animalDB.getFilteredCats(SearchFilterType.Name, filter);
		
		for(Cat cat : filteredCats)
		{
			assertTrue(cat.getName().startsWith(filter));
		}
	}
	
	@Test
	public void updateCatUpdatesCat()
	{
		String newName = "Peppermint";
		
		//currently do not have setters in the cat class
		Cat updatedCat = new Cat("NB-14-002", newName, Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");
		_animalDB.updateCat(updatedCat);
		
		Cat retrievedNewIdCat = _animalDB.getSingleCat("NB-14-002");

		assertEquals(newName, retrievedNewIdCat.getName());
	}
	
	@Test
	public void dateStorage()
	{
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(2011, Calendar.JUNE, 3);
		Cat dateCat = new Cat("NB-14-789", "Smeagol", birthdate, "M", "Siamese", "White", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");
		_animalDB.addNewCat(dateCat);
		
		Cat retrievedDateCat = _animalDB.getSingleCat(dateCat.getID());
		
		assertEquals(dateCat.getBirthdate().get(Calendar.YEAR),retrievedDateCat.getBirthdate().get(Calendar.YEAR));
		assertEquals(dateCat.getBirthdate().get(Calendar.MONTH),retrievedDateCat.getBirthdate().get(Calendar.MONTH));
		assertEquals(dateCat.getBirthdate().get(Calendar.DAY_OF_MONTH),retrievedDateCat.getBirthdate().get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testDefaultCatPictureFilePath() throws Exception {
		
		Cat dateCat = new Cat("NB-14-789", "Smeagol", Calendar.getInstance(), "M", "Siamese", "White", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");
		_animalDB.addNewCat(dateCat);
		
		Cat retrievedDateCat = _animalDB.getSingleCat(dateCat.getID());
		
		assertEquals(dateCat.getCatPictureFilePath(),retrievedDateCat.getCatPictureFilePath());

	}
	
	@Test
	public void testCommentsAddedToCat() throws Exception {
		Cat kittyNoCommentsAdded = new Cat("NB-14-789", "Smeagol", Calendar.getInstance(), "M", "Siamese", "White", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");
		_animalDB.addNewCat(kittyNoCommentsAdded);		
		Cat retrievedKittyCat = _animalDB.getSingleCat(kittyNoCommentsAdded.getID());		
		assertEquals(kittyNoCommentsAdded.getCommentsAboutCat(), retrievedKittyCat.getCommentsAboutCat());
		
		
		Cat kittyWithCommentsAdded = new Cat("NB-14-789", "Smeagol", Calendar.getInstance(), "M", "Siamese", "White", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "This cat bites");		
		_animalDB.updateCat(kittyWithCommentsAdded);		
		Cat retrievedCatCommentsEdited = _animalDB.getSingleCat(kittyWithCommentsAdded.getID());		
		assertEquals(kittyWithCommentsAdded.getCommentsAboutCat(), retrievedCatCommentsEdited.getCommentsAboutCat());
	}
	
	@Test
	public void suggestedID()
	{
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 2000;
		String tempMaxID = "NB-" + currentYear + "-998";
		String expectedSuggestedID = "NB-" + currentYear + "-999";
		
		Cat tempMaxCat = new Cat(tempMaxID, "Smeagol", Calendar.getInstance(), "M", "Siamese", "White", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");
		_animalDB.addNewCat(tempMaxCat);
		
		assertEquals(expectedSuggestedID, _animalDB.getSuggestedNextID());
	}
	
	
	@Test
	public void medicalHistoryStorage()
	{
		//this cat has plenty of medical history
		Cat medicalCat = ((FakeAnimalDatabase)_animalDB).getFakeCatsList().get(2);
		
		//retrieve this cat from the database
		Cat retrievedCat = _animalDB.getSingleCat(medicalCat.getID());
		
		assertArrayEquals(medicalCat.getMedicalHistory(), retrievedCat.getMedicalHistory());
	}
}
