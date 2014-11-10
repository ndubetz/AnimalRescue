package searchResultsView;

import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JPanel;

import model.Cat;

@SuppressWarnings("serial")
public class SearchResultsView extends JPanel
{
	public SearchResultsView()
	{
		super(new GridBagLayout());
	}
	
	private void refreshView(List<Cat> searchResultCats)
	{
		//clear out all elements form the previous search
		this.removeAll();
		
		//add a new SearchResultItem for each cat in the results list
		for(Cat cat : searchResultCats)
		{
			
		}
	}
}
