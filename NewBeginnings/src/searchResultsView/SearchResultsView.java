package searchResultsView;

import java.awt.GridBagLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Cat;

@SuppressWarnings("serial")
public class SearchResultsView extends JPanel
{
	private List<SearchResultItemViewController> _resultControllers;
	
	public SearchResultsView(List<Cat> searchResultCats)
	{
		super(new GridBagLayout());
		refreshView(searchResultCats);
	}
	
	private void refreshView(List<Cat> searchResultCats)
	{
		//clear out all elements form the previous search
		this.removeAll();
		
		_resultControllers = new LinkedList<SearchResultItemViewController>();
		
		//add a new SearchResultItem for each cat in the results list
		for(Cat cat : searchResultCats)
		{
			SearchResultItemViewController newController = new SearchResultItemViewController();
			this.add(newController.buildView(cat));
			_resultControllers.add(newController);
		}
	}
}
