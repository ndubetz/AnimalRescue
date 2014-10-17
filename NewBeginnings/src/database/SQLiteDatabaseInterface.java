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
	public static final String[][] C_CatsColumns = 
		{
			{"id", "TEXT"},
			{"name", "TEXT"},
			{"age", "INTEGER"},
			{"gender", "TEXT"},
			{"breed", "TEXT"},
			{"hairColor", "TEXT"},
			{"isFixed", "INTEGER"},
			{"arrivalDate", "TEXT"},
			{"departureDate", "TEXT"}
		};
	
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

	public static final String C_GeneralCatSearch = 
			"SELECT *\n" + 
			"FROM Cats C\n" +
			"WHERE C.%s LIKE '%s%%';\n";
	
	public static final String C_SpecificCatByID = 
			"SELECT *\n" + 
			"FROM Cats C\n" + 
			"WHERE C.id='%s';\n";
	
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