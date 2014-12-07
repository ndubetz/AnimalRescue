package searchResults;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import resources.ResourceProvider;
import mainWindow.MainView;
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
		this.setBackground(MainView.LIGHT_BLUE);
		
		GridBagConstraints c = new GridBagConstraints();
	
		BufferedImage image = loadImage();
		JLabel imageLabel = new JLabel(new ImageIcon(image));
		c.gridx = 0;
		c.gridy = 0;
		this.add(imageLabel);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText(_cat.getName());
		c.gridy = 1;
		this.add(nameLabel, c);
		
		_viewEditButton = new JButton("View");
		c.gridy = 2;
		this.add(_viewEditButton, c);
	}
	
	private BufferedImage loadImage()
	{
		BufferedImage catImage = null;
		try {
			if (_cat.getCatPictureFilePath() != null
					&& !_cat.getCatPictureFilePath().equals("")) {
				File file = new File(_cat.getCatPictureFilePath());
				catImage = ImageIO.read(file);
			} else {
				ResourceProvider r = new ResourceProvider();
				InputStream defaultPicFile = r
						.getResourceStream("Images/defaultImage.jpg");
				catImage = ImageIO.read(defaultPicFile);
			}
			
			BufferedImage resizedImage = new BufferedImage(100, 100, BufferedImage.TRANSLUCENT);
			Graphics2D g2d = resizedImage.createGraphics();
			g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(catImage, 0, 0, 100, 100, null);
			g2d.dispose();
			return resizedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return catImage;
	}
}