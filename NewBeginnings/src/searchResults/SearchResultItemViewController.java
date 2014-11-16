package searchResults;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import model.Cat;

/**
 * Controller for a SearchResultItem
 */
public class SearchResultItemViewController implements ActionListener
{	
	private SearchResultsViewController _resultsView;
	private SearchResultItemView _itemView;
	
	public SearchResultItemViewController(SearchResultsViewController resultsView)
	{
		_resultsView = resultsView;
	}
	
	/**
	 * Creates the view object
	 * 
	 * @param cat the cat result being displayed
	 * @return the view object
	 */
	public SearchResultItemView buildView(Cat cat)
	{
		_itemView = new SearchResultItemView(cat);
		_itemView.attachSelectionListener(this);
		
		return _itemView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		_resultsView.setSelectedCat(_itemView.getCat());
	}
}