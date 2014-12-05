package mainWindow;

import database.*;

public class StartingClass {
	public StartingClass() {
		MainModel model = new MainModel();
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
		
		model.setAnimalDatabase(database);
		
		MainView view = new MainView();

		MainController controller = new MainController(model, view);

		view.addController(controller);
	}
}
