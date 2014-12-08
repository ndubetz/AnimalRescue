package database;

import static org.junit.Assert.*;

import java.util.Calendar;

import model.Cat;

import org.junit.Before;
import org.junit.Test;

import database.AnimalDatabaseSQLite;
import database.SQLiteDatabaseInterface;
import database.SearchFilterType;
import fakes.FakeSQLiteWrapper;

public class AnimalDatabaseSQLiteTest 
{
	AnimalDatabaseSQLite _animalDB;
	FakeSQLiteWrapper _fakeSQLite;
	
	@Before
	public void setUp() throws Exception 
	{
		_fakeSQLite = new FakeSQLiteWrapper();
		_animalDB = new AnimalDatabaseSQLite(_fakeSQLite);
	}

	@Test
	public void constructorSetsUpWrapper() 
	{		
		//until we're doing a version check and migration, we
		//should also always be running the initialization script
		assertTrue(_fakeSQLite.getInitializeDatabaseCallHistory().size() >= 1);
		assertArrayEquals(SQLiteDatabaseInterface.databaseInit(), _fakeSQLite.getInitializeDatabaseCallHistory().get(0));
	}
	
	//These next few tests are really tests of the use of the SQLCodeConstants
	//class. Further tests are in the AnimalDatabaseSQLiteTestWithLiveDatabase
	//case. 
	
	@Test
	public void getFilteredCatsUsesProperQuery()
	{
		_animalDB.getFilteredCats(SearchFilterType.Name, "Stormy");
		
		int callCount = _fakeSQLite.getExecuteQueryCallHistory().size();
		assertTrue(callCount >= 1);
		
		String expectedSQL = 
				"SELECT *\n" + 
				"FROM Cats C\n" + 
				"WHERE C.Name LIKE 'Stormy%';\n";
		
		String actualSQL = _fakeSQLite.getExecuteQueryCallHistory().get(callCount - 1);
		assertEquals(expectedSQL, actualSQL);
	}
	
	@Test
	public void getSingleCatUsesProperQuery()
	{
		String fakeID = "NB-14-1337";
		_animalDB.getSingleCat(fakeID);
		
		int callCount = _fakeSQLite.getExecuteQueryCallHistory().size();
		assertTrue(callCount >= 1);
		
		String expectedSQL = 
				"SELECT *\n" + 
				"FROM Cats C\n" + 
				"WHERE C.id='" + fakeID + "';\n";
		
		String actualSQL = _fakeSQLite.getExecuteQueryCallHistory().get(callCount - 1);
		assertEquals(expectedSQL, actualSQL);
	}
	
	@Test
	public void addCatUsesProperQuery()
	{
		Cat cat = new Cat("NB-14-003", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");

		_animalDB.addNewCat(cat);
		int callCount = _fakeSQLite.getExecuteUpdateCallHistory().size();
		assertTrue(callCount >= 1);
		
		String expectedSQL = String.format(SQLiteDatabaseInterface.insertNewCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getBirthdate().getTimeInMillis(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.getFixed(),
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				cat.getRabies(),
				cat.getFeLeuk(),
				cat.getDistemper(),
				"",
				cat.getCatPictureFilePath()
				);
		
		String actualSQL = _fakeSQLite.getExecuteUpdateCallHistory().get(callCount - 1);
		assertEquals(expectedSQL, actualSQL);		
	}
	
	@Test
	public void updateCatUsesProperQuery()
	{
		Cat cat = new Cat("NB-14-003", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", "", Calendar.getInstance(), Calendar.getInstance(),
				"", "", "", new String[]{}, "src/resources/Images/TestImage.jpg", "Comments");

		_animalDB.updateCat(cat);
		int callCount = _fakeSQLite.getExecuteUpdateCallHistory().size();
		assertTrue(callCount >= 1);
		
		String expectedSQL = String.format(SQLiteDatabaseInterface.updateExistingCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getBirthdate().getTimeInMillis(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.getFixed(),
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				cat.getRabies(),
				cat.getFeLeuk(),
				cat.getDistemper(),
				"",
				cat.getCatPictureFilePath(),
				cat.getID()
				);
		
		String actualSQL = _fakeSQLite.getExecuteUpdateCallHistory().get(callCount - 1);
		assertEquals(expectedSQL, actualSQL);		
	}
	
	@Test
	public void getSuggestedNextIDReturnsCorrectIDReturnsProperDefault()
	{
		String exectedDefaultID = 
				"NB-"
				+ (Calendar.getInstance().get(Calendar.YEAR) - 2000)
				+ "-001";
		
		String actualID = _animalDB.getSuggestedNextID();
		
		assertEquals(exectedDefaultID, actualID);
	}
}
