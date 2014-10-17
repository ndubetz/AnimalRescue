package database;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

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
	
	//TODO: These next few tests are really tests of the use of the SQLCodeConstants
	//class. In order to test true conversion of an SQL result into a Cat, we may
	//need to wrap up the ResultSet object to give fake results to the
	//AnimalDatabaseSQLite object, and I think that kind of testing
	//of these adapters isn't really standard practice
	
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
		Cat cat = new Cat("NB-14-3", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar());

		_animalDB.addNewCat(cat);
		int callCount = _fakeSQLite.getExecuteUpdateCallHistory().size();
		assertTrue(callCount >= 1);
		
		String expectedSQL = String.format(SQLiteDatabaseInterface.insertNewCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getAge(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.isFixed()? 0 : 1,
				cat.getArrivalDate().toString(),
				cat.getExpectedDepartureDate().toString()
				);
		
		String actualSQL = _fakeSQLite.getExecuteUpdateCallHistory().get(callCount - 1);
		assertEquals(expectedSQL, actualSQL);		
	}
	
	@Test
	public void updateCatUsesProperQuery()
	{
		Cat cat = new Cat("NB-14-3", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar());

		_animalDB.updateCat(cat);
		int callCount = _fakeSQLite.getExecuteUpdateCallHistory().size();
		assertTrue(callCount >= 1);
		
		String expectedSQL = String.format(SQLiteDatabaseInterface.updateExistingCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getAge(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.isFixed()? 0 : 1,
				cat.getArrivalDate().toString(),
				cat.getExpectedDepartureDate().toString(),
				cat.getID()
				);
		
		String actualSQL = _fakeSQLite.getExecuteUpdateCallHistory().get(callCount - 1);
		assertEquals(expectedSQL, actualSQL);		
	}
}