package model;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a temporary test to give me an entry point to make
 * sure the database stuff is working with file ops
 */
public class TemporarySQLiteDatabaseTest 
{
	ISQLiteWrapper _wrapper;
	IAnimalDatabase _animalDB;
	
	List<Cat> _fakeCats;
	
	@Before
	public void setUp() throws Exception 
	{
		System.out.println(SQLiteDatabaseInterface.getDefaultDatabaseConnectionString());
		_wrapper = new SQLiteWrapper(SQLiteDatabaseInterface.getDefaultDatabaseConnectionString());
		_animalDB = new AnimalDatabaseSQLite(_wrapper);
		
		_fakeCats = new LinkedList<Cat>();
		_fakeCats.add(new Cat("NB-14-3", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-1", "Storm", 2, "F", "Aegean", "Brown and White", false, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-2", "Han Solo", 4, "M", "American Curl", "Black with White Stripes", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-5", "Fluffy", 3, "F", "Australian Mist", "White", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-4", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar()));//this repeat is intentional. Note the different ID number
		_fakeCats.add(new Cat("NB-14-7", "Geoff", 5, "M", "California Spangled", "Spotted Orange", false, new GregorianCalendar(), new GregorianCalendar()));
	
	}

	@Test
	public void test() 
	{
		for(Cat cat : _fakeCats)
		{
			_animalDB.addNewCat(cat);
		}
		
		Cat c = _animalDB.getSingleCat("NB-14-7");
		System.out.println(c.getName());
		_animalDB.setSearchFiler("B");
		List<Cat> filteredCats = _animalDB.getFilteredCats();
		
		for(Cat cat : filteredCats)
		{
			System.out.println(cat.getName());
		}
	}

}
