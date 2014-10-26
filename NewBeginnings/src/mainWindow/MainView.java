package mainWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

public class MainView{

	private JButton addNewCatButton;
	private JFrame frame;
	private JScrollPane scrollPane;
<<<<<<< HEAD
	private JPanel panelToContainThemAll;
=======
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git
	private JPanel hostPanel;
	private JPanel previousPanel = new JPanel();

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

	private void addComponentsToPane() {
		panelToContainThemAll = new JPanel();
		this.panelToContainThemAll.setLayout(new BorderLayout());

		final JMenuBar menuBar = createTheMenuBar();

		final JPanel topPanel = createTopPanel();

<<<<<<< HEAD
		final JPanel bottomPanel = makeBottomPanel();
		
		this.hostPanel = makeBottomPanel();
		bottomPanel.add(this.hostPanel);
		
		setPanelToScrollPane(bottomPanel);
		
		this.frame.setJMenuBar(menuBar);
		
		this.panelToContainThemAll.add(topPanel, BorderLayout.PAGE_START);
		this.frame.getContentPane().add(panelToContainThemAll);
	}
=======
		this.hostPanel = makeBottomPanel();
		setPanelToScrollPane(this.hostPanel);
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git

	private JMenuBar createTheMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		final JMenu fileMenu = new JMenu("File");
		final JMenu formMenu = new JMenu("Forms");
		menuBar.add(fileMenu);
		menuBar.add(formMenu);
		menuBar.setBackground(new Color(252, 245, 235));	
		final JMenuItem exitAction = new JMenuItem("Exit");
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
<<<<<<< HEAD
		this.panelToContainThemAll.remove(this.scrollPane);
		this.previousPanel = this.hostPanel;
		this.hostPanel = bottomPanel;
		setPanelToScrollPane(this.hostPanel);
		this.panelToContainThemAll.validate();
=======
		this.frame.getContentPane().remove(this.scrollPane);
		this.previousPanel = this.hostPanel;
		this.hostPanel = bottomPanel;
		setPanelToScrollPane(this.hostPanel);
		this.frame.validate();
>>>>>>> branch 'master' of https://github.com/ndubetz/AnimalRescue.git
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

	public void addController(MainController controller) {
		System.out.println("Adding Controller");
		this.addNewCatButton.addActionListener(controller);
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public static class CloseListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}

	protected JPanel getPreviousPanel() {
		return this.previousPanel;
	}

	protected JButton getAddNewCatButton() {
		return this.addNewCatButton;
	}
}
