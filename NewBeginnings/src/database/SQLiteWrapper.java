package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteWrapper implements ISQLiteWrapper 
{
	String _connectionString;
	Connection _currentConn;
	Statement _currentStatement;
	
	public SQLiteWrapper(String connectionString) throws ClassNotFoundException 
	{
		Class.forName("org.sqlite.JDBC");
		_connectionString = connectionString;
	}
	
	@Override
	public ResultSet executeQuery(String SQLQuery) 
	{
		try 
		{
			setConnetion(30);
			
			ResultSet results = _currentStatement.executeQuery(SQLQuery);
			return results;
		} 
		catch (SQLException e) 
		{
			return null;
		}
	}

	@Override
	public void executeUpdate(String SQLCommand) 
	{
		try 
		{
			setConnetion(30);
			_currentStatement.executeUpdate(SQLCommand);
			
			_currentStatement.close();
			_currentConn.close();
		} 
		catch (SQLException e) 
		{
			
		}
	}

	@Override
	public void executeMultipleUpdate(String[] script) 
	{
		try 
		{
			setConnetion(30);
			
			for(String command : script)
			{
				_currentStatement.executeUpdate(command);
			}
			
			_currentStatement.close();
			_currentConn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void closeEverything() 
	{
		try 
		{
			if(_currentConn != null && !_currentConn.isClosed())
			{
				_currentConn.close();
			}
			
			if(_currentStatement != null && !_currentStatement.isClosed())
			{
				_currentStatement.close();
			}
		}
		catch (SQLException e) 
		{
		}
	}
	
	private void setConnetion(int timeout) throws SQLException
	{
		closeEverything();
		
		_currentConn = DriverManager.getConnection(_connectionString);
		_currentStatement = _currentConn.createStatement();
		_currentStatement.setQueryTimeout(timeout);
	}
}
