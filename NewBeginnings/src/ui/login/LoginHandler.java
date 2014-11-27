package ui.login;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LoginHandler handles all interaction regarding user login. Currently, only
 * admins can login. It holds a map of usernames to passwords and is responsible
 * for holding login status and opening up the login dialog. It is a singleton
 * object
 * 
 */
public class LoginHandler {

	private final boolean loginState;
	private final HashMap<String, String> passwordMap;

	private static LoginHandler SINGLETON = new LoginHandler();

	private LoginHandler() {
		this.loginState = false;
		this.passwordMap = new LinkedHashMap<String, String>();
		buildLoginDialog();
	}

	private void buildLoginDialog() {

	}

	// returns singleton object
	public static LoginHandler singleton() {
		return SINGLETON;
	}

	public boolean getLoginState() {
		return SINGLETON.loginState;
	}

	public void openLoginDialog() {

	}

	public void addAdmin(String username, String password) {
		SINGLETON.passwordMap.put(username, password);
	}

}
