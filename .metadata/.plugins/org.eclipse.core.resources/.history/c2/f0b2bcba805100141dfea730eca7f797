package model;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class AnimalDatabaseSQLite implements IAnimalDatabase
{
	private String _databasePath;
	private SearchFilterType _filterType;
	private String _searchFilter;
	private ISQLiteWrapper _sqlite;

	private static final String C_SQLCatListQuery = 
		"SELECT *\n" + 
		"FROM Cats C\n" +
		"WHERE C.%s LIKE \"%s%%\";\n";
	
	public AnimalDatabaseSQLite(ISQLiteWrapper sqliteWrapper)
	{
		_sqlite = sqliteWrapper;
		_sqlite.setConnection(getDefaultDatabaseConnectionString());
		
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
		
		List<Cat> catList = new LinkedList<Cat>();
		ResultSet results = _sqlite.executeQuery(query);
		
		if(results == null)	return catList;

		//TODO:Martial resultSet data into a real list of cats
		
		return null;
	}
	
	@Override
	public Cat getSingleCat(String catID) 
	{
		// TODO Auto-generated method stub
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
		
		return String.format(C_SQLCatListQuery, filterColumn, _searchFilter);
	}
	
	private String getDefaultDatabaseConnectionString()
	{
		String systemPath;
		String slash;
		
		String OS = System.getProperty("os.name").toLowerCase();
		
	    if (OS.contains("win"))
	    {
	    	systemPath = System.getenv("APPDATA");
	    	slash = "\\";
	    }
	    else
	    {
	    	systemPath = System.getProperty("user.dir");
	    	slash = "/";
	    }
	    
	    return "jdbc:sqlite:" + systemPath + slash + "NewBeginningsAnimalRescue" + slash + "cats.db";
	}
}