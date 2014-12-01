package hostView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cat;
import ui.PanelFactory;

/**
 * AnimalInfoView displays the top layer of animal data, which is currently the
 * basic information and the medical history. Has a control panel of buttons
 * 
 */

@SuppressWarnings("serial")
public class AnimalInfoView extends JPanel {
	private JButton viewCatHistoryButton;
	private JButton editAndSaveCatButton;
	private JButton printCatButton;
	private JButton exportPDFButton;
	private JButton backButton;
	private final Cat theCat;
	private boolean isInEditMode;
	private JPanel upperControlPanel;
	private JPanel basicInfoPanel;
	private JPanel medicalHistoryPanel;
	private JPanel imageDisplayPanel;
	private JButton changeCatImageButton;

	// FakeAnimalDatabase fkdb = new FakeAnimalDatabase();

	public AnimalInfoView(Cat cat) {
		this.theCat = cat;
		this.isInEditMode = false;
		this.setLayout(new GridBagLayout());
		buildAndAddUpperControlPanel();
		buildAndAddImageDisplayPanel();
		buildAndAddBasicInfoPanel();
		buildAndAddMedicalHistoryPanel();
	}

	private void buildAndAddUpperControlPanel() {
		this.upperControlPanel = new JPanel();
		this.upperControlPanel.setMaximumSize(new Dimension(500, 60));
		this.upperControlPanel.setBackground(new Color(201, 226, 233));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 0;
		constraints.gridx = 0;
		constraints.weighty = 0.2;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.PAGE_START;

		this.viewCatHistoryButton = new JButton("View History");
		this.editAndSaveCatButton = new JButton("Edit");
		this.printCatButton = new JButton("Print");
		this.exportPDFButton = new JButton("Export PDF");
		this.backButton = new JButton("Back");

		this.upperControlPanel.add(this.viewCatHistoryButton);
		this.upperControlPanel.add(this.editAndSaveCatButton);
		this.upperControlPanel.add(this.printCatButton);
		this.upperControlPanel.add(this.exportPDFButton);
		this.upperControlPanel.add(this.backButton);

		this.add(this.upperControlPanel, constraints);
	}

	private void buildAndAddImageDisplayPanel() {
		BufferedImage image = null;
		try {
			File file = new File("src/resources/Images/TestImage.jpg");
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		buildOrRebuildImageDisplayPanel(image);
	}

	private void buildOrRebuildImageDisplayPanel(BufferedImage image) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.weighty = 0.3;
		constraints.weightx = 0.5;
		constraints.anchor = GridBagConstraints.LINE_END;

		this.imageDisplayPanel = new JPanel();

		this.imageDisplayPanel.setMinimumSize(new Dimension(250, 250));
		this.imageDisplayPanel.setMaximumSize(new Dimension(250, 250));
		
		image = resize(image, (int)imageDisplayPanel.getMaximumSize().getWidth(), (int)imageDisplayPanel.getMaximumSize().getHeight());
		JLabel imageLabel = new JLabel(new ImageIcon(image));

		this.imageDisplayPanel.setLayout(new BoxLayout(this.imageDisplayPanel, BoxLayout.PAGE_AXIS));
		this.imageDisplayPanel.setBackground(new Color(255, 0, 0));		
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.imageDisplayPanel.add(imageLabel);
		changeImageButton(this.imageDisplayPanel);
		this.add(this.imageDisplayPanel, constraints);
		
	}
	
	public BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	private void changeImageButton(JPanel imageDisplayPanel){
		JPanel changeCatImagePanel = new JPanel();
		changeCatImagePanel.setBorder(BorderFactory.createEmptyBorder(2,2,0,0));
		changeCatImagePanel.setBackground((new Color(201, 226, 233)));
		this.changeCatImageButton = new JButton("Picture");
		this.changeCatImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		changeCatImagePanel.add(this.changeCatImageButton);		
		imageDisplayPanel.add(changeCatImagePanel);
	}

	private void buildAndAddBasicInfoPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.weighty = 0.3;
		constraints.weightx = 0.3;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;

		String[] labels = new String[] { "ID", "Name", "Birth Date", "Age",
				"Gender", "Breed", "Hair Color", "Arrival Date",
				"Departure Date" };

		String[] content = new String[] { this.theCat.getID(),
				this.theCat.getName(),
				this.theCat.getBirthdate().getTime().toString(),
				this.theCat.getAge(), this.theCat.getGender(),
				this.theCat.getBreed(), this.theCat.getHairColor(),
				this.theCat.getArrivalDate().getTime().toString(),
				this.theCat.getExpectedDepartureDate().getTime().toString() };

		if (content[8] == null) {// Departure date not known
			content[8] = "Not yet known";
		}

