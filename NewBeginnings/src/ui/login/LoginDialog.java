package ui.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * LoginDialog is the user interaction element for logging in. It is package
 * protected and interacts only with the LoginHandler
 * 
 */

@SuppressWarnings("serial")
class LoginDialog extends JDialog {

	private JButton loginButton;
	private JButton cancelButton;
	private JTextField usernameField;
	private JPasswordField passwordField;

	protected LoginDialog() {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setTitle("Login");
		this.setLayout(new BorderLayout());
		this.setSize(300, 130);
		this.setLocation(550, 350);
		this.setResizable(false);
		buildDialog();
	}

	private void buildDialog() {

		// modified version of the PanelFactory method that uses JPasswordField
		String[] labels = new String[] { "Username", "Password" };
		JPanel usernamePasswordPanel = new JPanel();
		usernamePasswordPanel.setLayout(new GridBagLayout());
		usernamePasswordPanel.setBackground(new Color(201, 226, 233));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		for (int i = 0; i < labels.length; i++) {
			JLabel jLabel = new JLabel();
			jLabel.setText(labels[i] + ": ");
			constraints.gridy = i;
			constraints.gridx = 0;
			constraints.anchor = GridBagConstraints.LINE_START;
			usernamePasswordPanel.add(jLabel, constraints);
		}

		this.usernameField = new JTextField(15);
		constraints.gridy = 0;
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		usernamePasswordPanel.add(this.usernameField, constraints);

		this.passwordField = new JPasswordField(15);
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		usernamePasswordPanel.add(this.passwordField, constraints);

		this.add(usernamePasswordPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(201, 226, 233));

		this.loginButton = new JButton("Login");
		this.cancelButton = new JButton("Cancel");

		buttonPanel.add(this.loginButton);
		buttonPanel.add(this.cancelButton);
		this.add(buttonPanel, BorderLayout.CENTER);

	}

	protected JButton getLoginButton() {
		return this.loginButton;
	}

	protected JButton getCancelButton() {
		return this.cancelButton;
	}

	protected JTextField getUsernameField() {
		return this.usernameField;
	}

	protected JPasswordField getPasswordField() {
		return this.passwordField;
	}
}
