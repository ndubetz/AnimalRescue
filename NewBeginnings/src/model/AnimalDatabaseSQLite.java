package model;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class AnimalDatabaseSQLite implements IAnimalDatabase
{
	private SearchFilterType _filterType;
	private String _searchFilter;
	private ISQLiteWrapper _sqlite;
	
	public AnimalDatabaseSQLite(ISQLiteWrapper sqliteWrapper)
	{
		_sqlite = sqliteWrapper;
		_sqlite.setConnection(getDefaultDatabaseConnectionString());
		
		//For now, I'm just creating the database if need be.
		//Eventually, we may want to compare against a version
		//number to know how to upgrade an existing database
		//to accommodate schema changes.
		_sqlite.initializeDatabase(SQLCodeConstants.databaseInit());
		
		_filterType = SearchFilterType.Name;
		_searchFilter = "";
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
		String query = buildGeneralCatSearchQuery();
		
		List<Cat> catList = new LinkedList<Cat>();
		ResultSet results = _sqlite.executeQuery(query);
		
		if(results == null)	return catList;

		try 
		{
			while(results.next())
			{
				Cat newCat = createCatFromSQLResult(results);
				if (newCat != null) catList.add(newCat);
			}
		} 
		catch (SQLException e) 
		{
			//TODO: Not sure why this would ever throw,
			//it would seem by the design of the whole
			//thing that next() should just return
			//false if any internal errors occur
		}
		
		return catList;
	}
	
	@Override
	public Cat getSingleCat(String catID) 
	{
		String query = String.format(SQLCodeConstants.C_SpecificCatByID, catID);
		ResultSet result = _sqlite.executeQuery(query);
		
		if(result == null) return null;
		
		try 
		{
			result.next();
			return createCatFromSQLResult(result);
		} 
		catch (SQLException e) 
		{
			return null;
		}
		
	}
	
	@Override
	public void addNewCat(Cat cat) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCat(Cat cat) 
	{
		// TODO Auto-generated method stub
		
	}
	
	private Cat createCatFromSQLResult(ResultSet result)
	{
		try {
			return new Cat
					(
							result.getString("id"),
							result.getString("name"),
							result.getInt("age"),
							result.getString("gender"),
							result.getString("breed"),
							result.getString("hairColor"),
							//currently storing fixed status as an integer, need to convert
							(result.getInt("isFixed") == 0),
							//not sure how we want to serialize/deserialize these
							//may change to SQL "BLOB" type
							new GregorianCalendar(),
							new GregorianCalendar()
					);
		} 
		catch (SQLException e) 
		{
			//if we get a corrupted cat, just return null
			return null;
		}
	}
	
	private String buildGeneralCatSearchQuery()
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
		
		return String.format(SQLCodeConstants.C_GeneralCatSearch, filterColumn, _searchFilter);
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