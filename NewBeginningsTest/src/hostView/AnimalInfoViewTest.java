package hostView;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Dimension;

import model.Cat;

import org.junit.Before;
import org.junit.Test;

import database.FakeAnimalDatabase;
import database.IAnimalDatabase;

public class AnimalInfoViewTest {
	private IAnimalDatabase fakeDatabase;
	
	@Before
	public void setUp(){
		fakeDatabase = new FakeAnimalDatabase();
	}
	
	@Test
	public void testConstructor() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat(), fakeDatabase);
		assertEquals(4, animalInfoView.getComponents().length);
	}

	@Test
	public void testUpperPanel() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat(), fakeDatabase);
		assertEquals(new Dimension(500, 60), animalInfoView
				.getUpperControlPanel().getMaximumSize());
		assertEquals(5,
				animalInfoView.getUpperControlPanel().getComponents().length);
	}

	@Test
	public void testBasicInfoPanel() throws Exception {
		AnimalInfoView animalInfoView = new AnimalInfoView(Cat.emptyCat(), fakeDatabase);
		Component[] components = animalInfoView.getBasicInfoPanel()
				.getComponents();
		assertEquals(18, components.length);
	}
}
