package searchResults;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 * View object for displaying the search results from a cat search
 */
@SuppressWarnings("serial")
public class SearchResultsView extends JPanel {
	private int _currentGBCol;
	private int _currentGBRow;

	public SearchResultsView() {
		super(new GridBagLayout());
		this._currentGBRow = 0;
		this._currentGBCol = 0;
	}

	/**
	 * Adds the view object for a new search result to this panel
	 * 
	 * @param newView
	 *            the result item view
	 */
	public void addCatItemView(SearchResultItemView newView) {
		// the idea behind adding this additional step to adding a cat view
		// is that we can handle the more advanced gridBag stuff
		// in the view and make sure the controller only has to
		// do the actual adding
		GridBagConstraints c = new GridBagConstraints();
		if (this._currentGBCol == 5) {
			this._currentGBCol = 0;
			this._currentGBRow++;
		}

		c.insets = new Insets(5, 5, 5, 5);
		c.gridx = ++this._currentGBCol;
		c.gridy = this._currentGBRow;

		this.add(newView, c);
	}
}