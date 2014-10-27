package database;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;

/**
 * Fake for the AnimalDatabase NOTE: We should probably migrate this to the test
 * project later in development
 * 
 */
public class FakeAnimalDatabase implements IAnimalDatabase
{
	private List<Cat> _fakeCats;
	private IAnimalDatabase _realDatabase;
	
	public FakeAnimalDatabase()
	{
		this(true);
	}
	
	public FakeAnimalDatabase(boolean deleteFile)
	{	
		_fakeCats = new LinkedList<Cat>();
		_fakeCats.add(new Cat("NB-14-003", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-001", "Storm", Calendar.getInstance(), "F", "Aegean", "Brown and White", false, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-009", "Garfield", Calendar.getInstance(), "M", "Calico", "Dark Orange", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-002", "Han Solo", Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-005", "Fluffy", Calendar.getInstance(), "F", "Australian Mist", "White", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-004", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", true, Calendar.getInstance(), Calendar.getInstance()));//this repeat is intentional. Note the different ID number
		_fakeCats.add(new Cat("NB-14-007", "Geoff", Calendar.getInstance(), "M", "California Spangled", "Spotted Orange", false, Calendar.getInstance(), Calendar.getInstance()));
		
		String fakeFilename = "test.db";
		String fakeDatabasePath = "jdbc:sqlite:" + fakeFilename;
		
		if(deleteFile)
		{
			File f = new File(fakeFilename);
			if(f.exists() && !f.isDirectory())
			{
				f.delete();
			}
		}
		
		try {
			_realDatabase = new AnimalDatabaseSQLite(new SQLiteWrapper(fakeDatabasePath));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Cat cat : _fakeCats)
		{
			_realDatabase.addNewCat(cat);
		}
	}
	
	@Override
	public List<Cat> getFilteredCats(SearchFilterType filterType, String filter) {
		return _realDatabase.getFilteredCats(filterType, filter);
	}

	@Override
	public Cat getSingleCat(String catID) {
		return _realDatabase.getSingleCat(catID);
	}

	@Override
	public void addNewCat(Cat cat) {
		_realDatabase.addNewCat(cat);
	}

	@Override
	public void updateCat(Cat cat) {
		_realDatabase.updateCat(cat);
	}

	@Override
	public String getSuggestedNextID() {
		return _realDatabase.getSuggestedNextID();
	}
}