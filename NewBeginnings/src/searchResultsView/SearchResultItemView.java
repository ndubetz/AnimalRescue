package searchResultsView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Cat;

@SuppressWarnings("serial")
public class SearchResultItemView extends JPanel
{
	private Cat _cat;
	private JButton _editButton;
	
	public SearchResultItemView(Cat cat)
	{
		super(new GridBagLayout());
		
		this._cat = cat;
		buildView();
	}
	
	private void buildView()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(_cat.getName());
		c.gridx = 0;
		c.gridy = 0;
		this.add(nameLabel, c);
		
		_editButton = new JButton("View");
		c.gridy = 1;
		this.add(_editButton, c);
	}
}
