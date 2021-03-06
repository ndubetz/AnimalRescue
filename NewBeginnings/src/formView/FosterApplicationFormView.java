package formView;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ui.PanelFactory;

@SuppressWarnings("serial")
public class FosterApplicationFormView extends JPanel {
	private JPanel personalInfoPanel;

	public FosterApplicationFormView() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setUpPersonalInfoPanel();

	}

	private void setUpPersonalInfoPanel() {
		String[] labels = new String[] { "Name", "Birthdate", "Address",
				"Phone number", "Email" };
		this.personalInfoPanel = PanelFactory.buildLabelAndTextFieldPairPanel(
				labels, new String[labels.length]);
		this.add(this.personalInfoPanel);
	}
}
