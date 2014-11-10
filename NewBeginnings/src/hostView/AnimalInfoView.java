package hostView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cat;
import ui.LabelAndTextfieldPairPanelFactory;

@SuppressWarnings("serial")
public class AnimalInfoView extends JPanel {
	private JPanel upperControlPanel;
	private JButton saveCatButton;
	private JButton editCatButton;
	private JButton printCatButton;
	private JButton exportPDFButton;
	private JButton backButton;
	private JPanel basicInfoPanel;
	private JPanel medicalHistoryPanel;
	private final Cat theCat;
	private boolean isInEditMode;
	private JPanel imageDisplayPanel;

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
		constraints.weighty = 0.1;
		constraints.weightx = 1;
		constraints.anchor = GridBagConstraints.PAGE_START;

		this.saveCatButton = new JButton("Save");
		this.editCatButton = new JButton("Edit");
		this.printCatButton = new JButton("Print");
		this.exportPDFButton = new JButton("Export PDF");
		this.backButton = new JButton("Back");

		this.upperControlPanel.add(this.saveCatButton);
		this.upperControlPanel.add(this.editCatButton);
		this.upperControlPanel.add(this.printCatButton);
		this.upperControlPanel.add(this.exportPDFButton);
		this.upperControlPanel.add(this.backButton);

		this.add(this.upperControlPanel, constraints);
	}

	private void buildAndAddImageDisplayPanel() {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(2, 2, 2, 2);
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.weighty = 0.3;
		constraints.weightx = 0.5;
		constraints.anchor = GridBagConstraints.LINE_END;

		BufferedImage image = null;

		try {
			File file = new File("src/resources/Images/TestImage.jpg");
			System.out.println(file.getAbsolutePath());
			System.out.println(file.getPath());
			System.out.println(file.getCanonicalPath());
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		JLabel imageLabel = new JLabel(new ImageIcon(image));

		this.imageDisplayPanel = new JPanel();
		this.imageDisplayPanel.setBackground(new Color(255, 0, 0));
		this.imageDisplayPanel.setMinimumSize(new Dimension(250, 250));
		this.imageDisplayPanel.setMaximumSize(new Dimension(250, 250));
		this.imageDisplayPanel.add(imageLabel);
		this.add(this.imageDisplayPanel, constraints);
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

		this.basicInfoPanel = LabelAndTextfieldPairPanelFactory.buildPanel(
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

		this.medicalHistoryPanel = LabelAndTextfieldPairPanelFactory
				.buildPanel(labels, content);
		this.add(this.medicalHistoryPanel, constraints);

	}

	// will need to be refactored to accommodate changes
	protected void savenewcat() {
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

	protected JButton getPrintButton() {
		return this.printCatButton;
	}

	protected JButton getExportPDFButton() {
		return this.exportPDFButton;
	}

	protected JButton getSaveButton() {
		return this.saveCatButton;
	}

	protected JButton getBackButton() {
		return this.backButton;
	}

	protected JButton getEditCatButton() {
		return this.editCatButton;
	}

	protected void toggleEditMode() {
		List<Component> basicInfoComponents = Arrays.asList(this.basicInfoPanel
				.getComponents());
		List<Component> medicalHistoryComponents = Arrays
				.asList(this.medicalHistoryPanel.getComponents());
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
			this.editCatButton.setText("Done");
			this.isInEditMode = true;
		} else {
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
			this.editCatButton.setText("Edit");
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
