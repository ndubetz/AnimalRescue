package database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
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
				cat.getBirthdate().getTimeInMillis(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.isFixed()? 0 : 1,
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis()
				);
		
		_sqlite.executeUpdate(statement);
	}

	@Override
	public void updateCat(Cat cat) 
	{
		String statement = String.format(SQLiteDatabaseInterface.updateExistingCatSQL(), 
				cat.getID(),
				cat.getName(),
				cat.getBirthdate().getTimeInMillis(),
				cat.getGender(),
				cat.getBreed(),
				cat.getHairColor(),
				cat.isFixed()? 0 : 1, //convert boolean to integer
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				cat.getID()
				);
		
		_sqlite.executeUpdate(statement);
	}
	
	@Override
	public String getSuggestedNextID() 
	{
		int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 2000;
		String suggestedID;
		String defaultID = "NB-" + currentYear + "-001";
		
		ResultSet result = _sqlite.executeQuery(SQLiteDatabaseInterface.C_LatestCatID);
		
		if(result == null)
		{
			suggestedID = defaultID;
		}
		else
		{
			try 
			{
				result.next();
				String latestID = result.getString("id");
				String[] idParts = latestID.split("-");
				
				int latestYear = Integer.parseInt(idParts[1]);
				if(currentYear > latestYear)
				{
					suggestedID = defaultID;
				}
				else
				{
					int latestNumber = Integer.parseInt(idParts[2]);
					suggestedID = "NB-" + currentYear + "-" + (latestNumber + 1);
				}
			} 
			catch (SQLException e) 
			{
				suggestedID = defaultID;
			}
		}
		
		return suggestedID;
	}
	
	private Cat createCatFromSQLResult(ResultSet result)
	{
		try {
			return new Cat
					(
							result.getString("id"),
							result.getString("name"),
							longToCalander(result.getLong("birthdate")),
							result.getString("gender"),
							result.getString("breed"),
							result.getString("hairColor"),
							//currently storing fixed status as an integer, need to convert
							(result.getInt("isFixed") == 0),
							longToCalander(result.getLong("arrivalDate")),
							longToCalander(result.getLong("departureDate"))
					);
		} 
		catch (SQLException e) 
		{
			//if we get a corrupted cat, just return the empty Cat
			return Cat.emptyCat();
		}
	}
	
	private Calendar longToCalander(long timeInMs)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timeInMs);
		
		return calendar;
	}
}