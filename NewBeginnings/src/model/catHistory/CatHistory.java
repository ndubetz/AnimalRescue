package model.catHistory;

/**
 * CatHistory will hold all information pertaining to the history of the cat
 * upon admission into the rescue. Chunks of related information are grouped
 * into package-protected classes and interact only with the CatHistory class
 */

public class CatHistory {
	// containers
	private FeedingInformation feedingInfo;
	private BehaviourInformation behaviourInfo;
	private VeterinarianCareHistory vetCareHistory;
	private LitterBoxInfo litterBoxInfo;
	private GuardianInformation guardianInfo;

	private String additionalInfo;

	public CatHistory() {

	}

	public FeedingInformation getFeedingInfo() {
		return this.feedingInfo;
	}

	public BehaviourInformation getBehaviourInfo() {
		return this.behaviourInfo;
	}

	public VeterinarianCareHistory getVetCareHistory() {
		return this.vetCareHistory;
	}

	public LitterBoxInfo getLitterBoxInfo() {
		return this.litterBoxInfo;
	}

	public GuardianInformation getGuardianInfo() {
		return this.guardianInfo;
	}

	public String getAdditionalInfo() {
		return this.additionalInfo;
	}
}
