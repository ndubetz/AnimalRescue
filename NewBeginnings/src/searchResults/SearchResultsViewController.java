package searchResults;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import mainWindow.MainController;
import model.Cat;

/**
 * Controller class for the search results view
 */
public class SearchResultsViewController {
	private final MainController _mainController;
	private Cat _selectedCat;
	private SearchResultsView _view;
	private LinkedList<SearchResultItemViewController> _resultControllers;

	public SearchResultsViewController(MainController mainController) {
		this._mainController = mainController;
	}

	/**
	 * Gets the cat that is currently selected in the search results panel
	 * 
	 * @return
	 */
	public Cat getSelectedCat() {
		return this._selectedCat;
	}

	/**
	 * Selects a cat from the search results panel and tells the main controller
	 * to switch the view to the AnimalInfoView representing the selected cat
	 * 
	 * @param selectedCat
	 *            the selected cat
	 */
	public void setSelectedCat(Cat selectedCat) {
		this._selectedCat = selectedCat;

		// manually relay this event back up to the MainController
		// since we are switching views
		ActionEvent switchEvent = new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "Opening a Cat View");
		this._mainController.actionPerformed(switchEvent);
	}

	/**
	 * Builds the view object associated with this controller
	 * 
	 * @param cats
	 *            the list of cats to show
	 * @return the view object
	 */
	public SearchResultsView buildView(List<Cat> cats) {
		this._view = new SearchResultsView();
		refreshView(cats);

		return this._view;
	}

	private void refreshView(List<Cat> searchResultCats) {
		// clear out all elements from the previous search
		this._view.removeAll();

		this._resultControllers = new LinkedList<SearchResultItemViewController>();

		// add a new SearchResultItem for each cat in the results list
		for (Cat cat : searchResultCats) {
			SearchResultItemViewController newController = new SearchResultItemViewController(
					this);
			this._view.addCatItemView(newController.buildView(cat));
			this._resultControllers.add(newController);
		}
	}
}