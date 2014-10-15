package hostView;

import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Cat;

public class AnimalInfoViewBuilder {
	private AnimalInfoView theView;
	
	private static AnimalInfoViewBuilder SINGLETON = new AnimalInfoViewBuilder();
	
	private AnimalInfoViewBuilder(){
		
	}

	public static AnimalInfoViewBuilder singleton(){
		return SINGLETON;
	}
	
	public AnimalInfoView build(Cat cat){
		theView = new AnimalInfoView();
		
		//the build labels calls will get refactored after the first iteration or 
		//whenever more information gets added
		buildLabelAndTextfieldPair("ID: ", cat.getID(), 1);
		buildLabelAndTextfieldPair("Name: ", cat.getName(), 2);
		buildLabelAndTextfieldPair("Age: ", Integer.toString(cat.getAge()), 3);
		buildLabelAndTextfieldPair("Gender: ", cat.getGender(), 4);
		buildLabelAndTextfieldPair("Breed: ", cat.getBreed(), 5);
		buildLabelAndTextfieldPair("Hair Color: ", cat.getHairColor(), 6);
		buildLabelAndTextfieldPair("Arrival Date: ", cat.getArrivalDate().toString(), 7);
		
		if(cat.getExpectedDepartureDate() == null){			
			buildLabelAndTextfieldPair("Departure Date: ", "Not yet known", 8);
		}
		else{
			buildLabelAndTextfieldPair("Departure Date: ", cat.getExpectedDepartureDate().toString(), 8);			
		}
		
		buildLabelAndTextfieldPair("Is Fixed: ", Boolean.toString(cat.isFixed()), 9);
		
		return theView;
	}
	
	private void buildLabelAndTextfieldPair(String label, String content, int row){
		JLabel jLabel = new JLabel();
		jLabel.setText(label + ": ");
		theView.add(jLabel, 1, row);
		JTextField jTextField = new JTextField();
		jTextField.setText(content);
		theView.add(jTextField, 2, row);
	}
}
