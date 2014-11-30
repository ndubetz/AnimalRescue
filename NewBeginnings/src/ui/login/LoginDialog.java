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
class LoginDialog extends JDialog {

	protected LoginDialog() {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
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

		JTextField jTextField = new JTextField(15);
		constraints.gridy = 0;
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		usernamePasswordPanel.add(jTextField, constraints);

		JPasswordField jPasswordField = new JPasswordField(15);
		constraints.gridy = 1;
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.LINE_END;
		usernamePasswordPanel.add(jPasswordField, constraints);

		this.add(usernamePasswordPanel, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(201, 226, 233));
		JButton loginButton = new JButton("Login");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(loginButton);
		buttonPanel.add(cancelButton);
		this.add(buttonPanel, BorderLayout.CENTER);

	}
}
