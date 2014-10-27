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

public class MainView implements java.util.Observer {

	private JButton addNewCatButton;
	private JFrame frame;
	private JScrollPane scrollPane;
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
		final JTextField searchBar = new JTextField("Search For A Cat");
		this.addNewCatButton = new JButton("Add New Cat");
		final JButton searchButton = new JButton("Search");

		final JPanel topPanel = new JPanel();
		final JPanel searchPanel = new JPanel();

		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 20));

		this.hostPanel = makeBottomPanel();
		setPanelToScrollPane(this.hostPanel);

		final JMenuBar menuBar = new JMenuBar();

		final JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		final JMenuItem exitAction = new JMenuItem("Exit");
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