		this.basicInfoPanel = PanelFactory.buildLabelAndTextFieldPairPanel(
				labels, content);
		this.add(this.basicInfoPanel, constraints);
	}

	private void buildAndAddMedicalHistoryPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.weighty = 0.3;
		constraints.weightx = 0.3;
		constraints.anchor = GridBagConstraints.LINE_START;

		String[] labels = new String[] { "Spayed/Neutered", "Rabies",
				"Distemper", "FiV/FeLeuk" };

		String[] content = new String[] { this.theCat.getFixed(),
				this.theCat.getRabies(), this.theCat.getDistemper(),
				this.theCat.getFeLeuk() };

		this.medicalHistoryPanel = PanelFactory
				.buildLabelAndTextFieldPairPanel(labels, content);
		this.add(this.medicalHistoryPanel, constraints);

	}

	// needs to be refactored to accommodate changes
	protected void saveNewCat() {
		List<Component> bInfoComponents = Arrays.asList(this.basicInfoPanel
				.getComponents());
		JTextField textfield1 = (JTextField) bInfoComponents.get(1);
		JTextField textfield2 = (JTextField) bInfoComponents.get(3);
		JTextField textfield3 = (JTextField) bInfoComponents.get(5);
		JTextField textfield4 = (JTextField) bInfoComponents.get(7);
		JTextField textfield5 = (JTextField) bInfoComponents.get(9);
		JTextField textfield6 = (JTextField) bInfoComponents.get(11);
		JTextField textfield7 = (JTextField) bInfoComponents.get(13);
		JTextField textfield8 = (JTextField) bInfoComponents.get(15);
		JTextField textfield9 = (JTextField) bInfoComponents.get(17);
		// JTextField textfield10 = (JTextField) bInfoComponents.get(19);
		Cat nc = new Cat(textfield1.toString(), textfield2.toString(),
				Calendar.getInstance(), textfield5.toString(),
				textfield6.toString(), textfield7.toString(), "",
				Calendar.getInstance(), Calendar.getInstance(), "", "", "",
				new String[] {});
		// Cat nc = new Cat(textfield1.toString(), textfield2.toString(),
		// Calendar.getInstance(), textfield5.toString(), textfield6.toString(),
		// textfield7.toString(), "F", Calendar.getInstance(),
		// Calendar.getInstance());
		// this.fkdb.addNewCat(nc);
		// this.fkdb.check();

	}
	
	protected void openFileMenuChooserForCatImage(){
		
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			this.remove(this.imageDisplayPanel);
			BufferedImage image = null;
			try {
				File file = fileChooser.getSelectedFile();
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			buildOrRebuildImageDisplayPanel(image);
			validatePanels();
        }
	}

	private void validatePanels() {
		 this.validate();		
	}

	protected JButton getPrintButton() {
		return this.printCatButton;
	}

	protected JButton getExportPDFButton() {
		return this.exportPDFButton;
	}

	protected JButton getViewCatHistoryButton() {
		return this.viewCatHistoryButton;
	}

	protected JButton getBackButton() {
		return this.backButton;
	}

	protected JButton getEditAndSaveCatButton() {
		return this.editAndSaveCatButton;
	}
	
	protected JButton getChangeCatImageButton() {
		return this.changeCatImageButton;
	}

	protected void toggleEditMode() {
		List<Component> basicInfoComponents = Arrays.asList(this.basicInfoPanel
				.getComponents());
		List<Component> medicalHistoryComponents = Arrays
				.asList(this.medicalHistoryPanel.getComponents());
		// switch on edit mode, requires login
		if (!this.isInEditMode) {
			for (int i = 0; i < basicInfoComponents.size(); i++) {
				if (i % 2 == 1) {
					JTextField textField = (JTextField) basicInfoComponents
							.get(i);
					textField.setEditable(true);
				}
			}
			for (int i = 0; i < medicalHistoryComponents.size(); i++) {
				if (i % 2 == 1) {
					JTextField textField = (JTextField) medicalHistoryComponents
							.get(i);
					textField.setEditable(true);
				}
			}
			this.editAndSaveCatButton.setText("Save");
			this.isInEditMode = true;
			// save, then switch off edit mode
			// NOTE: This code should only be reached if a user has
			// admin privilege
		} else {
			// save if in login mode
			// will fill in shortly
			if (!true) {
				// saveNewCat();
			}
			for (int i = 0; i < basicInfoComponents.size(); i++) {
				if (i % 2 == 1) {
					JTextField textField = (JTextField) basicInfoComponents
							.get(i);
					textField.setEditable(false);
				}
			}
			for (int i = 0; i < medicalHistoryComponents.size(); i++) {
				if (i % 2 == 1) {
					JTextField textField = (JTextField) medicalHistoryComponents
							.get(i);
					textField.setEditable(false);
				}
			}
			this.editAndSaveCatButton.setText("Edit");
			this.isInEditMode = false;
		}
	}

	protected JPanel getBasicInfoPanel() {
		return this.basicInfoPanel;
	}

	protected JPanel getUpperControlPanel() {
		return this.upperControlPanel;
	}

	protected Cat getCat() {
		return this.theCat;
	}

}
