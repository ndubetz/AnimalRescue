package model.catHistory;

import java.util.LinkedList;

/**
 * BehaviourInformation is a container for the behavioural information of the
 * cat.
 */
class BehaviourInformation {
	private boolean useScratchingPost;
	private LinkedList<String> scratchingPreferences;
	private String activityLevel;
	private String sensitiveAreas;
	private LinkedList<String> whatMakesItNervous;
	private LinkedList<String> preferredCompany;
	private LinkedList<String> overallDescription;

	public BehaviourInformation(boolean useScratchingPost,
			LinkedList<String> scratchingPreferences, String activityLevel,
			String sensitiveAreas, LinkedList<String> whatMakesItNervous,
			LinkedList<String> preferredCompany,
			LinkedList<String> overallDescription) {
		this.useScratchingPost = useScratchingPost;
		this.scratchingPreferences = scratchingPreferences;
		this.activityLevel = activityLevel;
		this.sensitiveAreas = sensitiveAreas;
		this.whatMakesItNervous = whatMakesItNervous;
		this.preferredCompany = preferredCompany;
		this.overallDescription = overallDescription;
	}

	public void updateUseScratchingPost(boolean useScratchingPost) {
		this.useScratchingPost = useScratchingPost;
	}

	public void updateScratchingPreferences(
			LinkedList<String> scratchingPreferences) {
		this.scratchingPreferences = scratchingPreferences;
	}

	public void updateActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

	public void updateSensitiveAreas(String sensitiveAreas) {
		this.sensitiveAreas = sensitiveAreas;
	}

	public void updateWhatMakesItNervous(LinkedList<String> whatMakesItNervous) {
		this.whatMakesItNervous = whatMakesItNervous;
	}

	public void updatePreferredCompany(LinkedList<String> preferredCompany) {
		this.preferredCompany = preferredCompany;
	}

	public void updateOverallDescription(LinkedList<String> overallDescription) {
		this.overallDescription = overallDescription;
	}

	public boolean isUseScratchingPost() {
		return this.useScratchingPost;
	}

	public LinkedList<String> getScratchingPreferences() {
		return this.scratchingPreferences;
	}

	public String getActivityLevel() {
		return this.activityLevel;
	}

	public String getSensitiveAreas() {
		return this.sensitiveAreas;
	}

	public LinkedList<String> getWhatMakesItNervous() {
		return this.whatMakesItNervous;
	}

	public LinkedList<String> getPreferredCompany() {
		return this.preferredCompany;
	}

	public LinkedList<String> getOverallDescription() {
		return this.overallDescription;
	}
}
