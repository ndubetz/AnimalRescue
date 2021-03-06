package database;

import java.sql.ResultSet;

/**
 * Wrapper interface for the SQLite library
 * (using this to let us test without a real
 * database)
 */
public interface ISQLiteWrapper 
{
	/**
	 * Connects to the database and gets the
	 * results of the query
	 * 
	 * Be sure to call closeEverything()when finished
	 * 
	 * @param SQLQuery the SQL query to execute
	 * @return the set of results from the query
	 */
	public ResultSet executeQuery(String SQLQuery);
	
	/**
	 * Connects to the database and performs
	 * the requested update
	 * 
	 * @param SQLCommand the SQL update command
	 */
	public void executeUpdate(String SQLCommand);
	
	/**
	 * Runs the specified sequence of commands on the database.
	 * SQLite will automatically create the database file at
	 * the current connection path if it is not already there.
	 * The initialization commands should contain a set of
	 * "CREATE TABLE IF NOT EXISTS" commands, as well as
	 * insertion of any special properties to insert.
	 * 
	 * @param initializationScript
	 */
	public void executeMultipleUpdate(String[] initializationScript);
	
	/**
	 * Closes the current connection and statement
	 * used when results need to be passed back
	 * out of another method before closure
	 */
	public void closeEverything();
}
