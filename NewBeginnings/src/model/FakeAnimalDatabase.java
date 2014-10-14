package model;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Fake for the AnimalDatabase
 * NOTE: We should probably migrate this to the test project
 * later in development
 *
 */
public class FakeAnimalDatabase implements IAnimalDatabase
{
	private SearchFilterType _filterType;
	private String _searchFilter;

	private List<Cat> _fakeCats;
	
	public FakeAnimalDatabase()
	{
		_filterType = SearchFilterType.Name;
		_searchFilter = "";
	
		_fakeCats = new LinkedList<Cat>();
		_fakeCats.add(new Cat("NB-14-3", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-1", "Storm", 2, "F", "Aegean", "Brown and White", false, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-2", "Han Solo", 4, "M", "American Curl", "Black with White Stripes", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-5", "Fluffy", 3, "F", "Australian Mist", "White", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("NB-14-4", "Boots", 3, "M", "Siberian", "Jet Black", true, new GregorianCalendar(), new GregorianCalendar()));//this repeat is intentional. Note the different ID number
		_fakeCats.add(new Cat("NB-14-7", "Geoff", 5, "M", "California Spangled", "Spotted Orange", false, new GregorianCalendar(), new GregorianCalendar()));
	}
	
	@Override
	public void setSearchFilterType(SearchFilterType filterType)
	{
		_filterType = filterType;
	}

	@Override
	public void setSearchFiler(String filter)
	{
		_searchFilter = filter;
	}
	
	@Override
	public List<Cat> getFilteredCats()
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCat(Cat cat) {
		// TODO Auto-generated method stub
		
	}
}