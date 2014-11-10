package hostView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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

	// FakeAnimalDatabase fkdb = new FakeAnimalDatabase();

	public AnimalInfoView(Cat cat) {
		this.theCat = cat;
		this.isInEditMode = false;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		buildAndAddUpperControlPanel();
		buildAndAddBasicInfoPanel();
		buildAndAddMedicalHistoryPanel();
	}

	private void buildAndAddUpperControlPanel() {
		this.upperControlPanel = new JPanel();
		this.upperControlPanel.setMaximumSize(new Dimension(700, 100));
		this.upperControlPanel.setBackground(new Color(201, 226, 233));

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
		this.add(this.basicInfoPanel);
	}

	private void buildAndAddMedicalHistoryPanel() {
		String[] labels = new String[] { "Spayed/Neutered", "Rabies",
				"Distemper", "FiV/FeLeuk" };

		String[] content = new String[] { this.theCat.getFixed(),
				this.theCat.getRabies(), this.theCat.getDistemper(),
				this.theCat.getFeLeuk() };

		this.medicalHistoryPanel = LabelAndTextfieldPairPanelFactory
				.buildPanel(labels, content);
		this.add(this.medicalHistoryPanel);

	}

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
