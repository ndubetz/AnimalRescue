package model.catHistory;

import java.util.LinkedList;

/**
 * GuardianInformation is a container for the contact info and ownership of the
 * previous owner of a cat.
 */

public class GuardianInformation {
	private String guardianName;
	private String guardianPhoneNumber;
	private String guardianAddress;
	private String guardianEmail;

	private String durationOfOwnership;
	private LinkedList<String> reasonsForGivingUp;
	private String howWasTheCatRaised;
	private String whereWasTheCatKept;
	private final String howDoesItGetOutside;

	public GuardianInformation(String guardianName, String guardianPhoneNumber,
			String guardianAddress, String guardianEmail, String duration,
			LinkedList<String> reasons, String howWasTheCatRaised,
			String whereWasTheCatKept, String howDoesItGetOutside) {
		this.guardianName = guardianName;
		this.guardianPhoneNumber = guardianPhoneNumber;
		this.guardianAddress = guardianAddress;
		this.guardianEmail = guardianEmail;

		this.durationOfOwnership = duration;
		this.reasonsForGivingUp = reasons;
		this.howWasTheCatRaised = howWasTheCatRaised;
		this.whereWasTheCatKept = whereWasTheCatKept;
		this.howDoesItGetOutside = howDoesItGetOutside;
	}

	public void updateGuardianName(String name) {
		this.guardianName = name;
	}

	public void updateGuardianPhoneNumber(String phoneNumber) {
		this.guardianPhoneNumber = phoneNumber;
	}

	public void updateGuardianAddress(String address) {
		this.guardianAddress = address;
	}

	public void updateGuardianEmail(String email) {
		this.guardianEmail = email;
	}

	public void updateDurationOfOwnership(String duration) {
		this.durationOfOwnership = duration;
	}

	public void updateReasonsForGivingUp(LinkedList<String> reasons) {
		this.reasonsForGivingUp = reasons;
	}

	public void updateHowWasTheCatRaised(String howWasTheCatRaised) {
		this.howWasTheCatRaised = howWasTheCatRaised;
	}

	public void updateWhereWasTheCatKept(String whereWasTheCatKept) {
		this.whereWasTheCatKept = whereWasTheCatKept;
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

	public String getWhoLetsItOut() {
		return whoLetsItOut;
	}

	public void updateWhoLetsItOut(String whoLetsItOut) {
		this.whoLetsItOut = whoLetsItOut;
	}
}
