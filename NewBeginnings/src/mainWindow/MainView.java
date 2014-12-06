package mainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import searchResults.SearchResultsViewController;
import ui.login.LoginHandler;

public class MainView {

	private JButton addNewCatButton;
	private JButton searchButton;
	private JTextField searchBar;
	private SearchResultsViewController searchResults;
	private JFrame frame;
	private JScrollPane scrollPane;
	private JPanel panelToContainThemAll;
	private JPanel hostPanel;
	private final JPanel previousPanel = new JPanel();
	private JMenuItem exitAction;
	private JMenuItem formAction;
	private JMenuItem dataBaseAction;
	private JButton loginButton;
	private final Color BLUE = new Color(47, 140, 171);
	private final Color GRAY = new Color(252, 245, 235);
	private final Color LIGHT_BLUE = new Color(201, 226, 233);

	public MainView() {
		build();
	}

	private void build() {
		this.frame = new JFrame("New Beginnings");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponentsToPane();

		this.frame.setSize(800, 600);
		// this.frame.setMaximumSize(new Dimension(800, 600));
		this.frame.setResizable(false);
		this.frame.setLocation(200, 25);
		this.frame.setVisible(true);
		this.addNewCatButton.requestFocus();
	}

	public void addComponentsToPane() {
		this.panelToContainThemAll = new JPanel();

		this.panelToContainThemAll.setLayout(new BorderLayout());

		final JMenuBar menuBar = createTheMenuBar();

		final JPanel topPanel = createTopPanel();

		this.hostPanel = makeBottomPanel();

		setPanelToScrollPane(this.hostPanel);

		this.frame.setJMenuBar(menuBar);

		this.panelToContainThemAll.add(topPanel, BorderLayout.PAGE_START);
		this.frame.getContentPane().add(this.panelToContainThemAll);
	}

	private JMenuBar createTheMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		final JMenu fileMenu = new JMenu("File");
		final JMenu viewModeMenu = new JMenu("View Mode");
		menuBar.add(fileMenu);
		menuBar.add(viewModeMenu);
		menuBar.setBackground(this.GRAY);
		this.exitAction = new JMenuItem("Exit");

		ButtonGroup group = new ButtonGroup();
		this.formAction = new JRadioButtonMenuItem("Forms View");
		this.dataBaseAction = new JRadioButtonMenuItem("DataBase View");
		this.dataBaseAction.setSelected(true);

		group.add(this.formAction);
		group.add(this.dataBaseAction);
		viewModeMenu.add(this.formAction);
		viewModeMenu.add(this.dataBaseAction);
		fileMenu.add(this.exitAction);

		return menuBar;
	}

	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		final JPanel searchPanel = createSearchPanel();
		final JPanel buttonPanel = createButtonPanel();

		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 190, 25));
		topPanel.setPreferredSize(new Dimension(800, 100));
		topPanel.setBackground(this.BLUE);

		topPanel.add(searchPanel);
		topPanel.add(buttonPanel);

		return topPanel;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		this.addNewCatButton = new JButton("Add New Cat");
		this.loginButton = LoginHandler.singleton().getLoginButton();
		buttonPanel.setBackground(this.BLUE);

		buttonPanel.add(this.addNewCatButton);
		buttonPanel.add(this.loginButton);

		return buttonPanel;
	}

	private JPanel createSearchPanel() {
		JPanel searchPanel = new JPanel();
		this.searchBar = new JTextField(MainModel.C_DefaultSearchText);

		this.searchButton = new JButton("Search");

		searchPanel.setBackground(this.BLUE);

		this.searchBar.setPreferredSize(new Dimension(120, 20));
		searchPanel.add(this.searchBar);
		searchPanel.add(this.searchButton);

		return searchPanel;
	}

	private void setPanelToScrollPane(JPanel bottomPanel) {
		bottomPanel.setBackground(this.LIGHT_BLUE);
		this.scrollPane = new JScrollPane(bottomPanel);
		this.panelToContainThemAll.add(this.scrollPane, BorderLayout.CENTER);
	}

	public void changePanelOnScrollPane(JPanel bottomPanel) {
		// this.previousPanel = this.hostPanel;
		this.hostPanel = bottomPanel;

		this.panelToContainThemAll.remove(this.scrollPane);

		setPanelToScrollPane(this.hostPanel);
		this.panelToContainThemAll.validate();
	}

	private JPanel makeBottomPanel() {
		JPanel bottomPanel = new JPanel();

		return bottomPanel;
	}

	public void addController(MainController controller) {
		this.addNewCatButton.addActionListener(controller);
		this.searchButton.addActionListener(controller);
		this.dataBaseAction.addActionListener(controller);
		this.exitAction.addActionListener(controller);
		this.formAction.addActionListener(controller);
		this.searchBar.addActionListener(controller);
		this.searchBar.addFocusListener(controller);
		this.loginButton.addActionListener(controller);
	}

	public void buildDataBaseView() {
		this.frame.remove(this.panelToContainThemAll);
		this.addComponentsToPane();
		this.frame.validate();
	}

	public void buildFormsView(JPanel mainPanel) {
		this.frame.remove(this.panelToContainThemAll);
		this.panelToContainThemAll = mainPanel;
		this.frame.add(this.panelToContainThemAll);
		this.frame.validate();
	}

	public JFrame getFrame() {
		return this.frame;
	}

	protected JPanel getPreviousPanel() {
		return this.previousPanel;
	}

	protected JPanel getHostPanel() {
		return this.hostPanel;
	}

	protected JButton getAddNewCatButton() {
		return this.addNewCatButton;
	}

	protected JButton getSearchButton() {
		return this.searchButton;
	}

	protected JTextField getSearchBar() {
		return this.searchBar;
	}

	public JMenuItem getExitActionMenuItem() {
		return this.exitAction;
	}

	public JMenuItem getFormActionMenuItem() {
		return this.formAction;
	}

	public JMenuItem getDataBaseActionMenuItem() {
		return this.dataBaseAction;
	}

	public SearchResultsViewController getSearchResults() {
		return this.searchResults;
	}

	public void setSearchResults(SearchResultsViewController searchResults) {
		this.searchResults = searchResults;
	}

	protected JButton getLoginButton() {
		return this.loginButton;
	}

}