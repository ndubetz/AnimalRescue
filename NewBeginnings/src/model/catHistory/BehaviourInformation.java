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

	protected BehaviourInformation() {

	}
}
