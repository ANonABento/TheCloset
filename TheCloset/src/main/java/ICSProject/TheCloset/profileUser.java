package ICSProject.TheCloset;

/**
 * Represents a user profile with additional attributes specific to users.
 * Extends the base profile class.
 */
public class profileUser extends profile {
	
	//variable declaration
	int reports;
	int reportsMade;
	statusBadge selfStatus;
	
	//constructor with nothing and one with everything
	/**
     * Default constructor for a user profile.
     * Initializes attributes with default values.
     */
	public profileUser() {
		super("", "", null);
		selfStatus = new statusBadge();
	}
	/**
     * Constructor for a user profile with all values
     *
     * @param username User's username.
     * @param password User's password.
     * @param status   User's status badge.
     */
	public profileUser(String username, String password, statusBadge status) {
		super(username, password, status);
		selfStatus = new statusBadge();
	}
	
	/**
     * Overrides the toString method to include the user's own status.
     *
     * @return A string representation of the user profile.
     */
	@Override
	public String toString() {
		return username + "/" + password + "/" + orientation + "/" + identity + "/" + selfStatus.toString();
	}
	
	public void toStatus(String value) {
		this.selfStatus.setStatus(value);
	}
}
