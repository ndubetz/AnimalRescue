package hostView;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;

import model.Cat;

import org.junit.Test;

import database.FakeAnimalDatabase;

public class AnimalInfoViewTest {
	@Test
	public void testConstructor() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat(), new FakeAnimalDatabase());
		assertEquals(4, animalInfoView.getComponents().length);
	}

	@Test
	public void testUpperPanel() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat(), new FakeAnimalDatabase());
		assertEquals(new Dimension(500, 60), animalInfoView
				.getUpperControlPanel().getMaximumSize());
		assertEquals(5,
				animalInfoView.getUpperControlPanel().getComponents().length);
	}

	@Test
	public void testBasicInfoPanel() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat(), new FakeAnimalDatabase());
		Component[] components = animalInfoView.getBasicInfoPanel()
				.getComponents();
		assertEquals(18, components.length);
		JTextField textfield1 = (JTextField) components[5];
		JTextField textfield2 = (JTextField) components[15];
		JTextField textfield3 = (JTextField) components[17];
		String expectedBirthdate = animalInfoView.getCat().getBirthdateAsDateFormattedString();
		String expectedArrivaldate = animalInfoView.getCat().getArrivalDateAsDateFormattedString();
		String expectedDepaturedate = animalInfoView.getCat().getDepartureDateAsDateFormattedString();
		assertEquals(expectedBirthdate, textfield1.getText());
		assertEquals(expectedArrivaldate, textfield2.getText());
		assertEquals(expectedDepaturedate, textfield3.getText());
	}
}
