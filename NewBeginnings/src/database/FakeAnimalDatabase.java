package database;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;

/**
 * Fake for the AnimalDatabase
 * NOTE: We should probably migrate this to the test project
 * later in development
 *
 */
public class FakeAnimalDatabase implements IAnimalDatabase
{
	private List<Cat> _fakeCats;
	
	public FakeAnimalDatabase()
	{	
		_fakeCats = new LinkedList<Cat>();
		_fakeCats.add(new Cat("NB-14-3", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-1", "Storm", Calendar.getInstance(), "F", "Aegean", "Brown and White", false, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-2", "Han Solo", Calendar.getInstance(), "M", "American Curl", "Black with White Stripes", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-5", "Fluffy", Calendar.getInstance(), "F", "Australian Mist", "White", true, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(new Cat("NB-14-4", "Boots", Calendar.getInstance(), "M", "Siberian", "Jet Black", true, Calendar.getInstance(), Calendar.getInstance()));//this repeat is intentional. Note the different ID number
		_fakeCats.add(new Cat("NB-14-7", "Geoff", Calendar.getInstance(), "M", "California Spangled", "Spotted Orange", false, Calendar.getInstance(), Calendar.getInstance()));
		_fakeCats.add(Cat.emptyCat());
	}
	
	@Override
	public List<Cat> getFilteredCats(SearchFilterType filterType, String filter)
	{
		return _fakeCats;
	}

	@Override
	public Cat getSingleCat(String catID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNewCat(Cat cat) {
		_fakeCats.add(cat);		
	}

	@Override
	public void updateCat(Cat cat) {
		// TODO Auto-generated method stub
		
	}
}