package mainWindow;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.BoxLayout;

public class MainView implements java.util.Observer{

	private Button button;
//	private String name;

	public MainView(String frameName) {
		Frame frame = new Frame(frameName);
		frame.add("North", new Label("Add Cat"));
		
		TextField nameTextField = new TextField();
		TextField ageTextField = new TextField();
		
		Panel textFieldPanel = new Panel();
		textFieldPanel.setLayout( new BoxLayout(textFieldPanel, BoxLayout.X_AXIS));
		textFieldPanel.setPreferredSize(new Dimension(700, 100));
		textFieldPanel.add(nameTextField);
		textFieldPanel.add(ageTextField);
		frame.add("Center", textFieldPanel);
		
		Panel panel = new Panel();
		button = new Button("Add");
		panel.add(button);
		frame.add("South", panel);
		
		frame.addWindowListener(new CloseListener());
		frame.setSize(1000, 700);
		frame.setLocation(200, 200);
		frame.setVisible(true);	
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("I was called");
	}
	
	public void addController(MainController controller){
		System.out.println("Adding Controller");
		button.addActionListener(controller);
	}
	
	public static class CloseListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

}
