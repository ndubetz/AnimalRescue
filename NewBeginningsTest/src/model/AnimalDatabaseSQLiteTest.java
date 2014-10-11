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
		assertArrayEquals(SQLCodeConstants.C_DatabaseSchema, _fakeSQLite.getInitializeDatabaseCallHistory().get(0));
	}
	
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
}
