package database;

import java.io.File;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;

import org.junit.Before;
import org.junit.Test;

import database.AnimalDatabaseSQLite;
import database.IAnimalDatabase;
import database.ISQLiteWrapper;
import database.SQLiteWrapper;
import database.SearchFilterType;

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
		//do test on a different file that we delete before running tests
		String testFilename = "test.db";
		String testDatabasePath = "jdbc:sqlite:" + testFilename;
		File f = new File(testFilename);
		if(f.exists() && !f.isDirectory())
		{
			f.delete();
		}
		
		_wrapper = new SQLiteWrapper(testDatabasePath);
		_animalDB = new AnimalDatabaseSQLite(_wrapper);
		
		_fakeCats = new LinkedList<Cat>();
		_fakeCats.add(new Cat("NB-14-3", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-1", "Storm", 2, "F", "Aegean", "Brown and White", false, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-9", "Garfield", 5, "M", "Calico", "Dark Orange", true, new GregorianCalendar(), new GregorianCalendar()));
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
		
		String filter = "G";
		System.out.println("Filtered cats with filter " + filter + ":");
		List<Cat> filteredCats = _animalDB.getFilteredCats(SearchFilterType.Name, filter);
		for(Cat cat : filteredCats)
		{
			System.out.println(cat.getID() + "\t" + cat.getName() + "\t" + cat.getBreed());
		}
		
		System.out.println("\nGetting cat with ID NB-14-2:");
		Cat idCat = _animalDB.getSingleCat("NB-14-2");
		System.out.println("Name: " + idCat.getName());
		System.out.println("Changing name to 'Peppermint':");
		
		//currently do not have setters in the cat class
		Cat newIDCat = new Cat("NB-14-2", "Peppermint", 4, "M", "American Curl", "Black with White Stripes", true, new GregorianCalendar(), new GregorianCalendar());
		_animalDB.updateCat(newIDCat);
		System.out.println("Getting cat with ID NB-14-2:");
		Cat retrievedNewIdCat = _animalDB.getSingleCat("NB-14-2");
		System.out.println("Name: " + retrievedNewIdCat.getName());
	}
}