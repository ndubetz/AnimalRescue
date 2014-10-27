package database;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;

/**
 * Fake for the AnimalDatabase NOTE: We should probably migrate this to the test
 * project later in development
 * 
 */
<<<<<<< HEAD
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
=======
public class FakeAnimalDatabase implements IAnimalDatabase {
	private final List<Cat> _fakeCats;

	public FakeAnimalDatabase() {
		this._fakeCats = new LinkedList<Cat>();
		this._fakeCats.add(new Cat("NB-14-3", "Boots", Calendar.getInstance(),
				"M", "Siberian", "Jet Black", true, Calendar.getInstance(),
				Calendar.getInstance()));
		this._fakeCats.add(new Cat("NB-14-1", "Storm", Calendar.getInstance(),
				"F", "Aegean", "Brown and White", false,
				Calendar.getInstance(), Calendar.getInstance()));
		this._fakeCats.add(new Cat("NB-14-2", "Han Solo", Calendar
				.getInstance(), "M", "American Curl",
				"Black with White Stripes", true, Calendar.getInstance(),
				Calendar.getInstance()));
		this._fakeCats.add(new Cat("NB-14-5", "Fluffy", Calendar.getInstance(),
				"F", "Australian Mist", "White", true, Calendar.getInstance(),
				Calendar.getInstance()));
		this._fakeCats.add(new Cat("NB-14-4", "Boots", Calendar.getInstance(),
				"M", "Siberian", "Jet Black", true, Calendar.getInstance(),
				Calendar.getInstance()));// this repeat is intentional. Note the
											// different ID number
		this._fakeCats.add(new Cat("NB-14-7", "Geoff", Calendar.getInstance(),
				"M", "California Spangled", "Spotted Orange", false, Calendar
						.getInstance(), Calendar.getInstance()));
		this._fakeCats.add(Cat.emptyCat());
>>>>>>> 2ccd09fdec596ba9004066d0e1038aba87d7cdd9
	}

	@Override
	public List<Cat> getFilteredCats(SearchFilterType filterType, String filter) {
		return this._fakeCats;
	}

	@Override
	public Cat getSingleCat(String catID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewCat(Cat cat) {
		this._fakeCats.add(cat);
	}

	@Override
	public void updateCat(Cat cat) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSuggestedNextID() {
		// TODO Auto-generated method stub
		return null;
	}
}