package hostView;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cat;

public class AnimalInfoViewBuilder {
	private static AnimalInfoViewBuilder SINGLETON = new AnimalInfoViewBuilder();

	private AnimalInfoView theView;
	private Cat theCat;
	private JPanel basicInfoPanel;

	private JPanel upperControlPanel;

	private AnimalInfoViewBuilder() {

	}

	public static AnimalInfoViewBuilder singleton() {
		return SINGLETON;
	}

	public AnimalInfoView build(Cat cat) {
		this.theCat = cat;
		this.theView = new AnimalInfoView();

		buildAndAddUpperControlPanel();
		buildAndAddBasicInfoPanel();

		return this.theView;
	}

	private void buildAndAddUpperControlPanel() {
		this.upperControlPanel = new JPanel();
		this.upperControlPanel.setMaximumSize(new Dimension(700, 100));

		JButton saveCatButton = new JButton();
		saveCatButton.setText("Save Cat");
		JButton editCatButton = new JButton();
		editCatButton.setText("Edit Cat");
		JButton printCatButton = new JButton();
		printCatButton.setText("Print Cat");
		JButton exportToPDFButton = new JButton();
		exportToPDFButton.setText("Export to PDF");
		JButton backButton = new JButton();
		backButton.setText("Back");

		this.upperControlPanel.add(saveCatButton);
		this.upperControlPanel.add(editCatButton);
		this.upperControlPanel.add(printCatButton);
		this.upperControlPanel.add(exportToPDFButton);
		this.upperControlPanel.add(backButton);

		this.theView.add(this.upperControlPanel);
	}

	private void buildAndAddBasicInfoPanel() {
		// the build labels calls will get refactored after the first iteration
		// or whenever more information gets added
		this.basicInfoPanel = new JPanel();
		this.basicInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);

		buildLabelAndTextfieldPair("ID", this.theCat.getID(), constraints, 0);
		buildLabelAndTextfieldPair("Name", this.theCat.getName(), constraints,
				1);
		buildLabelAndTextfieldPair("Birth Date", this.theCat.getBirthdate()
				.getTime().toString(), constraints, 2);
		buildLabelAndTextfieldPair("Age", this.theCat.getAge(), constraints, 3);
		buildLabelAndTextfieldPair("Gender", this.theCat.getGender(),
				constraints, 4);
		buildLabelAndTextfieldPair("Breed", this.theCat.getBreed(),
				constraints, 5);
		buildLabelAndTextfieldPair("Hair Color", this.theCat.getHairColor(),
				constraints, 6);
		buildLabelAndTextfieldPair("Arrival Date", this.theCat.getArrivalDate()
				.getTime().toString(), constraints, 7);
		if (this.theCat.getExpectedDepartureDate() == null) {
			buildLabelAndTextfieldPair("Departure Date", "Not yet known",
					constraints, 8);

		} else {
			buildLabelAndTextfieldPair("Departure Date", this.theCat
					.getExpectedDepartureDate().getTime().toString(),
					constraints, 8);
		}
		buildLabelAndTextfieldPair("Is Fixed",
				Boolean.toString(this.theCat.isFixed()), constraints, 9);

		this.theView.add(this.basicInfoPanel);
		// Yi, use this code to get info from textfield. Odd indices
		// Component[] components = this.basicInfoPanel.getComponents();
		// String str = ((JTextField) components[1]).getText();
		// String str = ((JTextField) components[3]).getText();
	}

	private void buildLabelAndTextfieldPair(String label, String content,
			GridBagConstraints constraints, int row) {
		JLabel jLabel = new JLabel();
		jLabel.setText(label + ": ");
		constraints.gridy = row;
		constraints.gridx = 0;
		this.basicInfoPanel.add(jLabel, constraints);
		JTextField jTextField = new JTextField(25);
		jTextField.setText(content);
		constraints.gridx = 1;
		this.basicInfoPanel.add(jTextField, constraints);
	}
}
