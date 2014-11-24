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
	private boolean hasTheCatShatOutsideTheBox;
	private String ifYesThenWhere;
	private String howOftenWereTheAccidents;
	private String recentChangesToRoutine;
	private String hasSeenTheVetForHavingAccidents;
	private String wasAccidentProblemSolved;

	public LitterBoxInfo(boolean isLitterBoxTrained, String ifNoPleaseExplain,
			int numberOfCatsInHome, int numberOfLitterBoxesInHome,
			String litterBoxType, LinkedList<String> litterTypes,
			boolean hasTheCatShatOutsideTheBox, String ifYesThenWhere,
			String howOftenWereTheAccidents, String recentChangesToRoutine,
			String hasSeenTheVetForHavingAccidents,
			String wasAccidentProblemSolved) {
		this.isLitterBoxTrained = isLitterBoxTrained;
		this.ifNoPleaseExplain = ifNoPleaseExplain;
		this.numberOfCatsInHome = numberOfCatsInHome;
		this.numberOfLitterBoxesInHome = numberOfLitterBoxesInHome;
		this.litterBoxType = litterBoxType;
		this.litterTypes = litterTypes;
		this.hasTheCatShatOutsideTheBox = hasTheCatShatOutsideTheBox;
		this.ifYesThenWhere = ifYesThenWhere;
		this.howOftenWereTheAccidents = howOftenWereTheAccidents;
		this.hasSeenTheVetForHavingAccidents = hasSeenTheVetForHavingAccidents;
		this.wasAccidentProblemSolved = wasAccidentProblemSolved;
	}

	public boolean isLitterBoxTrained() {
		return this.isLitterBoxTrained;
	}

	public void updateLitterBoxTrained(boolean isLitterBoxTrained) {
		this.isLitterBoxTrained = isLitterBoxTrained;
	}

	public String getIfNoPleaseExplain() {
		return this.ifNoPleaseExplain;
	}

	public void updateIfNoPleaseExplain(String ifNoPleaseExplain) {
		this.ifNoPleaseExplain = ifNoPleaseExplain;
	}

	public int getNumberOfCatsInHome() {
		return this.numberOfCatsInHome;
	}

	public void updateNumberOfCatsInHome(int numberOfCatsInHome) {
		this.numberOfCatsInHome = numberOfCatsInHome;
	}

	public int getNumberOfLitterBoxesInHome() {
		return this.numberOfLitterBoxesInHome;
	}

	public void updateNumberOfLitterBoxesInHome(int numberOfLitterBoxesInHome) {
		this.numberOfLitterBoxesInHome = numberOfLitterBoxesInHome;
	}

	public String getLitterBoxType() {
		return this.litterBoxType;
	}

	public void updateLitterBoxType(String litterBoxType) {
		this.litterBoxType = litterBoxType;
	}

	public LinkedList<String> getLitterTypes() {
		return this.litterTypes;
	}

	public void updateLitterTypes(LinkedList<String> litterTypes) {
		this.litterTypes = litterTypes;
	}

	public boolean getHasTheCatShatOutsideTheBox() {
		return this.hasTheCatShatOutsideTheBox;
	}

	public void updateHasTheCatShatOutsideTheBox(
			boolean hasTheCatShatOutsideTheBox) {
		this.hasTheCatShatOutsideTheBox = hasTheCatShatOutsideTheBox;
	}

	public String getIfYesThenWhere() {
		return this.ifYesThenWhere;
	}

	public void updateIfYesThenWhere(String ifYesThenWhere) {
		this.ifYesThenWhere = ifYesThenWhere;
	}

	public String getHowOftenWereTheAccidents() {
		return this.howOftenWereTheAccidents;
	}

	public void updateHowOftenWereTheAccidents(String howOftenWereTheAccidents) {
		this.howOftenWereTheAccidents = howOftenWereTheAccidents;
	}

	public String getRecentChangesToRoutine() {
		return this.recentChangesToRoutine;
	}

	public void updateRecentChangesToRoutine(String recentChangesToRoutine) {
		this.recentChangesToRoutine = recentChangesToRoutine;
	}

	public String getHasSeenTheVetForHavingAccidents() {
		return this.hasSeenTheVetForHavingAccidents;
	}

	public void updateHasSeenTheVetForHavingAccidents(
			String hasSeenTheVetForHavingAccidents) {
		this.hasSeenTheVetForHavingAccidents = hasSeenTheVetForHavingAccidents;
	}

	public String getWasAccidentProblemSolved() {
		return this.wasAccidentProblemSolved;
	}

	public void updateWasAccidentProblemSolved(String wasAccidentProblemSolved) {
		this.wasAccidentProblemSolved = wasAccidentProblemSolved;
	}
}
