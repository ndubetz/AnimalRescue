package database;

import java.util.ArrayList;

/**
 * Class to hold some static constants to avoid cluttering up
 * other classes
 */
public class SQLiteDatabaseInterface 
{
	//NOTE The idea behind this is that any change we make to the Cat constructor
	//will also be updated here
	/**
	 * Schema for the Cats table
	 */
	public static final String[][] C_CatsColumns = 
		{
			{"id", "TEXT"},
			{"name", "TEXT"},
			{"birthdate", "INTEGER"},
			{"gender", "TEXT"},
			{"breed", "TEXT"},
			{"hairColor", "TEXT"},
			{"fixed", "TEXT"},
			{"arrivalDate", "INTEGER"},
			{"departureDate", "INTEGER"},
			{"rabies", "TEXT"},
			{"feLeuk", "TEXT"},
			{"distemper", "TEXT"},
			{"medicalHistory", "TEXT"}
		};
	
	/**
	 * Schema for the BehaviourInformation table
	 */
	public static final String[][] C_BehaviourInformationColumns = 
		{
			{"id", "TEXT"},
			{"useScratchingPost", "INTEGER"},
			{"scratchingPreferences", "TEXT"},
			{"activityLevel", "TEXT"},
			{"sensitiveAreas", "TEXT"},
			{"whatMakesItNervous", "TEXT"},
			{"preferredCompany", "TEXT"},
			{"overallDescription", "TEXT"}
		};
	
	public static final String[][] C_FeedingInformationColumns = 
		{
			{"id", "TEXT"},
			{"foodType", "TEXT"},
			{"brands", "TEXT"},
			{"specialDiet", "TEXT"},
			{"eatingFrequency", "TEXT"}
		};
	
	public static final String[][]C_GuardianInformationColumns = 
		{
			{"id", "TEXT"},
			{"guardianName", "TEXT"},
			{"guardianPhoneNumber", "TEXT"},
			{"guardianAddress", "TEXT"},
			{"guardianEmail", "TEXT"},
			{"durationOfOwnership", "TEXT"},
			{"reasonsForGivingUp", "TEXT"},
			{"howWasTheCatRaised", "TEXT"},
			{"whereWasTheCatKept", "TEXT"}
		};
	
	public static final String[][] C_LitterBoxInfoColumns = 
		{
			{"id", "TEXT"}
		};
	
	/**
	 * Returns the list of commands to run to initialize
	 * the database
	 */
	public static String[] databaseInit() 
	{
		ArrayList<String> script = new ArrayList<String>();
		//initialization of the database properties table
		script.add("DROP TABLE IF EXISTS DBProperties;");
		script.add("CREATE TABLE DBProperties(propKey, propValue);");
				
		//we should be incrementing this number as we go to help check for changes
		script.add("INSERT INTO DBProperties VALUES ('dbVersion', 0);");
		
		script.add(createTableSQL("Cats", C_CatsColumns));
		script.add(createTableSQL("BehaviourInformation", C_BehaviourInformationColumns));
		script.add(createTableSQL("FeedingInformation", C_FeedingInformationColumns));
		script.add(createTableSQL("GuardianInformation", C_GuardianInformationColumns));		
		
		return script.toArray(new String[script.size()]);
	}

//**********Cats Table SQL Commands************
	
	/**
	 * Formatable query to get the list of cats
	 * that match the search pattern
	 */
	public static final String C_GeneralCatSearch = 
			"SELECT *\n" + 
			"FROM Cats C\n" +
			"WHERE C.%s LIKE '%s%%';\n";
	
	/**
	 * Formattable query to get a specific cat
	 */
	public static final String C_SpecificCatByID = 
			"SELECT *\n" + 
			"FROM Cats C\n" + 
			"WHERE C.id='%s';\n";
	
	/**
	 * Query to get the highest ID number in the database
	 */
	public static final String C_LatestCatID = 
			"SELECT max(C.id) AS id\n" +
			"FROM Cats C;";
	
	/**
	 * @return the SQL command for inserting a new cat
	 */
	public static String insertNewCatSQL() 
	{
		return insertNewSomething("Cats", C_CatsColumns);
	}
	
	/**
	 * Generates a formatable query to update an 
	 * existing cat
	 */
	public static String updateExistingCatSQL() 
	{
		return updateExistingSomething("Cats", C_CatsColumns);
	}
	
//*********BehaviourInformation Table SQL Commands****************
	
	/**
	 * Formattable query to get a cat's bahviour information
	 */
	public static final String C_BehaviourInformationByID = 
			"SELECT *\n" + 
			"FROM BehaviourInformation B\n" + 
			"WHERE B.id='%s';\n";

	/**
	 * @return the SQL command for inserting a new BehaviourInformation
	 */
	public static String insertNewBehaviourInformationSQL() 
	{
		return insertNewSomething("BehaviourInformation", C_BehaviourInformationColumns);
	}
	
	/**
	 * Generates a formatable query to update an 
	 * existing BehaviourInformation
	 */
	public static String updateExistingBehaviourInformationSQL() 
	{
		return updateExistingSomething("BehaviourInformation", C_BehaviourInformationColumns);
	}
	
//*********GuardianInformation Table SQL Commands****************
	/**
	 * Formattable query to get a cat's Guardian information
	 */
	public static final String C_GuardianInformationByID = 
			"SELECT *\n" + 
			"FROM GuardianInformation B\n" + 
			"WHERE B.id='%s';\n";

	/**
	 * @return the SQL command for inserting a new GuardianInformation
	 */
	public static String insertNewGuardianInformationSQL() 
	{
		return insertNewSomething("GuardianInformation", C_GuardianInformationColumns);
	}
		
	/**
	 * Generates a formatable query to update an 
	 * existing GuardianInformation
	 */
	public static String updateExistingGuardianInformationSQL() 
	{
		return updateExistingSomething("GuardianInformation", C_GuardianInformationColumns);
	}

//*******Abstracted SQL Commands*******
	private static String createTableSQL(String tableName, String[][] columns)
	{
		String tableSchemaLine = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
		
		tableSchemaLine += columns[0][0];
		for(int i = 1; i < columns.length; i++)
		{
			tableSchemaLine += ", " + columns[i][0];
		}
		
		tableSchemaLine += ");";
		
		return tableSchemaLine;
	}
	
	private static String insertNewSomething(String tableName, String[][] columns)
	{
		String statement = "INSERT INTO " + tableName + " VALUES(";
		
		statement += formatType(columns[0]);
		for(int i = 1; i < columns.length; i++)
		{
			statement += ", " + formatType(columns[i]);
		}
		
		statement += ");";
		
		return statement;
	}
	
	private static String updateExistingSomething(String tableName, String[][] columns)
	{
		String statement = 
				"UPDATE " + tableName + "\n" + 
				"SET ";
		
		String idSet = fullFormat(columns[0]);		
		statement += idSet;
		for(int i = 1; i < columns.length; i++)
		{
			statement += ", " + fullFormat(columns[i]);
		}
		
		statement += "\nWHERE " + idSet + ";\n";
		
		return statement;
	}
	
//*******General Database Stuff************	
	/**
	 * Determines the default path to the database file to open
	 */
	public static String defaultDatabasePath()
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
	    
	    return "jdbc:sqlite:" + systemPath + slash + "NewBeginningsAnimalRescueCats.db";
	}
	
	private static String formatType(String[] item)
	{
		if(item[1] == "INTEGER")
		{
			return "%d";
		}
		else
		{
			return "'%s'";
		}
	}
	
	private static String fullFormat(String[] item)
	{
		return item[0] + "=" + formatType(item);
	}
}