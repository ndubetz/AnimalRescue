package hostView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import mainWindow.MainController;
import model.Cat;
import ui.login.LoginHandler;

public class AnimalInfoViewController implements ActionListener, FocusListener {
	private AnimalInfoView animalInfoView;
	private final MainController mainController;

	public AnimalInfoViewController(MainController mainController) {
		this.mainController = mainController;
		this.animalInfoView = new AnimalInfoView(Cat.emptyCat(),
				this.mainController.getModel().getAnimalDatabase());
	}

	// all action listeners for the AnimalInfoView go here. Should refactor if
	// it gets too large
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.animalInfoView.getBackButton()) {
			this.mainController.reloadPreviousPanel();
		} else if (e.getSource() == this.animalInfoView
				.getEditAndSaveCatButton()) {
			// open login dialog if not logged in
			if (!LoginHandler.singleton().isLoggedIn()) {
				LoginHandler.singleton().openLoginDialog();
			}
			// switch on edit mode, requires login
			if (LoginHandler.singleton().isLoggedIn()) {
				this.animalInfoView.toggleEditMode();
			}
		} else if (e.getSource() == this.animalInfoView
				.getViewCatHistoryButton()) {
			// TODO uncomment this code when CatHistoryView and the model
			// are both completed

			// this.mainController.getView().changePanelOnScrollPane(
			// this.catHistoryViewController.buildView());
		} else if (e.getSource() == this.animalInfoView.getPrintButton()) {
			// TODO implement printing
		} else if (e.getSource() == this.animalInfoView.getExportPDFButton()) {
			// TODO implement PDF export
		} else if (e.getSource() == this.animalInfoView
				.getChangeCatImageButton()) {
			boolean theySaved = this.animalInfoView
					.openFileMenuChooserForCatImage();
			if (theySaved) {
				addChangeCatImageButtonActionListioner();
			}

			this.mainController.getFrame().validate();
		} else if (e.getSource() == this.animalInfoView
				.getAddMedicalHistoryButton()) {
			this.animalInfoView.addRowOfMedicalHistory();
		}
	}

	// Validation for certain textfields
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this.animalInfoView.getBasicInfoPanel()
				.getComponent(5)) {// Birthdate Field
			validateDateFormat((JTextField) e.getSource());
			updateAge();
		} else if (e.getSource() == this.animalInfoView.getBasicInfoPanel()
				.getComponent(15)
				|| e.getSource() == this.animalInfoView.getBasicInfoPanel()
						.getComponent(17)) {// ArrivalDate and
											// DepartureDateField
			validateDateFormat((JTextField) e.getSource());
		}
	}

	private void updateAge() {
		JTextField birthdateField = (JTextField) this.animalInfoView
				.getBasicInfoPanel().getComponent(5);
		Calendar calendar = this.animalInfoView
				.convertDateFormattedStringToCalendar(birthdateField.getText());

		JTextField age = (JTextField) this.animalInfoView.getBasicInfoPanel()
				.getComponent(7);
		age.setText(this.animalInfoView.getCat().getAge(calendar));
	}

	private void validateDateFormat(JTextField textField) {
		if (!textField.getText().matches(
				"[0-1][0-9]/[0-3][0-9]/[0-9][0-9][0-9][0-9]")) {
			JOptionPane.showMessageDialog(this.animalInfoView,
					"Date field must be in MM/DD/YYYY format.");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	public AnimalInfoView buildView(Cat cat) {
		this.animalInfoView = new AnimalInfoView(cat, this.mainController
				.getModel().getAnimalDatabase());
		addActionListenersToButtons();
		addFocusListenersToTextFields();
		return this.animalInfoView;
	}

	private void addFocusListenersToTextFields() {
		this.animalInfoView.getBasicInfoPanel().getComponent(5)
				.addFocusListener(this);
		this.animalInfoView.getBasicInfoPanel().getComponent(15)
				.addFocusListener(this);
		this.animalInfoView.getBasicInfoPanel().getComponent(17)
				.addFocusListener(this);
	}

	private void addActionListenersToButtons() {
		this.animalInfoView.getBackButton().addActionListener(this);
		this.animalInfoView.getEditAndSaveCatButton().addActionListener(this);
		this.animalInfoView.getViewCatHistoryButton().addActionListener(this);
		this.animalInfoView.getPrintButton().addActionListener(this);
		this.animalInfoView.getExportPDFButton().addActionListener(this);
		this.animalInfoView.getChangeCatImageButton().addActionListener(this);
		this.animalInfoView.getAddMedicalHistoryButton()
				.addActionListener(this);
	}

	private void addChangeCatImageButtonActionListioner() {
		this.animalInfoView.getChangeCatImageButton().addActionListener(this);
	}
}
