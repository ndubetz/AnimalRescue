package ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * LoginHandler handles all interaction regarding user login. Currently, only
 * admins can login. It holds a map of usernames to passwords and is responsible
 * for holding login status and opening up the login dialog. It is a singleton
 * object
 * 
 */
public class LoginHandler implements ActionListener, KeyListener {

	private boolean loginState;
	private final HashMap<String, String> passwordMap;

	private static LoginHandler SINGLETON = new LoginHandler();
	private LoginDialog loginDialog;
	private final JButton loginButton;
	private final JLabel loginStatusLabel;

	private LoginHandler() {
		this.loginState = false;
		this.passwordMap = new LinkedHashMap<String, String>();
		this.loginButton = new JButton("Login");
		this.loginStatusLabel = new JLabel("Logged out");

		// add default password for prototyping purposes
		this.passwordMap.put("admin", "Admin");

	}

	private void buildLoginDialog() {
		SINGLETON.loginDialog = new LoginDialog();
		addListenersToDialog();
	}

	private void addListenersToDialog() {
		SINGLETON.loginDialog.getLoginButton().addActionListener(this);
		SINGLETON.loginDialog.getCancelButton().addActionListener(this);

		// key listener for hitting enter to validate password
		SINGLETON.loginDialog.getPasswordField().addKeyListener(this);

	}

	// returns singleton object
	public static LoginHandler singleton() {
		return SINGLETON;
	}

	public boolean isLoggedIn() {
		return SINGLETON.loginState;
	}

	public void openLoginDialog() {
		buildLoginDialog();
		SINGLETON.loginDialog.setVisible(true);
	}

	public void addAdmin(String username, String password) {
		SINGLETON.passwordMap.put(username, password);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.loginDialog.getLoginButton()) {
			validatePassword();
		} else if (e.getSource() == this.loginDialog.getCancelButton()) {
			SINGLETON.loginDialog.dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == SINGLETON.loginDialog.getPasswordField()
				&& e.getKeyCode() == KeyEvent.VK_ENTER) {
			validatePassword();
		}
	}

	private void validatePassword() {
		String username = SINGLETON.loginDialog.getUsernameField().getText();
		if (SINGLETON.passwordMap.containsKey(username)) {
			String passwordFromMap = SINGLETON.passwordMap.get(username
					.toLowerCase());
			String passwordField = new String(SINGLETON.loginDialog
					.getPasswordField().getPassword());
			if ((passwordFromMap.hashCode()) == (passwordField.hashCode())) {
				SINGLETON.setLoginState(true);
				SINGLETON.loginDialog.dispose();
			}
		} else {
			SINGLETON.loginDialog.getUsernameField().setText("");
			SINGLETON.loginDialog.getPasswordField().setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void setLoginState(boolean loginState) {
		SINGLETON.loginState = loginState;
		if (SINGLETON.loginState) {
			JOptionPane.showMessageDialog(null, "You are now logged in.");
			this.loginButton.setText("Logout");
			this.loginStatusLabel.setText("Logged in");
		} else {
			JOptionPane.showMessageDialog(null, "You are now logged out.");
			this.loginButton.setText("Login");
			this.loginStatusLabel.setText("Logged out");
		}
	}

	public JButton getLoginButton() {
		return this.loginButton;
	}

	public JLabel getLoginStatusLabel() {
		return this.loginStatusLabel;
	}
}
