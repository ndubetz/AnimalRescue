package mainWindow;

import java.util.ArrayList;

import database.IAnimalDatabase;
import model.Cat;

public class MainModel extends java.util.Observable{
	public static final String C_DefaultSearchText = "Search For A Cat";
	
	ArrayList<Cat> catsList = new ArrayList<Cat>();
	
	private IAnimalDatabase animalDatabase;
	
	public MainModel(){
		System.out.println("model");
	}
	
	public void newAnimal(){
		System.out.println("New Animal");
	}

	public IAnimalDatabase getAnimalDatabase() {
		return animalDatabase;
	}

	public void setAnimalDatabase(IAnimalDatabase animalDatabase) {
		this.animalDatabase = animalDatabase;
	}
}
