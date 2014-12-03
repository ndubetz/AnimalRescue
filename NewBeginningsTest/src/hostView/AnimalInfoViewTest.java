package hostView;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Dimension;

import model.Cat;

import org.junit.Test;

public class AnimalInfoViewTest {
	@Test
	public void testConstructor() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat());
		assertEquals(4, animalInfoView.getComponents().length);
	}

	@Test
	public void testUpperPanel() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat());
		assertEquals(new Dimension(500, 60), animalInfoView
				.getUpperControlPanel().getMaximumSize());
		assertEquals(5,
				animalInfoView.getUpperControlPanel().getComponents().length);
	}

	@Test
	public void testBasicInfoPanel() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat());
		Component[] components = animalInfoView.getBasicInfoPanel()
				.getComponents();
		assertEquals(18, components.length);
	}
}
