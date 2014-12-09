package hostView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import mainWindow.MainView;
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
	private JPanel medicalInfoPanel;
	private JPanel imageDisplayPanel;
	private JButton changeCatImageButton;
	private final IAnimalDatabase database;
	private JPanel medicalHistoryPanel;
	private JTextArea commentTextArea;
	private JButton addMedicalHistoryButton;

	public AnimalInfoView(Cat cat, IAnimalDatabase database) {
		this.theCat = cat;
		this.database = database;
		this.isInEditMode = false;
		this.setLayout(new GridBagLayout());
		buildAndAddUpperControlPanel();
		buildAndAddImageDisplayPanel();
		buildAndAddBasicInfoPanel();
		buildAndAddMedicalInfoPanel();
		buildAndAddMedicalHistoryPanel();
		buildCommentPanel();
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
		constraints.gridheight = GridBagConstraints.REMAINDER;
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
		constraints.weighty = 0;
		constraints.weightx = 0.3;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;

		this.add(buildHeader("Basic Information:"), constraints);

		constraints.gridy = 2;
		constraints.weighty = 0.3;

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

	private void buildAndAddMedicalInfoPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.weighty = 0.0;
		constraints.weightx = 0.3;
		constraints.anchor = GridBagConstraints.LINE_START;

		this.add(buildHeader("Medical Information:"), constraints);

		constraints.gridy = 4;

		String[] labels = new String[] { "Spayed/Neutered", "Rabies",
				"Distemper", "FiV/FeLeuk" };

		String[] content = new String[] { this.theCat.getFixed(),
				this.theCat.getRabies(), this.theCat.getDistemper(),
				this.theCat.getFeLeuk() };

		this.medicalInfoPanel = PanelFactory.buildLabelAndTextFieldPairPanel(
				labels, content);
		this.add(this.medicalInfoPanel, constraints);

	}

	private void buildAndAddMedicalHistoryPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.LINE_START;

		final JPanel medicalHistoryHeaderPanel = new JPanel();
		medicalHistoryHeaderPanel.setLayout(new GridBagLayout());
		medicalHistoryHeaderPanel.setBackground(MainView.LIGHT_BLUE);
		constraints.gridy = 0;
		medicalHistoryHeaderPanel.add(buildHeader("Medical History:"),
				constraints);
		this.addMedicalHistoryButton = new JButton("Add Medical History");
		this.addMedicalHistoryButton.setEnabled(false);
		constraints.gridx = 1;
		medicalHistoryHeaderPanel
				.add(this.addMedicalHistoryButton, constraints);

		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.gridy = 5;
		constraints.gridx = 0;
		this.add(medicalHistoryHeaderPanel, constraints);

		constraints.gridy = 6;
		// turns single dimension array of strings into two arrays
		final String[] medicalHistory = this.theCat.getMedicalHistory();
		String[] dates = new String[medicalHistory.length];
		String[] medicalInfo = new String[medicalHistory.length];
		for (int i = 0; i < medicalHistory.length; i++) {
			String[] pair = medicalHistory[i].split(":");

			if (pair.length == 2) {
				dates[i] = pair[0].trim();
				medicalInfo[i] = pair[1].trim();
			}
		}

		this.medicalHistoryPanel = PanelFactory
				.buildTextFieldAndTextFieldPairPanel(dates, medicalInfo);
		this.add(this.medicalHistoryPanel, constraints);

	}

	private void buildCommentPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 7;
		constraints.gridx = 0;
		constraints.weighty = 0;
		constraints.weightx = 0;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.anchor = GridBagConstraints.LINE_START;

		this.add(buildHeader("Comments:"), constraints);

		constraints.gridy = 8;
		this.commentTextArea = new JTextArea(this.theCat.getCommentsAboutCat(), 10, 30);
		JScrollPane commentSectionScrollablePanel = new JScrollPane(
				this.commentTextArea);

		commentSectionScrollablePanel
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.commentTextArea.setVisible(true);
		this.commentTextArea.setEnabled(false);

		this.add(commentSectionScrollablePanel, constraints);
	}

	private void saveCat() {
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
		
		List<Component> mInfoComponents = Arrays.asList(this.medicalInfoPanel
				.getComponents());
		JTextField spayNeut = (JTextField)mInfoComponents.get(1);
		JTextField rabies = (JTextField)mInfoComponents.get(3);
		JTextField distemper = (JTextField)mInfoComponents.get(5);
		JTextField fiv = (JTextField)mInfoComponents.get(7);
		
		String[] medicalHistory = extractMedicalHistory();

		Cat newCat = new Cat(textfield1.getText(), textfield2.getText(),
				convertDateFormattedStringToCalendar(textfield3.getText()),
				textfield5.getText(), textfield6.getText(),
				textfield7.getText(), spayNeut.getText(),
				convertDateFormattedStringToCalendar(textfield8.getText()),
				convertDateFormattedStringToCalendar(textfield9.getText()), rabies.getText(),
				distemper.getText(), fiv.getText(), medicalHistory, this.theCat.getCatPictureFilePath(),
				this.commentTextArea.getText());
		System.out.println(this.commentTextArea.getText());

		if (!this.database.getSingleCat(newCat.getID()).getID()
				.equals("NB-00-000")) {
			this.database.updateCat(newCat);
		} else {
			this.database.addNewCat(newCat);
		}
	}

	private String[] extractMedicalHistory() {
		List<Component> medicalHistoryComponents = Arrays
				.asList(this.medicalHistoryPanel.getComponents());
		String[] medicalHistory = new String[medicalHistoryComponents.size() / 2];
		// turn each pair of textfields into one string
		for (int i = 0; i < medicalHistory.length * 2; i = i + 2) {
			JTextField dateField = (JTextField) medicalHistoryComponents.get(i);
			JTextField infoField = (JTextField) medicalHistoryComponents
					.get(i + 1);
			medicalHistory[i / 2] = dateField.getText() + " : "
					+ infoField.getText();
		}

		return medicalHistory;
	}

	protected Calendar convertDateFormattedStringToCalendar(String date) {
		Calendar calendar = Calendar.getInstance();

		String[] values = date.split("/");
		calendar.set(Calendar.MONTH, Integer.parseInt(values[0]) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(values[1]));
		calendar.set(Calendar.YEAR, Integer.parseInt(values[2]));

		return calendar;
	}

	private JPanel buildHeader(String headerText) {
		final JPanel panel = new JPanel();

		JLabel label = new JLabel(headerText);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(label);
		panel.setBackground(MainView.LIGHT_BLUE);

		return panel;
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

	protected void addRowOfMedicalHistory() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridy = this.medicalHistoryPanel.getComponentCount() / 2 + 1;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.LINE_START;

		JTextField dateField = new JTextField(10);
		this.medicalHistoryPanel.add(dateField, constraints);

		JTextField infoField = new JTextField(40);
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		this.medicalHistoryPanel.add(infoField, constraints);
		validatePanels();
	}

	// toggleEditMode is responsible for enabling/disabling text fields and
	// saving to database
	protected void toggleEditMode() {
		List<Component> basicInfoComponents = Arrays.asList(this.basicInfoPanel
				.getComponents());
		List<Component> medicalHistoryComponents = Arrays
				.asList(this.medicalInfoPanel.getComponents());
		List<Component> medicalHistoryComponents2 = Arrays
				.asList(this.medicalHistoryPanel.getComponents());

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
			for (int i = 0; i < medicalHistoryComponents2.size(); i++) {
				JTextField textField = (JTextField) medicalHistoryComponents2
						.get(i);
				textField.setEditable(true);
			}
			this.editAndSaveCatButton.setText("Save");
			this.isInEditMode = true;
			this.addMedicalHistoryButton.setEnabled(true);
			this.changeCatImageButton.setEnabled(true);
			this.commentTextArea.setEnabled(true);
		} else if (this.isInEditMode) {
			saveCat();
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
			for (int i = 0; i < medicalHistoryComponents2.size(); i++) {
				JTextField textField = (JTextField) medicalHistoryComponents2
						.get(i);
				textField.setEditable(false);
			}
			this.editAndSaveCatButton.setText("Edit");
			this.isInEditMode = false;
			this.changeCatImageButton.setEnabled(false);
			this.addMedicalHistoryButton.setEnabled(false);
			this.commentTextArea.setEnabled(false);
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

	protected JButton getAddMedicalHistoryButton() {
		return this.addMedicalHistoryButton;
	}

}
