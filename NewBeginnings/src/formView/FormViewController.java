package formView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainWindow.MainController;

public class FormViewController implements ActionListener {

	private final MainController mainController;
	private FormsView formView;

	public FormViewController(MainController mainController) {
		this.mainController = mainController;
	}

	public FormsView buildView() {
		this.formView = new FormsView();
		addActionListeners();
		return this.formView;
	}

	private void addActionListeners() {
		this.formView.getFormSelectionComboBox().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.formView.getFormSelectionComboBox()) {
			String string = this.formView.getFormSelectionComboBox()
					.getSelectedItem().toString();
			this.formView.loadSelectedForm(string);
		}
	}

}
