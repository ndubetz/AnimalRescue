package fakes;

import java.sql.ResultSet;
import model.ISQLiteWrapper;
import java.util.LinkedList;
import java.util.List;

public class FakeSQLiteWrapper implements ISQLiteWrapper
{
	private List<String> _executeQueryCallHistory;
	private List<String> _executeUpdateCallHistory;
	private List<String> _setConectionCallHistory;
	private List<String[]> _initializeDatabaseCallHistory;
	
	public FakeSQLiteWrapper()
	{
		_executeQueryCallHistory = new LinkedList<String>();
		_executeUpdateCallHistory = new LinkedList<String>();
		_setConectionCallHistory = new LinkedList<String>();
		_initializeDatabaseCallHistory = new LinkedList<String[]>();
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
	public void setConnection(String connectionString) 
	{
		_setConectionCallHistory.add(connectionString);
	}

	@Override
	public void initializeDatabase(String[] initializationScript) 
	{
		_initializeDatabaseCallHistory.add(initializationScript);
	}
	
	public List<String> getExecuteQueryCallHistory() 
	{
		return _executeQueryCallHistory;
	}

	public List<String> getExecuteUpdateCallHistory() 
	{
		return _executeUpdateCallHistory;
	}

	public List<String> getSetConectionCallHistory() 
	{
		return _setConectionCallHistory;
	}

	public List<String[]> getInitializeDatabaseCallHistory() 
	{
		return _initializeDatabaseCallHistory;
	}
}
