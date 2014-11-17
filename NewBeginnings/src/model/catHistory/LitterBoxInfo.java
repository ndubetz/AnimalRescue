package model.catHistory;

import java.util.LinkedList;

/**
 * LitterBoxInfo is a container for the information on the cat's litter box
 * training.
 * 
 */
class LitterBoxInfo {
	private boolean isLitterBoxTrained;
	private String ifNoPleaseExplain;
	private int numberOfCatsInHome;
	private int numberOfLitterBoxesInHome;
	private String litterBoxType;
	private LinkedList<String> litterTypes;
	private String hasTheCatShatOutsideTheBox;
	private LinkedList<String> ifYesThenWhere;
	private String howOftenWereTheAccidents;
	private String recentChangesToRoutine;
	private String hasSeenTheVetForHavingAccidents;
	private String wasAccidentProblemSolved;

	protected LitterBoxInfo() {

	}
}
