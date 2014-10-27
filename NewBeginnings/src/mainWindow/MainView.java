package mainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
<<<<<<< HEAD
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainView implements java.util.Observer {

	private JButton addNewCatButton;
	private JFrame frame;
	private JScrollPane scrollPane;
	private JPanel hostPanel;
	private JPanel previousPanel = new JPanel();
	private JMenuItem exitAction;
	private JMenuItem formAction;
	private JMenuItem dataBaseAction;

	public MainView() {
		build();
	}

	private void build() {
		this.frame = new JFrame("New Beginnings");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		addComponentsToPane();

		this.frame.setSize(1000, 700);
		this.frame.setMaximumSize(new Dimension(1000, 700));
		this.frame.setLocation(200, 25);
		this.frame.setVisible(true);
	}

<<<<<<< HEAD
	public void addComponentsToPane() {
		panelToContainThemAll = new JPanel();
		
		this.panelToContainThemAll.setLayout(new BorderLayout());
=======
	private void addComponentsToPane() {
		final JTextField searchBar = new JTextField("Search For A Cat");
		this.addNewCatButton = new JButton("Add New Cat");
		final JButton searchButton = new JButton("Search");
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git

		final JPanel topPanel = new JPanel();
		final JPanel searchPanel = new JPanel();

		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));

		this.hostPanel = makeBottomPanel();
		setPanelToScrollPane(this.hostPanel);

		final JMenuBar menuBar = new JMenuBar();

		final JMenu fileMenu = new JMenu("File");
<<<<<<< HEAD
		final JMenu viewModeMenu = new JMenu("View Mode");
=======
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git
		menuBar.add(fileMenu);
<<<<<<< HEAD
		menuBar.add(viewModeMenu);
		menuBar.setBackground(new Color(252, 245, 235));	
		 exitAction = new JMenuItem("Exit");
		 formAction = new JMenuItem("Forms View");
		 dataBaseAction = new JMenuItem("DataBase View");
		
		viewModeMenu.add(formAction);
		viewModeMenu.add(dataBaseAction);
=======

		final JMenuItem exitAction = new JMenuItem("Exit");
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git
		fileMenu.add(exitAction);
		exitAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.frame.setJMenuBar(menuBar);

		menuBar.setBackground(new Color(252, 245, 235));
		topPanel.setBackground(new Color(47, 140, 171));
		searchPanel.setBackground(new Color(47, 140, 171));

		topPanel.setPreferredSize(new Dimension(1000, 100));
		searchBar.setPreferredSize(new Dimension(150, 20));
		searchPanel.add(searchBar);
		searchPanel.add(searchButton);

		topPanel.add(searchPanel);
		topPanel.add(this.addNewCatButton);

		this.frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
		this.frame.getContentPane().add(this.scrollPane, BorderLayout.CENTER);
	}

	private void setPanelToScrollPane(JPanel bottomPanel) {
		this.scrollPane = new JScrollPane(bottomPanel);
		this.frame.getContentPane().add(this.scrollPane, BorderLayout.CENTER);
	}

	public void changePanelOnScrollPane(JPanel bottomPanel) {
		this.frame.getContentPane().remove(this.scrollPane);
		this.previousPanel = this.hostPanel;
		this.hostPanel = bottomPanel;
		setPanelToScrollPane(this.hostPanel);
		this.frame.validate();
	}

	private JPanel makeBottomPanel() {
		JPanel bottomPanel = new JPanel();

		for (int i = 1; i <= 100; i++) {
			final JLabel label = new JLabel("CAT " + i);
			bottomPanel.setLayout(new BoxLayout(bottomPanel,
					BoxLayout.PAGE_AXIS));
			bottomPanel.add(label);
		}

		bottomPanel.setBackground(new Color(201, 226, 233));

		return bottomPanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("I was called");
	}

	public void addController(MainController controller) {
		System.out.println("Adding Controller");
		this.addNewCatButton.addActionListener(controller);
		this.dataBaseAction.addActionListener(controller);
		this.exitAction.addActionListener(controller);
		this.formAction.addActionListener(controller);
		
	}
	
	public void buildDataBaseView(){
		this.frame.remove(this.panelToContainThemAll);
		this.addComponentsToPane();
		this.frame.validate();
	}
	
	public void buildFormsView(JPanel mainPanel) {
		this.frame.remove(this.panelToContainThemAll);
		this.frame.add(mainPanel);
		this.frame.validate();
	}

	public JFrame getFrame() {
		return this.frame;
	}

	protected JPanel getPreviousPanel() {
		return this.previousPanel;
	}

	protected JButton getAddNewCatButton() {
		return this.addNewCatButton;
	}
	
	public JMenuItem getExitActionMenuItem() {
		return exitAction;
	}
	
	public JMenuItem getFormActionMenuItem() {
		return formAction;
	}
	
	public JMenuItem getDataBaseActionMenuItem() {
		return dataBaseAction;
	}

	
}
