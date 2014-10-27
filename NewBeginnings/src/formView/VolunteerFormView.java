package formView;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VolunteerFormView {
	public VolunteerFormView(){
		JPanel volunteerFormView = new JPanel();
		JPanel personalInfoPanel = new JPanel();
		JPanel animalExperience = new JPanel();
		JPanel volunteerQuestionnaire = new JPanel();
		
		setUpPersonalInfoPanel(personalInfoPanel);
		animalExperience = setUpAnimalExperiencePanel(animalExperience);
		setUpVolunteerQuestionnairePanel(volunteerQuestionnaire);
		setUpVolunteerFormPanel(volunteerFormView, personalInfoPanel, animalExperience, volunteerQuestionnaire);
	}

	private void setUpVolunteerFormPanel(JPanel volunteerFormView,
			JPanel personalInfoPanel, JPanel animalExperience,
			JPanel volunteerQuestionnaire) {
	
		
	}

	private void setUpVolunteerQuestionnairePanel(JPanel volunteerQuestionnaire) {
		
		
	}

	private JPanel setUpAnimalExperiencePanel(JPanel animalExperience) {
		JCheckBox academic = new JCheckBox("Academic animal background");
		animalExperience.add(academic);
		return animalExperience;
	}

	private void setUpPersonalInfoPanel(JPanel personalInfoPanel) {
		JLabel nameLabel = new JLabel("Name");
		JLabel birthdateLabel = new JLabel("Birthdate");
		JLabel addressLabel = new JLabel("Address");
		JLabel cityLabel = new JLabel("City");
		JLabel zipCodeLabel = new JLabel("Zip");
		JLabel primaryPhoneLabel = new JLabel("Primary Phone");
		JLabel emailLabel = new JLabel("Email");
		JLabel specialNeedsLabel = new JLabel("Do you have any limitations or special needs?");
		
		JTextField nameText = new JTextField("Name");
		JTextField birthdateText = new JTextField("Birthdate");
		JTextField addressText = new JTextField("Address");
		JTextField cityText = new JTextField("City");
		JTextField zipCodeText = new JTextField("Zip");
		JTextField primaryPhoneText = new JTextField("Primary Phone");
		JTextField emailText = new JTextField("Email");
		JTextField specialNeedsText = new JTextField("");		
	}

}
