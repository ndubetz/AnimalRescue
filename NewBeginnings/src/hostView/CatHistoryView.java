package hostView;

import javax.swing.JPanel;

import model.catHistory.CatHistory;
import model.catHistory.GuardianInformation;
import ui.LabelAndTextfieldPairPanelFactory;

@SuppressWarnings("serial")
public class CatHistoryView extends JPanel {
	private JPanel contactInfoPanel;
	private final CatHistory catHistory;

	public CatHistoryView(CatHistory catHistory) {
		this.catHistory = catHistory;
		buildAndAddContactInfoPanel();
	}

	private void buildAndAddContactInfoPanel() {
		GuardianInformation guardianInfo = this.catHistory.getGuardianInfo();

		// add guardian contact info
		String[] labels = new String[] { "Guardian Name",
				"Guardian Phone Number", "Guardian Address", "Guardian Email",
				"Duration of ownsership", "How was the cat raised",
				"Where was the cat kept at home" };
		String[] content = new String[] { guardianInfo.getGuardianName(),
				guardianInfo.getGuardianPhoneNumber(),
				guardianInfo.getGuardianAddress(),
				guardianInfo.getGuardianEmail() };

		this.contactInfoPanel = LabelAndTextfieldPairPanelFactory.buildPanel(
				labels, content);
		this.add(this.contactInfoPanel);

		// add other information

	}
}
