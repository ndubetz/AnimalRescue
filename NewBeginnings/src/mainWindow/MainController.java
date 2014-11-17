package mainWindow;

import formView.FormViewController;
import formView.FormsView;
import hostView.AnimalInfoView;
import hostView.AnimalInfoViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import model.Cat;
import searchResults.SearchResultsView;
import searchResults.SearchResultsViewController;
import database.SearchFilterType;

public class MainController implements ActionListener, FocusListener {
	MainView view;
	MainModel model;
	private final AnimalInfoViewController animalInfoViewController;
	private final FormViewController formViewController;
	private final SearchResultsViewController searchResultsViewController;

	public MainController() {
		System.out.println("Controller");
		this.animalInfoViewController = new AnimalInfoViewController(this);
		this.formViewController = new FormViewController(this);
		this.searchResultsViewController = new SearchResultsViewController(this);
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
		} else if (e.getSource() == this.view.getSearchButton()
				|| e.getSource() == this.view.getSearchBar()) {
			SearchResultsView resultsView = this.searchResultsViewController
					.buildView(this.model.getAnimalDatabase().getFilteredCats(
							SearchFilterType.Name,
							this.view.getSearchBar().getText()));

			this.view.changePanelOnScrollPane(resultsView);
		} else if (e.getSource() == this.searchResultsViewController) {
			AnimalInfoView animalView = this.animalInfoViewController
					.buildView(this.searchResultsViewController
							.getSelectedCat());

			this.view.changePanelOnScrollPane(animalView);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == this.view.getSearchBar()) {
			if (this.view.getSearchBar().getText()
					.equals(MainModel.C_DefaultSearchText)) {
				this.view.getSearchBar().setText("");
			} else {
				this.view.getSearchBar().selectAll();
			}
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO We need this here to compile even
		// though we aren't using it quite yet
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
