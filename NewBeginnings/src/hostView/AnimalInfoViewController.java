package hostView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainWindow.MainController;
import model.Cat;

public class AnimalInfoViewController implements ActionListener {
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
		} else if (e.getSource() == this.animalInfoView.getEditCatButton()) {
			this.animalInfoView.toggleEditMode();
		}
		// Save Button Listener
		// Export to PDF Button Listener
		// Print Button Listener
	}

	public AnimalInfoView buildView(Cat cat) {
		this.animalInfoView = new AnimalInfoView(cat);
		addActionListeners();
		return this.animalInfoView;
	}

	private void addActionListeners() {
		this.animalInfoView.getBackButton().addActionListener(this);
		this.animalInfoView.getEditCatButton().addActionListener(this);
	}
}
