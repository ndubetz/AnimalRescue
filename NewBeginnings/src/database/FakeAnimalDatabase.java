package database;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;
import model.catHistory.CatHistory;

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
		_fakeCats.add(new Cat("NB-14-003", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", "yes", Calendar.getInstance(), Calendar.getInstance(), "10/11/12, 3 year", "FiV+ FlV-", "1 year", new String[]{"10-4-2014 | Got rabies shot", "11-2-2014 | Open heart surgery"}, "src/resources/Images/TestImage.jpg"));
		_fakeCats.add(new Cat("NB-14-001", "Storm", Calendar.getInstance(), "F", "Aegean", "Brown and White", "10/14/14", Calendar.getInstance(), Calendar.getInstance(), "September 3rd 2014, 1 year", "FiV- FlV-", "3 year", new String[]{"9-10-2014 | Gave bith to kittens"}, "src/resources/Images/TestImage.jpg"));
		_fakeCats.add(new Cat("NB-14-009", "Garfield", Calendar.getInstance(), "M", "Calico", "Dark Orange", "no", Calendar.getInstance(), Calendar.getInstance(), "July 11 2013, 1 year", "FiV- FlV-", "1 year", new String[]{"12-13-2013 | Prescribed medication", "1-4-2014 | Stopped taking medication", "3-6-2014 | Broken leg", "5-4-2014 | Cast removed"}, "src/resources/Images/TestImage.jpg"));
		_fakeCats.add(new Cat("NB-14-002", "Han Solo", Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", "YES 3/10/1013", Calendar.getInstance(), Calendar.getInstance(), "11/10/13, 3 year", "FiV+ FlV-", "1 year", new String[]{}, "src/resources/Images/TestImage.jpg"));
		_fakeCats.add(new Cat("NB-14-005", "Fluffy", Calendar.getInstance(), "F", "Australian Mist", "White", "january 1st 2011", Calendar.getInstance(), Calendar.getInstance(), "Feb fourth 2011 3-year", "FiV- FlV-", "1 year", new String[]{}, "src/resources/Images/TestImage.jpg"));
		_fakeCats.add(new Cat("NB-14-004", "Zephram", Calendar.getInstance(), "M", "Hungarian Tabby", "Light Black", "no", Calendar.getInstance(), Calendar.getInstance(), "9/6/14, 1year", "FiV+ FlV-", "3 year", new String[]{}, "src/resources/Images/TestImage.jpg"));
		_fakeCats.add(new Cat("NB-14-007", "Geoff", Calendar.getInstance(), "M", "California Spangled", "Spotted Orange", "yes", Calendar.getInstance(), Calendar.getInstance(), "December 7th 2013 1-yr", "FiV+ FlV-", "3 year", new String[]{}, "src/resources/Images/TestImage.jpg"));
		
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
		_fakeCats.add(cat);
	}

	@Override
	public void updateCat(Cat cat) {
		_realDatabase.updateCat(cat);
	}

	@Override
	public String getSuggestedNextID() {
		return _realDatabase.getSuggestedNextID();
	}
	
	@Override
	public CatHistory getCatHistory(String catID) {
		return _realDatabase.getCatHistory(catID);
	}

	@Override
	public void insertCatHistory(CatHistory catHistory) {
		_realDatabase.insertCatHistory(catHistory);
	}

	@Override
	public void updateCatHistory(CatHistory catHistory) {
		_realDatabase.updateCatHistory(catHistory);
	}
	
	public List<Cat> getFakeCatsList(){
		return _fakeCats;
	}
	
	public void check(){
		System.out.println(_fakeCats.get(7).getName());
	}
}