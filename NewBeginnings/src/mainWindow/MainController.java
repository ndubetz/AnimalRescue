package mainWindow;

import hostView.AnimalInfoView;
import hostView.AnimalInfoViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Cat;

public class MainController implements ActionListener {
	MainView view;
	MainModel model;
	private final AnimalInfoViewController animalInfoViewController;

	public MainController() {
		System.out.println("Controller");
		this.animalInfoViewController = new AnimalInfoViewController(this);
	}

	// all listeners for the MainView class go here
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.view.getAddNewCatButton()) {
			AnimalInfoView animalView = this.animalInfoViewController
					.buildView(Cat.emptyCat());

			this.model.newAnimal();

			this.view.changePanelOnScrollPane(animalView);
		}
	}

	public void addModel(MainModel model) {
		System.out.println("adding model");
		this.model = model;
	}

	public void addView(MainView view) {
		System.out.println("adding view");
		this.view = view;
	}

	public MainView getView() {
		return this.view;
	}

	public void reloadPreviousPanel() {
		this.view.changePanelOnScrollPane(this.view.getPreviousPanel());
	}

}
