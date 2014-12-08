package MainWindow;

import static org.junit.Assert.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import mainWindow.MainView;

import org.junit.Test;

public class MainViewTest {
	@Test
	public void testFrame() throws Exception {
		MainView mainView = new MainView();
		JFrame frame = mainView.getFrame();
		assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
		assertEquals(new Dimension(800,600), frame.getSize());
		assertFalse(frame.isResizable());
		assertEquals(new Point(200, 25), frame.getLocation());
		assertTrue(frame.isVisible());		
	}
	
	@Test
	public void testMenuBar() throws Exception {
		MainView mainView = new MainView();
		JFrame frame = mainView.getFrame();
		JMenuBar jMenuBar = frame.getJMenuBar();
		assertEquals(2, jMenuBar.getComponentCount());
		assertEquals(MainView.GRAY, jMenuBar.getBackground());
	}
}
