package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
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
		//by this point, there should be at least
		//one call to set the database connection
		//from the constructor in the setup
		assertTrue(_fakeSQLite.getSetConectionCallHistory().size() >= 1);
		
		//until we're doing a version check and migration, we
		//should also always be running the initialization script
		assertTrue(_fakeSQLite.getInitializeDatabaseCallHistory().size() >= 1);
		assertArrayEquals(SQLCodeConstants.databaseInit(), _fakeSQLite.getInitializeDatabaseCallHistory().get(0));
	}
	
	//TODO: These next few tests are really tests of the SQLCodeConstants class.
	//In order to test true conversion of an SQL result into a Cat, we may
	//need to wrap up the ResultSet object to give fake results to the
	//AnimalDatabaseSQLite object, and I think that kind of testing
	//of these adapters isn't really standard practice
	
	@Test
	public void getFilteredCatsUsesProperQuery()
	{
		_animalDB.setSearchFiler("Stormy");
		_animalDB.setSearchFilterType(SearchFilterType.Name);
		
		_animalDB.getFilteredCats();
		
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
	public void getSingleCatUserProperQuery()
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
}
