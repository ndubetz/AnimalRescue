package hostView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Cat;

@SuppressWarnings("serial")
public class AnimalInfoView extends JPanel {
	private JPanel upperControlPanel;
	private JButton saveCatButton;
	private JButton editCatButton;
	private JButton printCatButton;
	private JButton exportPDFButton;
	private JButton backButton;
	private JPanel basicInfoPanel;
	private final Cat theCat;
	private boolean isInEditMode;

	public AnimalInfoView(Cat cat) {
		this.theCat = cat;
		this.isInEditMode = false;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		buildAndAddUpperControlPanel();
		buildAndAddBasicInfoPanel();
	}

	private void buildAndAddUpperControlPanel() {
		this.upperControlPanel = new JPanel();
		this.upperControlPanel.setMaximumSize(new Dimension(700, 100));

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

		this.add(this.upperControlPanel);
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

		this.add(this.basicInfoPanel);
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
		jTextField.setEditable(false);
		constraints.gridx = 1;
		this.basicInfoPanel.add(jTextField, constraints);
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
		if (!this.isInEditMode) {
			for (int i = 0; i < basicInfoComponents.size(); i++) {
				if (i % 2 == 1) {
					// Yi, use this code to get info from textfield. Odd indices
					JTextField textField = (JTextField) basicInfoComponents
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
