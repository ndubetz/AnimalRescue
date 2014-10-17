package hostView;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Cat;

public class AnimalInfoViewBuilder {
	private AnimalInfoView theView;

	private static AnimalInfoViewBuilder SINGLETON = new AnimalInfoViewBuilder();

	private AnimalInfoViewBuilder() {

	}

	public static AnimalInfoViewBuilder singleton() {
		return SINGLETON;
	}

	public AnimalInfoView build(Cat cat) {
		this.theView = new AnimalInfoView();

		// the build labels calls will get refactored after the first iteration
		// or whenever more information gets added
		buildLabelAndTextfieldPair("ID", cat.getID());
		buildLabelAndTextfieldPair("Name", cat.getName());
		buildLabelAndTextfieldPair("Age", Integer.toString(cat.getAge()));
		buildLabelAndTextfieldPair("Gender", cat.getGender());
		buildLabelAndTextfieldPair("Breed", cat.getBreed());
		buildLabelAndTextfieldPair("Hair Color", cat.getHairColor());
		buildLabelAndTextfieldPair("Arrival Date", cat.getArrivalDate()
				.getTime().toString());
		if (cat.getExpectedDepartureDate() == null) {
			buildLabelAndTextfieldPair("Departure Date", "Not yet known");
		} else {
			buildLabelAndTextfieldPair("Departure Date", cat
					.getExpectedDepartureDate().getTime().toString());
		}

		buildLabelAndTextfieldPair("Is Fixed", Boolean.toString(cat.isFixed()));

		return this.theView;
	}

	private void buildLabelAndTextfieldPair(String label, String content) {
		JLabel jLabel = new JLabel();
		jLabel.setText(label + ": ");
		this.theView.add(jLabel);
		JTextField jTextField = new JTextField();
		jTextField.setText(content);
		this.theView.add(jTextField);
	}
}