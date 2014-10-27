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
		Cat updatedCat = new Cat("NB-14-002", newName, Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", true, Calendar.getInstance(), Calendar.getInstance());
		_animalDB.updateCat(updatedCat);
		
		Cat retrievedNewIdCat = _animalDB.getSingleCat("NB-14-002");

		assertEquals(newName, retrievedNewIdCat.getName());
	}
	
	@Test
	public void dateStorage()
	{
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(2011, Calendar.JUNE, 3);
		Cat dateCat = new Cat("NB-14-789", "Smeagol", birthdate, "M", "Siamese", "White", false, Calendar.getInstance(), Calendar.getInstance());
		_animalDB.addNewCat(dateCat);
		
		Cat retrievedDateCat = _animalDB.getSingleCat(dateCat.getID());
		
		assertEquals(dateCat.getBirthdate().get(Calendar.YEAR),retrievedDateCat.getBirthdate().get(Calendar.YEAR));
		assertEquals(dateCat.getBirthdate().get(Calendar.MONTH),retrievedDateCat.getBirthdate().get(Calendar.MONTH));
		assertEquals(dateCat.getBirthdate().get(Calendar.DAY_OF_MONTH),retrievedDateCat.getBirthdate().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void suggestedID()
	{
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 2000;
		String tempMaxID = "NB-" + currentYear + "-998";
		String expectedSuggestedID = "NB-" + currentYear + "-999";
		
		Cat tempMaxCat = new Cat(tempMaxID, "Smeagol", Calendar.getInstance(), "M", "Siamese", "White", false, Calendar.getInstance(), Calendar.getInstance());
		_animalDB.addNewCat(tempMaxCat);
		
		assertEquals(expectedSuggestedID, _animalDB.getSuggestedNextID());
	}
}
