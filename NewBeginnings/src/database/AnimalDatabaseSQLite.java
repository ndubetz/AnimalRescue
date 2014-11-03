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
				booleanToInt(cat.isFixed()),
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				booleanToInt(cat.isRabies()),
				booleanToInt(cat.isFeLeuk()),
				booleanToInt(cat.isDistemper()),
				medicalHistoryToFlatString(cat.getMedicalHistory())
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
				booleanToInt(cat.isFixed()),
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				booleanToInt(cat.isRabies()),
				booleanToInt(cat.isFeLeuk()),
				booleanToInt(cat.isDistemper()),
				medicalHistoryToFlatString(cat.getMedicalHistory()),
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
		
		_sqlite.closeEverything();
		
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
							intToBoolean(result.getInt("isFixed")),
							longToCalander(result.getLong("arrivalDate")),
							longToCalander(result.getLong("departureDate")),
							intToBoolean(result.getInt("isRabies")),
							intToBoolean(result.getInt("isFeLeuk")),
							intToBoolean(result.getInt("isDistemper")),
							flatStringToMedicalHistory(result.getString("medicalHistory"))
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
	
	private boolean intToBoolean(int value)
	{
		return (value == 0);
	}
	
	private int booleanToInt(boolean value)
	{
		return value? 1: 0;
	}
	
	private final String C_MedicalItemSeperator = "%~`~%";
	private String medicalHistoryToFlatString(String[] medicalHistory)
	{
		String flatString = "";
		
		boolean setFirst = false;
		for(String item : medicalHistory)
		{
			if(!setFirst)
			{
				setFirst = true;
			}
			else
			{
				flatString += C_MedicalItemSeperator;
			}
			
			flatString += item;
		}
		
		return flatString;
	}
	
	private String[] flatStringToMedicalHistory(String flatString)
	{		
		String[] history = flatString.split(C_MedicalItemSeperator);
		
		return (history != null)? history : new String[]{};
	}
}