package mainWindow;

import java.util.ArrayList;

public class MainModel extends java.util.Observable{
	ArrayList<Cat> catsList = new ArrayList<Cat>();
	
	public MainModel(){
		System.out.println("model");
	}
	
	public void newAnimal(){
		System.out.println("New Animal");
	}
}
