package mainWindow;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainView implements java.util.Observer{

	private JButton addNewCatButton;


	public MainView(String frameName) {
		JFrame frame = new JFrame("New Beginnings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentsToPane(frame);
		
		frame.setSize(1000, 700);
		frame.setLocation(200, 200);
		frame.setVisible(true);	
		
	}
	
	private void addComponentsToPane(JFrame frame) {
		JTextField searchBar = new JTextField("Search For A Cat");
		addNewCatButton = new JButton("Add New Cat");
		
		JPanel topPanel = new JPanel();
		
		topPanel.setLayout( new FlowLayout(FlowLayout.CENTER, 360, 20) );
		JPanel bottomPanel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(bottomPanel);
		
		JMenuBar menuBar = new JMenuBar();
		
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		
		JMenuItem exitAction = new JMenuItem("Exit");
		fileMenu.add(exitAction);
		exitAction.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {				
				System.exit(0);
			}
		});	
		
		for(int i = 0; i < 100; i++){
			JLabel label = new JLabel("CAT!!!!!!");
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.PAGE_AXIS));
			bottomPanel.add(label);
		}

		frame.setJMenuBar(menuBar);
		
		topPanel.setBackground(Color.RED);
		bottomPanel.setBackground(Color.GREEN);
		
		
		
		topPanel.setPreferredSize(new Dimension(1000, 100));
		searchBar.setPreferredSize(new Dimension(150, 20));
		
		
		topPanel.add(searchBar);
		topPanel.add(addNewCatButton);
		
		frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("I was called");
	}
	
	public void addController(MainController controller){
		System.out.println("Adding Controller");
		addNewCatButton.addActionListener(controller);
	}
	
	public static class CloseListener extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

}
