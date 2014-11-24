package model.catHistory;

/**
 * CatHistory will hold all information pertaining to the history of the cat
 * upon admission into the rescue. Chunks of related information are grouped
 * into classes and interact only with the CatHistory class. All the classes in
 * this package are stupid and only contain getters, setters, and constructors.
 */

public class CatHistory {
	// containers
	private FeedingInformation feedingInfo;
	private BehaviourInformation behaviourInfo;
	private VeterinarianCareHistory vetCareHistory;
	private LitterBoxInfo litterBoxInfo;
	private GuardianInformation guardianInfo;

	private String additionalInfo;

	public CatHistory(FeedingInformation feedingInfo,
			BehaviourInformation behaviourInfo,
			VeterinarianCareHistory vetCareHistory,
			LitterBoxInfo litterBoxInfo, GuardianInformation guardianInfo,
			String additionalInfo) {
		this.feedingInfo = feedingInfo;
		this.behaviourInfo = behaviourInfo;
		this.vetCareHistory = vetCareHistory;
		this.litterBoxInfo = litterBoxInfo;
		this.additionalInfo = additionalInfo;
	}

	public void updateFeedingInfo(FeedingInformation feedingInfo) {
		this.feedingInfo = feedingInfo;
	}

	public void updateBehaviourInfo(BehaviourInformation behaviourInfo) {
		this.behaviourInfo = behaviourInfo;
	}

	public void updateVetCareHistory(VeterinarianCareHistory vetCareHistory) {
		this.vetCareHistory = vetCareHistory;
	}

	public void updateLitterBoxInfo(LitterBoxInfo litterBoxInfo) {
		this.litterBoxInfo = litterBoxInfo;
	}

	public void updateAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
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
