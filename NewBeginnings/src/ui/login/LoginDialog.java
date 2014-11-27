package ui.login;

import javax.swing.JDialog;

/**
 * LoginDialog is the user interaction element for logging in. It is package
 * protected and interacts only with the LoginHandler
 * 
 */
class LoginDialog extends JDialog {

	protected LoginDialog() {
		this.setModalityType(ModalityType.APPLICATION_MODAL);
	}
}
