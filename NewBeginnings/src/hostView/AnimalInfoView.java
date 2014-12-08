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
import java.io.InputStream;
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
import resources.ResourceProvider;
import ui.PanelFactory;
import database.IAnimalDatabase;

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
	private JPanel medicalHistoryPanel1;
	private JPanel imageDisplayPanel;
	private JButton changeCatImageButton;
	private final IAnimalDatabase database;
	private JPanel medicalHistoryPanel2;

	public AnimalInfoView(Cat cat, IAnimalDatabase database) {
		this.theCat = cat;
		this.database = database;
		this.isInEditMode = false;
		this.setLayout(new GridBagLayout());
		buildAndAddUpperControlPanel();
		buildAndAddImageDisplayPanel();
		buildAndAddBasicInfoPanel();
		buildAndAddMedicalHistoryPanel1();
		buildAndAddMedicalHistoryPanel2();
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
		constraints.weightx = 0;
		constraints.anchor = GridBagConstraints.PAGE_START;

		this.viewCatHistoryButton = new JButton("View History");
		this.editAndSaveCatButton = new JButton("Edit");
		this.printCatButton = new JButton("Print");
		this.exportPDFButton = new JButton("Export PDF");
		this.backButton = new JButton("Back");

		// this.upperControlPanel.add(this.viewCatHistoryButton);
		this.upperControlPanel.add(this.editAndSaveCatButton);
		// this.upperControlPanel.add(this.printCatButton);
		// this.upperControlPanel.add(this.exportPDFButton);
		this.upperControlPanel.add(this.backButton);

		this.add(this.upperControlPanel, constraints);
	}

	private void buildAndAddImageDisplayPanel() {
		BufferedImage image = null;
		try {
			if (this.theCat.getCatPictureFilePath() != null
					&& !this.theCat.getCatPictureFilePath().equals("")) {
				File file = new File(this.theCat.getCatPictureFilePath());
				image = ImageIO.read(file);
			} else {
				ResourceProvider r = new ResourceProvider();
				InputStream defaultPicFile = r
						.getResourceStream("Images/defaultImage.jpg");
				image = ImageIO.read(defaultPicFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		buildOrRebuildImageDisplayPanel(image);
		this.changeCatImageButton.setEnabled(false);
	}

	private void buildOrRebuildImageDisplayPanel(BufferedImage image) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.weighty = 0.2;
		constraints.weightx = 0.2;
		constraints.anchor = GridBagConstraints.PAGE_START;

		this.imageDisplayPanel = new JPanel();

		this.imageDisplayPanel.setMinimumSize(new Dimension(250, 250));
		this.imageDisplayPanel.setMaximumSize(new Dimension(250, 250));

		image = resize(image, (int) this.imageDisplayPanel.getMaximumSize()
				.getWidth(), (int) this.imageDisplayPanel.getMaximumSize()
				.getHeight());
		JLabel imageLabel = new JLabel(new ImageIcon(image));

		this.imageDisplayPanel.setLayout(new BoxLayout(this.imageDisplayPanel,
				BoxLayout.PAGE_AXIS));
		this.imageDisplayPanel.setBackground(new Color(255, 0, 0));
		imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.imageDisplayPanel.add(imageLabel);
		changeImageButton(this.imageDisplayPanel);
		this.add(this.imageDisplayPanel, constraints);

	}

	public BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		Graphics2D g2d = bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	private void changeImageButton(JPanel imageDisplayPanel) {
		JPanel changeCatImagePanel = new JPanel();
		changeCatImagePanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 0,
				0));
		changeCatImagePanel.setBackground((new Color(201, 226, 233)));
		this.changeCatImageButton = new JButton("Picture");
		this.changeCatImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		changeCatImagePanel.add(this.changeCatImageButton);
		imageDisplayPanel.add(changeCatImagePanel);
	}

	protected boolean openFileMenuChooserForCatImage() {
		boolean theyClickedSave = false;
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			theyClickedSave = true;
			this.remove(this.imageDisplayPanel);
			BufferedImage image = null;
			try {
				File file = fileChooser.getSelectedFile();
				this.theCat.setCatPictureFilePath(file.toString());
				System.out.println(file.toString());
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			buildOrRebuildImageDisplayPanel(image);
			validatePanels();
		}
		return theyClickedSave;
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
				this.theCat.getBirthdateAsDateFormattedString(),
				this.theCat.getAge(this.theCat.getBirthdate()),
				this.theCat.getGender(), this.theCat.getBreed(),
				this.theCat.getHairColor(),
				this.theCat.getArrivalDateAsDateFormattedString(),
				this.theCat.getDepartureDateAsDateFormattedString() };

		if (content[8] == null) {// Departure date not known
			content[8] = "Not yet known";
		}

		this.basicInfoPanel = PanelFactory.buildLabelAndTextFieldPairPanel(
				labels, content);
		this.add(this.basicInfoPanel, constraints);
	}

	private void buildAndAddMedicalHistoryPanel1() {
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

		this.medicalHistoryPanel1 = PanelFactory
				.buildLabelAndTextFieldPairPanel(labels, content);
		this.add(this.medicalHistoryPanel1, constraints);

	}

	private void buildAndAddMedicalHistoryPanel2() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.weighty = 0;
		constraints.weightx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.LINE_START;

		// turns single dimension array of strings into two arrays
		final String[] medicalHistory = this.theCat.getMedicalHistory();
		String[] dates = new String[medicalHistory.length];
		String[] medicalInfo = new String[medicalHistory.length];
		for (int i = 0; i < medicalHistory.length; i++) {
			String[] pair = medicalHistory[i].split(":");
			System.out.println(pair.length);
			for (int j = 0; j < pair.length; j++)
				System.out.println(pair[j]);
			if (pair.length == 2) {
				dates[i] = pair[0].trim();
				medicalInfo[i] = pair[1].trim();
			}
		}

		this.medicalHistoryPanel2 = PanelFactory
				.buildTextFieldAndTextFieldPairPanel(dates, medicalInfo);
		this.add(this.medicalHistoryPanel2, constraints);

	}

	private void saveNewCat() {
		List<Component> bInfoComponents = Arrays.asList(this.basicInfoPanel
				.getComponents());
		JTextField textfield1 = (JTextField) bInfoComponents.get(1);
		JTextField textfield2 = (JTextField) bInfoComponents.get(3);
		JTextField textfield3 = (JTextField) bInfoComponents.get(5);
		JTextField textfield5 = (JTextField) bInfoComponents.get(9);
		JTextField textfield6 = (JTextField) bInfoComponents.get(11);
		JTextField textfield7 = (JTextField) bInfoComponents.get(13);
		JTextField textfield8 = (JTextField) bInfoComponents.get(15);
		JTextField textfield9 = (JTextField) bInfoComponents.get(17);
		Cat newCat = new Cat(textfield1.getText(), textfield2.getText(),
				convertDateFormattedStringToCalendar(textfield3.getText()),
				textfield5.getText(), textfield6.getText(),
				textfield7.getText(), "",
				convertDateFormattedStringToCalendar(textfield8.getText()),
				convertDateFormattedStringToCalendar(textfield9.getText()), "",
				"", "", new String[] {}, this.theCat.getCatPictureFilePath());

		if (!this.database.getSingleCat(newCat.getID()).getID()
				.equals("NB-00-000")) {
			this.database.updateCat(newCat);
		} else {
			this.database.addNewCat(newCat);
		}
	}

	protected Calendar convertDateFormattedStringToCalendar(String date) {
		Calendar calendar = Calendar.getInstance();

		String[] values = date.split("/");
		calendar.set(Calendar.MONTH, Integer.parseInt(values[0]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(values[1]));
		calendar.set(Calendar.YEAR, Integer.parseInt(values[2]));

		return calendar;
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

	// toggleEditMode is responsible for enabling/disabling text fields and
	// saving to database
	protected void toggleEditMode() {
		List<Component> basicInfoComponents = Arrays.asList(this.basicInfoPanel
				.getComponents());
		List<Component> medicalHistoryComponents = Arrays
				.asList(this.medicalHistoryPanel1.getComponents());

		if (!this.isInEditMode) {
			// skip id field
			for (int i = 1; i < basicInfoComponents.size(); i++) {
				if (i % 2 == 1 && i != 7) {
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
			this.changeCatImageButton.setEnabled(true);
		} else if (this.isInEditMode) {
			saveNewCat();
			// skip id field
			for (int i = 1; i < basicInfoComponents.size(); i++) {
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
			this.changeCatImageButton.setEnabled(false);
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
