package fakes;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import database.ISQLiteWrapper;

public class FakeSQLiteWrapper implements ISQLiteWrapper
{
	private List<String> _executeQueryCallHistory;
	private List<String> _executeUpdateCallHistory;
	private List<String[]> _initializeDatabaseCallHistory;
	
	private int _closeEverythingCallCount;
	
	public FakeSQLiteWrapper()
	{
		_executeQueryCallHistory = new LinkedList<String>();
		_executeUpdateCallHistory = new LinkedList<String>();
		_initializeDatabaseCallHistory = new LinkedList<String[]>();
		
		_closeEverythingCallCount = 0;
	}
	
	@Override
	public ResultSet executeQuery(String SQLQuery) 
	{
		_executeQueryCallHistory.add(SQLQuery);
		return null;
	}

	@Override
	public void executeUpdate(String SQLCommand) 
	{
		_executeUpdateCallHistory.add(SQLCommand);
	}

	@Override
	public void executeMultipleUpdate(String[] initializationScript) 
	{
		_initializeDatabaseCallHistory.add(initializationScript);
	}
	
	@Override
	public void closeEverything()
	{
		_closeEverythingCallCount++;	
	}
	
	public List<String> getExecuteQueryCallHistory() 
	{
		return _executeQueryCallHistory;
	}

	public List<String> getExecuteUpdateCallHistory() 
	{
		return _executeUpdateCallHistory;
	}

	public List<String[]> getInitializeDatabaseCallHistory() 
	{
		return _initializeDatabaseCallHistory;
	}
	
	public int getCloseEverythingCallCount()
	{
		return _closeEverythingCallCount;
	}
}
