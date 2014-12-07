package searchResults;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

/**
 * View object for displaying the search results from a cat search
 */
@SuppressWarnings("serial")
public class SearchResultsView extends JPanel
{	
	private int _currentGBCol;
	private int _currentGBRow;
	
	public SearchResultsView()
	{
		super(new GridBagLayout());
		_currentGBRow = 0;
		_currentGBCol = 0;
	}
	
	/**
	 * Adds the view object for a new search result to this
	 * panel
	 * 
	 * @param newView the result item view
	 */
	public void addCatItemView(SearchResultItemView newView)
	{
		//the idea behind adding this additional step to adding a cat view
		//is that we can handle the more advanced gridBag stuff
		//in the view and make sure the controller only has to 
		//do the actual adding
		GridBagConstraints c = new GridBagConstraints();
		if(_currentGBCol == 5)
		{
			_currentGBCol = 0;
			_currentGBRow++;
		}
		
		c.gridx = ++_currentGBCol;
		c.gridy = _currentGBRow;
		
		this.add(newView, c);
	}
}