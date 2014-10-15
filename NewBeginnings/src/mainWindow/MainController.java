package mainWindow;

import hostView.AnimalInfoView;
import hostView.AnimalInfoViewBuilder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Cat;

public class MainController implements ActionListener{
	MainView view;
	MainModel model;
	
	public MainController() {
		System.out.println("Controller");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AnimalInfoView animalView = AnimalInfoViewBuilder.singleton().build(Cat.emptyCat());
		//this animalView needs to be plumbed up to the MainView's JScrollPane 
		//so it can replace the current view in the JScrollPane
		model.newAnimal();	
	}

	public void addModel(MainModel model){
		System.out.println("adding model");
		this.model = model;
	}
	
	public void addView(MainView view){
		System.out.println("adding view");
		this.view = view;
	}
	
}
