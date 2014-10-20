package database;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
		_fakeCats.add(new Cat("NB-14-3", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-1", "Storm", Calendar.getInstance(), "F", "Aegean", "Brown and White", false, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-9", "Garfield", Calendar.getInstance(), "M", "Calico", "Dark Orange", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-2", "Han Solo", Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-5", "Fluffy", Calendar.getInstance(), "F", "Australian Mist", "White", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-4", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", true, Calendar.getInstance(), Calendar.getInstance()));//this repeat is intentional. Note the different ID number
		_fakeCats.add(new Cat("NB-14-7", "Geoff", Calendar.getInstance(), "M", "California Spangled", "Spotted Orange", false, Calendar.getInstance(), Calendar.getInstance()));
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
		Cat newIDCat = new Cat("NB-14-2", "Peppermint", Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", true, Calendar.getInstance(), Calendar.getInstance());
		_animalDB.updateCat(newIDCat);
		System.out.println("Getting cat with ID NB-14-2:");
		Cat retrievedNewIdCat = _animalDB.getSingleCat("NB-14-2");
		System.out.println("Name: " + retrievedNewIdCat.getName());
		
		//Test date storage format
		Calendar birthdate = Calendar.getInstance();
		birthdate.set(2011, Calendar.JUNE, 3);
		
		Cat dateCat = new Cat("NB-14-789", "Smeagol", birthdate, "M", "Siamese", "White", false, Calendar.getInstance(), Calendar.getInstance());
		System.out.print("\n\nDisplay age: ");
		System.out.println(dateCat.getAge());
		
		System.out.print("Birthdate before storage: ");
		System.out.print(dateCat.getBirthdate().get(Calendar.YEAR));
		System.out.print("\\");
		System.out.print(dateCat.getBirthdate().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));
		System.out.print("\\");
		System.out.println(dateCat.getBirthdate().get(Calendar.DAY_OF_MONTH));
		
		_animalDB.addNewCat(dateCat);
		
		Cat retrievedDateCat = _animalDB.getSingleCat("NB-14-789");
		System.out.print("Birthdate after storage:  ");
		System.out.print(retrievedDateCat.getBirthdate().get(Calendar.YEAR));
		System.out.print("\\");
		System.out.print(retrievedDateCat.getBirthdate().getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));
		System.out.print("\\");
		System.out.println(retrievedDateCat.getBirthdate().get(Calendar.DAY_OF_MONTH));
	}
}
