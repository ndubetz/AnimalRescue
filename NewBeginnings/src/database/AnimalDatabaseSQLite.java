package database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;

public class AnimalDatabaseSQLite implements IAnimalDatabase
{
	private ISQLiteWrapper _sqlite;
	
	public AnimalDatabaseSQLite(ISQLiteWrapper sqliteWrapper)
	{
		_sqlite = sqliteWrapper;
		
		//For now, I'm just creating the database if need be.
		//Eventually, we may want to compare against a version
		//number to know how to upgrade an existing database
		//to accommodate schema changes.
		_sqlite.executeMultipleUpdate(SQLiteDatabaseInterface.databaseInit());
	}
	
	@Override
	public List<Cat> getFilteredCats(SearchFilterType filterType, String filter)
	{
		String query = String.format(SQLiteDatabaseInterface.C_GeneralCatSearch, filterType, filter);
		
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
			
			_sqlite.closeEverything();
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
		String query = String.format(SQLiteDatabaseInterface.C_SpecificCatByID, catID);
		ResultSet result = _sqlite.executeQuery(query);
		
		if(result == null) return Cat.emptyCat();
		
		try 
		{
			result.next();
			Cat cat = createCatFromSQLResult(result);
			_sqlite.closeEverything();			
			return cat;
		} 
		catch (SQLException e) 
		{
			return Cat.emptyCat();
			
		}
	}
	
	@Override
	public void addNewCat(Cat cat) 
	{
		String statement = String.format(SQLiteDatabaseInterface.insertNewCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getAge(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.isFixed()? 0 : 1,
				cat.getArrivalDate().toString(),
				cat.getExpectedDepartureDate().toString()
				);
		
		_sqlite.executeUpdate(statement);
	}

	@Override
	public void updateCat(Cat cat) 
	{
		String statement = String.format(SQLiteDatabaseInterface.updateExistingCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getAge(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.isFixed()? 0 : 1, //convert boolean to integer
				cat.getArrivalDate().toString(),
				cat.getExpectedDepartureDate().toString(),
				cat.getID()
				);
		
		_sqlite.executeUpdate(statement);
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
			//if we get a corrupted cat, just return the empty Cat
			return Cat.emptyCat();
		}
	}
}