package MainWindow;

import static org.junit.Assert.*;
import mainWindow.MainController;
import mainWindow.MainModel;
import mainWindow.MainView;

import org.junit.Test;

public class MainControllerTest {
	@Test
	public void testConstructorAndGetters() throws Exception {
		MainView mainView = new MainView();
		MainModel mainModel = new MainModel();
		MainController mainController = new MainController(mainModel, mainView);
	
		assertSame(mainView, mainController.getView());
		assertSame(mainModel, mainController.getModel());
		assertSame(mainView.getFrame(), mainController.getFrame());
	}
}
