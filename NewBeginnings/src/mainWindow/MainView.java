package mainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainView{

	private JButton addNewCatButton;
	private JFrame frame;
	private JScrollPane scrollPane;
	private JPanel panelToContainThemAll;
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

	public void addComponentsToPane() {
		panelToContainThemAll = new JPanel();
		
		this.panelToContainThemAll.setLayout(new BorderLayout());

		final JMenuBar menuBar = createTheMenuBar();

		final JPanel topPanel = createTopPanel();
		
		this.hostPanel = makeBottomPanel();
		
		setPanelToScrollPane(this.hostPanel);
		
		this.frame.setJMenuBar(menuBar);
		
		this.panelToContainThemAll.add(topPanel, BorderLayout.PAGE_START);
		this.frame.getContentPane().add(panelToContainThemAll);
	}

	private JMenuBar createTheMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		final JMenu fileMenu = new JMenu("File");
		final JMenu viewModeMenu = new JMenu("View Mode");
		menuBar.add(fileMenu);
		menuBar.add(viewModeMenu);
		menuBar.setBackground(new Color(252, 245, 235));	
		 exitAction = new JMenuItem("Exit");
		 formAction = new JMenuItem("Forms View");
		 dataBaseAction = new JMenuItem("DataBase View");
		
		viewModeMenu.add(formAction);
		viewModeMenu.add(dataBaseAction);
		fileMenu.add(exitAction);
		
		return menuBar;
	}

	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		final JPanel searchPanel = createSearchPanel();
		
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));
		topPanel.setPreferredSize(new Dimension(1000, 100));
		topPanel.setBackground(new Color(47, 140, 171));
		this.addNewCatButton = new JButton("Add New Cat");
		
		topPanel.add(searchPanel);
		topPanel.add(this.addNewCatButton);
		
		return topPanel;
	}

	private JPanel createSearchPanel() {
		JPanel searchPanel = new JPanel();
		final JTextField searchBar = new JTextField("Search For A Cat");
		
		final JButton searchButton = new JButton("Search");
		
		searchPanel.setBackground(new Color(47, 140, 171));
	
		searchBar.setPreferredSize(new Dimension(150, 20));
		searchPanel.add(searchBar);
		searchPanel.add(searchButton);
		
		return searchPanel;
	}

	private void setPanelToScrollPane(JPanel bottomPanel) {
		this.scrollPane = new JScrollPane(bottomPanel);
		this.panelToContainThemAll.add(this.scrollPane, BorderLayout.CENTER);
	}

	public void changePanelOnScrollPane(JPanel bottomPanel) {
		this.previousPanel = this.hostPanel;
		this.hostPanel = bottomPanel;
		
		this.panelToContainThemAll.remove(this.scrollPane);
		
		setPanelToScrollPane(this.hostPanel);
		this.panelToContainThemAll.validate();
	}

	private JPanel makeBottomPanel() {
		JPanel bottomPanel = new JPanel();
		
		bottomPanel.setBackground(new Color(201, 226, 233));

		return bottomPanel;
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