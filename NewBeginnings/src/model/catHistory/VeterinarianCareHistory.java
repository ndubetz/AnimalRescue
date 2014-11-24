package model.catHistory;

public class VeterinarianCareHistory {
	private boolean hasBeenSterilized;
	private boolean hasBeenVaccinatedThisYear;
	private boolean hasBeenTestedFiVLeuk;
	private String vetName;
	private String vetPhoneNumber;

	public VeterinarianCareHistory(boolean hasBeenSterilized,
			boolean hasBeenVaccinatedThisYear, boolean hasBeenTestedFiVLeuk,
			String vetName, String vetPhoneNumber) {
		this.hasBeenSterilized = hasBeenSterilized;
		this.hasBeenVaccinatedThisYear = hasBeenVaccinatedThisYear;
		this.hasBeenTestedFiVLeuk = hasBeenTestedFiVLeuk;
		this.vetName = vetName;
		this.vetPhoneNumber = vetPhoneNumber;
	}

	public boolean isHasBeenSterilized() {
		return this.hasBeenSterilized;
	}

	public void updateHasBeenSterilized(boolean hasBeenSterilized) {
		this.hasBeenSterilized = hasBeenSterilized;
	}

	public boolean isHasBeenVaccinatedThisYear() {
		return this.hasBeenVaccinatedThisYear;
	}

	public void updateHasBeenVaccinatedThisYear(
			boolean hasBeenVaccinatedThisYear) {
		this.hasBeenVaccinatedThisYear = hasBeenVaccinatedThisYear;
	}

	public boolean isHasBeenTestedFiVLeuk() {
		return this.hasBeenTestedFiVLeuk;
	}

	public void updateHasBeenTestedFiVLeuk(boolean hasBeenTestedFiVLeuk) {
		this.hasBeenTestedFiVLeuk = hasBeenTestedFiVLeuk;
	}

	public String getVetName() {
		return this.vetName;
	}

	public void updateVetName(String vetName) {
		this.vetName = vetName;
	}

	public String getVetPhoneNumber() {
		return this.vetPhoneNumber;
	}

	public void updateVetPhoneNumber(String vetPhoneNumber) {
		this.vetPhoneNumber = vetPhoneNumber;
	}
}
