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
		_fakeCats.add(new Cat("Boots", 3, "M", "Siberian", "Jet Black", "NB-14-3", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("Storm", 2, "F", "Aegean", "Brown and White", "NB-14-1", false, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("Han Solo", 4, "M", "American Curl", "Black with White Stripes", "NB-14-2", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("Fluffy", 3, "F", "Australian Mist", "White", "NB-14-5", true, new GregorianCalendar(), new GregorianCalendar()));
		_fakeCats.add(new Cat("Boots", 3, "M", "Siberian", "Jet Black", "NB-14-4", true, new GregorianCalendar(), new GregorianCalendar()));//this repeat is intentional. Note the different ID number
		_fakeCats.add(new Cat("Geoff", 5, "M", "California Spangled", "Spotted Orange", "NB-14-7", false, new GregorianCalendar(), new GregorianCalendar()));
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
}