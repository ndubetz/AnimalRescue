package formView;

import javax.swing.JPanel;


public class FormsView {
	
	private JPanel mainFormViewPanel;

	public FormsView(){	
		build();
	}

	public void build() {
		mainFormViewPanel = new JPanel();
		System.out.println("FormView");
	}
	
	public JPanel getMainFormViewPanel() {
		return mainFormViewPanel;
	}
	
}
