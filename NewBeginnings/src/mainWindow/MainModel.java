package mainWindow;

import java.util.ArrayList;

import database.FakeAnimalDatabase;
import database.IAnimalDatabase;
import model.Cat;

public class MainModel extends java.util.Observable{
	public static final String C_DefaultSearchText = "Search For A Cat";
	
	ArrayList<Cat> catsList = new ArrayList<Cat>();
	
	private IAnimalDatabase animalDatabase;
	
	public MainModel(){
		IAnimalDatabase database = null;
		try {
			
			//once we are done with needing the fake database, 
			//our construction will be:
			/*
			ISQLiteWrapper sqlite = new SQLiteWrapper(
					SQLiteDatabaseInterface.defaultDatabasePath());
			IAnimalDatabase database = new AnimalDatabaseSQLite(sqlite);
			*/
			
			//but for now:
			database = new FakeAnimalDatabase();
			
		} catch(Exception e) {
			// TODO Iguess if we can't connect to the database
			//we'll need some kind of popup or something.
		}
		this.animalDatabase = database;
	}
	
	public void newAnimal(){
	}

	public IAnimalDatabase getAnimalDatabase() {
		return animalDatabase;
	}

	public void setAnimalDatabase(IAnimalDatabase animalDatabase) {
		this.animalDatabase = animalDatabase;
	}
}
