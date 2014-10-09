package model;
import java.util.List;

public class AnimalDatabase implements IAnimalDatabase
{
	private String _databasePath;
	private SearchFilterType _filterType;
	private String _searchFilter;

	private static final String C_SQLQuery = 
		"SELECT *" + 
		"FROM Cats C" +
		"WHERE C.%s LIKE %s%;";
	
	public AnimalDatabase()
	{
		_filterType = SearchFilterType.Name;
		_searchFilter = "";
	}
	
	public void setSearchFilterType(SearchFilterType filterType)
	{
		_filterType = filterType;
	}

	public void setSearchFiler(String filter)
	{
		_searchFilter = filter;
	}
	
	public List<Cat> getFilteredCats()
	{
		String query = buildQuery();
		
		//here, we would make the actual SQLite query and 
		//martial the results into a new list of cat objects
		
		return null;
	}
	
	private String buildQuery()
	{
		String filterColumn;
		
		switch(_filterType)
		{
		case Name: 
			filterColumn = "Name";
			break;
		default:
			filterColumn = "";
		}
		
		return String.format(C_SQLQuery, filterColumn, _searchFilter);
	}
}