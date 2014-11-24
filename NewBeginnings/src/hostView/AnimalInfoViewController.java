package hostView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

import mainWindow.MainController;
import model.Cat;

public class AnimalInfoViewController implements ActionListener, FocusListener {
	private AnimalInfoView animalInfoView;
	private final MainController mainController;

	public AnimalInfoViewController(MainController mainController) {
		this.mainController = mainController;
		this.animalInfoView = new AnimalInfoView(Cat.emptyCat());
	}

	// all action listeners for the AnimalInfoView go here. Should refactor if
	// it gets too large
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.animalInfoView.getBackButton()) {
			this.mainController.reloadPreviousPanel();
		} else if (e.getSource() == this.animalInfoView
				.getEditAndSaveCatButton()) {
			this.animalInfoView.toggleEditMode();
		} else if (e.getSource() == this.animalInfoView
				.getViewCatHistoryButton()) {
			// TODO change panel
		} else if (e.getSource() == this.animalInfoView.getPrintButton()) {
			// TODO implement printing
		} else if (e.getSource() == this.animalInfoView.getExportPDFButton()) {
			// TODO implement PDF export
		}else if(e.getSource() == this.animalInfoView.getChangeCatImageButton()){
			this.animalInfoView.openFileMenuChooserForCatImage();
		}
	}

	// Validation for certain textfields
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this.animalInfoView.getBasicInfoPanel()
				.getComponent(1)) {// ID Field
			validateID((JTextField) e.getSource());
		}
	}

	private void validateID(JTextField idField) {
		if (!idField.getText().matches("NB-[0-9][0-9]-[0-9][0-9][0-9]")) {
			idField.setText("Please enter a valid ID");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	public AnimalInfoView buildView(Cat cat) {
		this.animalInfoView = new AnimalInfoView(cat);
		addActionListenersToButtons();
		addFocusListenersToTextFields();
		return this.animalInfoView;
	}

	private void addFocusListenersToTextFields() {
		this.animalInfoView.getBasicInfoPanel().getComponent(1)
				.addFocusListener(this);
	}

	private void addActionListenersToButtons() {
		this.animalInfoView.getBackButton().addActionListener(this);
		this.animalInfoView.getEditAndSaveCatButton().addActionListener(this);
		this.animalInfoView.getViewCatHistoryButton().addActionListener(this);
		this.animalInfoView.getPrintButton().addActionListener(this);
		this.animalInfoView.getExportPDFButton().addActionListener(this);
		this.animalInfoView.getChangeCatImageButton().addActionListener(this);
	}

}
