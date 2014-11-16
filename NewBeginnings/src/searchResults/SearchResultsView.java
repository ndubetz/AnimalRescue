package searchResults;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

/**
 * View object for displaying the search results from a cat search
 */
@SuppressWarnings("serial")
public class SearchResultsView extends JPanel
{	
	public SearchResultsView()
	{
		super(new GridBagLayout());
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
		this.add(newView);
	}
}