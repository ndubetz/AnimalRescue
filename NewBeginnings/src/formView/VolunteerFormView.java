package formView;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VolunteerFormView extends JPanel {
	private JPanel personalInfoPanel;
	private final JPanel animalExperience;
	private final JPanel volunteerQuestionnaire;

	public VolunteerFormView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.animalExperience = new JPanel();
		this.volunteerQuestionnaire = new JPanel();

		setUpPersonalInfoPanel();
		setUpAnimalExperiencePanel();
		setUpVolunteerQuestionnairePanel();
		setUpVolunteerFormPanel();
	}

	private void setUpVolunteerFormPanel() {

	}

	private void setUpVolunteerQuestionnairePanel() {

	}

	private void setUpAnimalExperiencePanel() {
		JCheckBox academic = new JCheckBox("Academic animal background");
		this.animalExperience.add(academic);

		// this.add(this.personalInfoPanel);
	}

	private void setUpPersonalInfoPanel() {
		this.personalInfoPanel = new JPanel();
		this.personalInfoPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);

		buildLabelAndTextfieldPair("Name", constraints, 0);
		buildLabelAndTextfieldPair("Birthdate", constraints, 1);
		buildLabelAndTextfieldPair("Address", constraints, 2);
		buildLabelAndTextfieldPair("City", constraints, 3);
		buildLabelAndTextfieldPair("Zip Code", constraints, 4);
		buildLabelAndTextfieldPair("Primary Phone", constraints, 5);
		buildLabelAndTextfieldPair("Email", constraints, 6);
		buildLabelAndTextfieldPair(
				"Do you have any limitations or special needs", constraints, 7);

		this.add(this.personalInfoPanel);
	}

	private void buildLabelAndTextfieldPair(String label,
			GridBagConstraints constraints, int row) {
		JLabel jLabel = new JLabel();
		jLabel.setText(label + ": ");
		constraints.gridy = row;
		constraints.gridx = 0;
		this.personalInfoPanel.add(jLabel, constraints);
		JTextField jTextField = new JTextField(25);
		constraints.gridx = 1;
		this.personalInfoPanel.add(jTextField, constraints);
	}
}
