package formView;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import mainWindow.MainController;


public class FormViewController implements ActionListener{

	private final MainController mainController;
	private FormsView formView;
	
	public FormViewController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public FormsView buildView() {
		this.formView = new FormsView();
		return this.formView;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
