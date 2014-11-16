package mainWindow;

import formView.FormViewController;
import formView.FormsView;
import hostView.AnimalInfoView;
import hostView.AnimalInfoViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import database.SearchFilterType;
import searchResults.SearchResultItemViewController;
import searchResults.SearchResultsView;
import searchResults.SearchResultsViewController;
import model.Cat;

public class MainController implements ActionListener {
	MainView view;
	MainModel model;
	private final AnimalInfoViewController animalInfoViewController;
	private final FormViewController formViewController;
	private final SearchResultsViewController searchResultsViewController;
	
	public MainController() {
		System.out.println("Controller");
		this.animalInfoViewController = new AnimalInfoViewController(this);
		this.formViewController = new FormViewController(this);
		searchResultsViewController = new SearchResultsViewController(this);
	}

	// all listeners for the MainView class go here
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.view.getAddNewCatButton()) {
			AnimalInfoView animalView = this.animalInfoViewController
					.buildView(Cat.emptyCat());

			this.model.newAnimal();

			this.view.changePanelOnScrollPane(animalView);
		} else if (e.getSource() == this.view.getExitActionMenuItem()) {
			System.out.println("close");
			System.exit(0);
		} else if (e.getSource() == this.view.getDataBaseActionMenuItem()) {
			System.out.println("database View");
			this.view.buildDataBaseView();
			this.view.addController(this);
		} else if (e.getSource() == this.view.getFormActionMenuItem()) {
			System.out.println("forms view");
			FormsView formView = this.formViewController.buildView();
			this.view.buildFormsView(formView);
			this.view.addController(this);
		} else if (e.getSource() == this.view.getSearchButton()) {			
			SearchResultsView resultsView = this.searchResultsViewController.buildView(
					this.model.getAnimalDatabase()
					.getFilteredCats(
							SearchFilterType.Name, 
							this.view.getSearchText()));
			
			this.view.changePanelOnScrollPane(resultsView);
		} else if(e.getSource() == this.searchResultsViewController){
			AnimalInfoView animalView = this.animalInfoViewController
					.buildView(searchResultsViewController.getSelectedCat());

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
