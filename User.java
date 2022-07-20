package eShop;

public class User {
	
	private String username;
	private String password;
	private boolean isLoggedIn;
	
	//user constructor
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		isLoggedIn = false;
	}
	
	//username getter
	public String getUsername() {
		return this.username;
	}
	
	//password getter
	public String getPassword() {
		return this.password;
	}
	
	//login status getter
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	
	//change customer's password. has to match both username and password in security manners
	public boolean changePasssword(String username, String password, String newPassword) {
		if(this.getUsername().equals(username) && this.getPassword().equals(password) && newPassword.matches("[a-zA-Z0-9]*")) {
			this.password = newPassword;
			return true;
			}
		return false;
		}
	
	public void setIsLoggedIn(boolean status) {
		this.isLoggedIn = status;
	}
	
	//login
	public boolean login(String username, String password) {
		if(this.username.equals(username) && this.password.equals(password)) {
			this.setIsLoggedIn(true);
			return true;
		}
		return false;
	}
	
	//logout
	public void logout() {
		this.setIsLoggedIn(false);
	}
}
