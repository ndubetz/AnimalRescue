package formView;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import ui.LabelAndTextfieldPairPanelFactory;

@SuppressWarnings("serial")
public class VolunteerApplicationFormView extends JPanel {
	private JPanel personalInfoPanel;
	private final JPanel animalExperience;
	private final JPanel volunteerQuestionnaire;

	public VolunteerApplicationFormView() {
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
		String[] labels = new String[] { "Name", "Birthdate", "Address",
				"City", "Zip Code", "Primary Phone", "Email" };

		this.personalInfoPanel = LabelAndTextfieldPairPanelFactory.buildPanel(
				labels, new String[labels.length]);

		// TODO This will be a JTextArea
		// "Do you have any limitations or special needs", "", constraints, 7);

		this.add(this.personalInfoPanel);
	}
}
