package searchResultsView;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Cat;

@SuppressWarnings("serial")
public class SearchResultItemView extends JPanel
{
	private Cat _cat;
	
	public SearchResultItemView(Cat cat)
	{
		super(new GridBagLayout());
		
		this._cat = cat;
	}
	
	private void buildView()
	{
		JLabel nameLabel = new JLabel();
		nameLabel.setText(_cat.getName());
		
		this.add(nameLabel);
	}
}
