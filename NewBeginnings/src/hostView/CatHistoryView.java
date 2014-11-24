package hostView;

import javax.swing.JPanel;

import model.catHistory.CatHistory;
import model.catHistory.FeedingInformation;
import model.catHistory.GuardianInformation;
import ui.LabelAndTextfieldPairPanelFactory;

@SuppressWarnings("serial")
public class CatHistoryView extends JPanel {
	private JPanel guardianInfoPanel;
	private JPanel feedingInfoPanel;
	private final CatHistory catHistory;

	public CatHistoryView(CatHistory catHistory) {
		this.catHistory = catHistory;
		buildAndAddGuardianInfoPanel();
		buildAndAddFeedingInfoPanel();
	}

	private void buildAndAddFeedingInfoPanel() {
		FeedingInformation feedingInfo = this.catHistory.getFeedingInfo();

		String[] labels = new String[] {
				"What type of food does this cat eat?", "What brand(s)",
				"Does the cat have a special diet?", "How often does it eat?:" };
		String[] content = new String[] { feedingInfo.getFoodType(),
				feedingInfo.getBrands(), feedingInfo.getSpecialDiet(),
				feedingInfo.getEatingFrequency() };

		this.feedingInfoPanel = LabelAndTextfieldPairPanelFactory.buildPanel(
				labels, content);
		this.add(this.feedingInfoPanel);
	}

	private void buildAndAddGuardianInfoPanel() {
		GuardianInformation guardianInfo = this.catHistory.getGuardianInfo();

		// add labels and textfields for string data
		String[] labels = new String[] { "Guardian Name",
				"Guardian Phone Number", "Guardian Address", "Guardian Email",
				"Duration of ownsership", "How was the cat raised",
				"Where was the cat kept at home",
				"If this cat goes ouside, how does it get out?" };
		String[] content = new String[] { guardianInfo.getGuardianName(),
				guardianInfo.getGuardianPhoneNumber(),
				guardianInfo.getGuardianAddress(),
				guardianInfo.getGuardianEmail(),
				guardianInfo.getDurationOfOwnership(),
				guardianInfo.getHowWasTheCatRaised(),
				guardianInfo.getWhereWasTheCatKept(),
				guardianInfo.getHowDoesItGetOutside() };
		this.guardianInfoPanel = LabelAndTextfieldPairPanelFactory.buildPanel(
				labels, content);
		this.add(this.guardianInfoPanel);

		// add other information

	}

}
