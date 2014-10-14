package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteWrapper implements ISQLiteWrapper 
{
	String _connectionString;
	Connection _conn;
	
	public SQLiteWrapper(String connectionString) throws ClassNotFoundException 
	{
		Class.forName("org.sqlite.JDBC");
		_connectionString = connectionString;
	}
	
	@Override
	public ResultSet executeQuery(String SQLQuery) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeUpdate(String SQLCommand) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeDatabase(String[] initializationScript) 
	{
		for(String command : initializationScript)
		{
			
		}
	}

	private Statement setConnetion(int timeout) throws SQLException
	{
		if(_conn != null)
		{
			_conn.close();
		}
		
		_conn = DriverManager.getConnection(_connectionString);
		Statement statement = _conn.createStatement();
		statement.setQueryTimeout(timeout);
		
		return statement;
	}
}
