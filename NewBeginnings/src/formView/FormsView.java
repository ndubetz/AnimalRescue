package formView;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FormsView extends JPanel {

	private JPanel upperControlPanel;
	private JButton printButton;
	private JButton exportPDFButton;
	private static final String[] FORMS = new String[] { "Select a form...",
			"Volunteer form", "Other forms", "More forms" };
	private JComboBox<String> formSelectionComboBox;
	private JScrollPane scrollPane;

	public FormsView() {
		this.setLayout(new BorderLayout());

		build();
	}

	public void build() {
		System.out.println("FormView");
		buildAndAddUpperControlPanel();
		setPanelToScrollPane(new JPanel());
	}

	private void buildAndAddUpperControlPanel() {
		this.upperControlPanel = new JPanel();
		this.upperControlPanel.setMaximumSize(new Dimension(700, 100));

		this.formSelectionComboBox = new JComboBox<String>(FormsView.FORMS);
		this.formSelectionComboBox.setSelectedItem("Select a form...");
		this.formSelectionComboBox.setEditable(false);

		this.printButton = new JButton("Print");
		this.exportPDFButton = new JButton("Export PDF");

		this.upperControlPanel.add(this.formSelectionComboBox);
		this.upperControlPanel.add(this.printButton);
		this.upperControlPanel.add(this.exportPDFButton);

		this.add(this.upperControlPanel, BorderLayout.PAGE_START);
	}

	public JComboBox<String> getFormSelectionComboBox() {
		return this.formSelectionComboBox;
	}

	public void loadSelectedForm(String selectedItem) {
		switch (selectedItem) {
		case "Volunteer form":
			changePanelOnScrollPane(new VolunteerFormView());
			break;
		default:
			break;
		}
	}

	public void changePanelOnScrollPane(JPanel bottomPanel) {
		this.remove(this.scrollPane);

		setPanelToScrollPane(bottomPanel);
		this.validate();
	}

	private void setPanelToScrollPane(JPanel bottomPanel) {
		this.scrollPane = new JScrollPane(bottomPanel);
		this.add(this.scrollPane, BorderLayout.CENTER);
	}
}
