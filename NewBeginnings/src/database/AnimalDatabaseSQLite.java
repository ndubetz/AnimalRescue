package database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.Cat;
import model.catHistory.*;

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
				cat.getFixed(),
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				cat.getRabies(),
				cat.getFeLeuk(),
				cat.getDistemper(),
				stringArrayToFlatString(cat.getMedicalHistory()),
				cat.getCatPictureFilePath(),
				cat.getCommentsAboutCat()
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
				cat.getFixed(),
				cat.getArrivalDate().getTimeInMillis(),
				cat.getExpectedDepartureDate().getTimeInMillis(),
				cat.getRabies(),
				cat.getFeLeuk(),
				cat.getDistemper(),
				stringArrayToFlatString(cat.getMedicalHistory()),
				cat.getCatPictureFilePath(),
				cat.getCommentsAboutCat(),
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
					int latestNumber = Integer.parseInt(idParts[2]) + 1;
					String numberString = String.format("%d", latestNumber);
					if(latestNumber < 100)
					{
						numberString = "0" + numberString;
						if(latestNumber < 10)
						{
							numberString = "0" + numberString;
						}
					}
					suggestedID = "NB-" + currentYear + "-" + numberString;
				}
			} 
			catch (Exception e) 
			{
				suggestedID = defaultID;
			}
		}
		
		_sqlite.closeEverything();
		
		return suggestedID;
	}
	
	@Override
	public CatHistory getCatHistory(String catID) {
		CatHistory history = null;
		
		//get query results for all the components of the CatHistory
		String behaviourQuery = String.format(SQLiteDatabaseInterface.C_BehaviourInformationByID, catID);
		ResultSet behaviourResult = _sqlite.executeQuery(behaviourQuery);
		
		String feedingQuery = String.format(SQLiteDatabaseInterface.C_FeedingInformationByID, catID);
		ResultSet feedingResult = _sqlite.executeQuery(feedingQuery);
		
		String guardianQuery = String.format(SQLiteDatabaseInterface.C_GuardianInformationByID, catID);
		ResultSet guardianResult = _sqlite.executeQuery(guardianQuery);
		
		String litterQuery = String.format(SQLiteDatabaseInterface.C_LitterBoxInfoByID, catID);
		ResultSet litterResult = _sqlite.executeQuery(litterQuery);
		
		String vetQuery = String.format(SQLiteDatabaseInterface.C_VeterinarianCareHistoryByID, catID);
		ResultSet vetResult = _sqlite.executeQuery(vetQuery);
		
		try 
		{
			//create the objects from their SQL results
			behaviourResult.next();
			BehaviourInformation behaviour = (behaviourResult == null)? null : createBahaviourInformationFromSQL(behaviourResult);
			
			feedingResult.next();
			FeedingInformation feeding = (feedingResult == null)? null : createFeedingInformationFromSQL(feedingResult);
			
			guardianResult.next();
			GuardianInformation guardian = (guardianResult == null)? null : createGuardianInformationFromSQL(guardianResult);
			
			litterResult.next();
			LitterBoxInfo litter = (litterResult == null)? null : createLitterBoxInfoFromSQL(litterResult);
			
			vetResult.next();
			VeterinarianCareHistory vet = (vetResult == null)? null : createVeterinarianCareHistoryFromSQL(vetResult);
			
			//now consolidate into the final object
			history = new CatHistory
					(
							feeding,
							behaviour,
							vet,
							litter,
							guardian,
							""
					);
			
			_sqlite.closeEverything();			
		} 
		catch (SQLException e) 
		{
			return null;
		}
		return history;
	}

	@Override
	public void insertCatHistory(CatHistory catHistory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCatHistory(CatHistory catHistory) {
		// TODO Auto-generated method stub
		
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
							result.getString("fixed"),
							longToCalander(result.getLong("arrivalDate")),
							longToCalander(result.getLong("departureDate")),
							result.getString("rabies"),
							result.getString("feLeuk"),
							result.getString("distemper"),
							flatStringToStringArray(result.getString("medicalHistory")), 
							result.getString("catPictureFilePath"),
							result.getString("comments")
					);
		} catch (SQLException e) 
		{
			//if we get a corrupted cat, just return the empty Cat
			return Cat.emptyCat();
		}
	}
	
	private BehaviourInformation createBahaviourInformationFromSQL(ResultSet result)
	{
		try {
			return new BehaviourInformation
					(
							intToBoolean(result.getInt("useScratchingPost")),
							new LinkedList<String>(Arrays.asList(flatStringToStringArray(result.getString("scratchingPreferences")))),
							result.getString("activityLevel"),
							result.getString("sensitiveAreas"),
							new LinkedList<String>(Arrays.asList(flatStringToStringArray(result.getString("whatMakesItNervous")))),
							new LinkedList<String>(Arrays.asList(flatStringToStringArray(result.getString("preferredCompany")))),
							new LinkedList<String>(Arrays.asList(flatStringToStringArray(result.getString("overallDescription"))))
					);
		} catch (SQLException e)
		{
			return null;
		}
	}
	
	private FeedingInformation createFeedingInformationFromSQL(ResultSet result)
	{
		try {
			return new FeedingInformation
					(
							result.getString("foodType"),
							result.getString("brands"),
							result.getString("specialDiet"),
							result.getString("eatingFrequency")
					);
		} catch (SQLException e)
		{
			return null;
		}
	}
	
	private GuardianInformation createGuardianInformationFromSQL(ResultSet result)
	{
		try {
			return new GuardianInformation
					(
							result.getString("guardianName"),
							result.getString("guardianPhoneNumber"),
							result.getString("guardianAddress"),
							result.getString("guardianEmail"),
							result.getString("durationOfOwnership"),
							new LinkedList<String>(Arrays.asList(flatStringToStringArray(result.getString("reasonsForGivingUp")))),
							result.getString("howWasTheCatRaised"),
							result.getString("whereWasTheCatKept"),
							result.getString("howDoesItGetOutside")
					);
		} catch (SQLException e)
		{
			return null;
		}
	}
	
	private LitterBoxInfo createLitterBoxInfoFromSQL(ResultSet result)
	{
		try {
			return new LitterBoxInfo
					(
							intToBoolean(result.getInt("isLitterBoxTrained")),
							result.getString("ifNoPleaseExplain"),
							result.getInt("numberOfCatsInHome"),
							result.getInt("numberOfLitterBoxesInHome"),
							result.getString("litterBoxType"),
							new LinkedList<String>(Arrays.asList(flatStringToStringArray(result.getString("litterTypes")))),
							intToBoolean(result.getInt("hasTheCatShatOutsideTheBox")),
							result.getString("ifYesThenWhere"),
							result.getString("howOftenWereTheAccidents"),
							result.getString("recentChangesToRoutine"),
							result.getString("hasSeenTheVetForHavingAccidents"),
							result.getString("wasAccidentProblemSolved")
					);
		} catch (SQLException e)
		{
			return null;
		}
	}
	
	private VeterinarianCareHistory createVeterinarianCareHistoryFromSQL(ResultSet result)
	{
		try {
			return new VeterinarianCareHistory
					(
							intToBoolean(result.getInt("hasBeenSterilized")),
							intToBoolean(result.getInt("hasBeenVaccinatedThisYear")),
							intToBoolean(result.getInt("hasBeenTestedFiVLeuk")),
							result.getString("vetName"),
							result.getString("vetPhoneNumber")
					);
		} catch (SQLException e)
		{
			return null;
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
	/*
	private int booleanToInt(boolean value)
	{
		return value? 1: 0;
	}
	*/
	private final String C_StringArraySeperator = "%~`~%";
	private String stringArrayToFlatString(String[] medicalHistory)
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
				flatString += C_StringArraySeperator;
			}
			
			flatString += item;
		}
		
		return flatString;
	}
	
	private String[] flatStringToStringArray(String flatString)
	{		
		String[] history = flatString.split(C_StringArraySeperator);
		
		return (history != null)? history : new String[]{};
	}
}