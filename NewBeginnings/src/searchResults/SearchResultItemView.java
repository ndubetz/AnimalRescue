package searchResults;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Cat;

/**
 * View class for an individual search result from
 * a cat search
 *
 */
@SuppressWarnings("serial")
public class SearchResultItemView extends JPanel
{
	private Cat _cat;
	private JButton _viewEditButton;
	
	public SearchResultItemView(Cat cat)
	{
		super(new GridBagLayout());
		
		_cat = cat;
		buildView();
	}
	
	/**
	 * Attaches the controller object to the
	 * view's button
	 * 
	 * @param listener should be a SearchResultItemViewController
	 */
	public void attachSelectionListener(ActionListener listener)
	{
		_viewEditButton.addActionListener(listener);
	}
	
	/**
	 * Gets the Cat associated with this view
	 * 
	 * @return the cat
	 */
	public Cat getCat()
	{
		return _cat;
	}
	
	private void buildView()
	{
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(_cat.getName());
		c.gridx = 0;
		c.gridy = 0;
		this.add(nameLabel, c);
		
		_viewEditButton = new JButton("View");
		c.gridy = 1;
		this.add(_viewEditButton, c);
	}
}