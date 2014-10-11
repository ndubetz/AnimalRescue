package model;

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
	 * Sets the path to the current database
	 * 
	 * @param connectionString the path to the database
	 */
	public void setConnection(String connectionString);
}
