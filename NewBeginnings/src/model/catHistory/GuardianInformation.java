package model.catHistory;

import java.util.LinkedList;

/**
 * GuardianInformation is a container for the contact info and ownership of the
 * previous owner of a cat.
 */

public class GuardianInformation {
	private final String guardianName;
	private final String guardianPhoneNumber;
	private final String guardianAddress;
	private final String guardianEmail;

	private String durationOfOwnership;
	private LinkedList<String> reasonsForGivingUp;
	private String howWasTheCatRaised;
	private String whereWasTheCatKept;

	public GuardianInformation(String guardianName, String guardianPhoneNumber,
			String guardianAddress, String guardianEmail) {
		this.guardianName = guardianName;
		this.guardianPhoneNumber = guardianPhoneNumber;
		this.guardianAddress = guardianAddress;
		this.guardianEmail = guardianEmail;
	}

	public String getGuardianName() {
		return this.guardianName;
	}

	public String getGuardianPhoneNumber() {
		return this.guardianPhoneNumber;
	}

	public String getGuardianAddress() {
		return this.guardianAddress;
	}

	public String getGuardianEmail() {
		return this.guardianEmail;
	}

	public String getDurationOfOwnership() {
		return this.durationOfOwnership;
	}

	public String getHowWasTheCatRaised() {
		return this.howWasTheCatRaised;
	}

	public LinkedList<String> getReasonsForGivingUp() {
		return this.reasonsForGivingUp;
	}

	public String getWhereWasTheCatKept() {
		return this.whereWasTheCatKept;
	}
}
