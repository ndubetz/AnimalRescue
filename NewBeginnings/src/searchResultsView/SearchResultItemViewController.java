package searchResultsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import model.Cat;

public class SearchResultItemViewController implements ActionListener, FocusListener 
{	
	private SearchResultItemView _itemView;
	
	public SearchResultItemViewController()
	{
		
	}
	
	public SearchResultItemView buildView(Cat cat)
	{
		_itemView = new SearchResultItemView(cat);
		return _itemView;
	}
	
	/**
	 * call this when you are finished with the object,
	 * removes the listeners to help disposal
	 */
	public void removeListeners()
	{
		
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
