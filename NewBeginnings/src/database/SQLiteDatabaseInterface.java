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
		
		String catsSchemaLine = "CREATE TABLE IF NOT EXISTS Cats(";
		
		catsSchemaLine += C_CatsColumns[0][0];
		for(int i = 1; i < C_CatsColumns.length; i++)
		{
			catsSchemaLine += ", " + C_CatsColumns[i][0];
		}
		
		catsSchemaLine += ");";
		script.add(catsSchemaLine);
		
		return script.toArray(new String[script.size()]);
	}

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
	
	public static String insertNewCatSQL() 
	{
		String statement = "INSERT INTO CATS VALUES(";
		
		statement += formatType(C_CatsColumns[0]);
		for(int i = 1; i < C_CatsColumns.length; i++)
		{
			statement += ", " + formatType(C_CatsColumns[i]);
		}
		
		statement += ");";
		
		return statement;
	}
	
	/**
	 * Generates a formatable query to update an 
	 * existing cat
	 */
	public static String updateExistingCatSQL() 
	{
		String statement = 
				"UPDATE Cats\n" + 
				"SET ";
		
		String idSet = fullFormat(C_CatsColumns[0]);		
		statement += idSet;
		for(int i = 1; i < C_CatsColumns.length; i++)
		{
			statement += ", " + fullFormat(C_CatsColumns[i]);
		}
		
		statement += "\nWHERE " + idSet + ";\n";
		
		return statement;
	}
	
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
