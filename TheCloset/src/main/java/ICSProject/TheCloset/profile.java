package ICSProject.TheCloset;

/**
 * A user profile that stores their information
 * username, password, orientation, status, and identity.
 */
public class profile {
	
	//variables for user info
    String username;
    String password;
    String orientation;
    statusBadge status;
    String identity;

    //all available constructors to build someone from
    
    /**
     * Constructs a new profile with specified values for username, password, orientation, status, and identity.
     *
     * @param username   The username of the user.
     * @param password   The password associated with the user's account.
     * @param orientation The orientation of the user.
     * @param status      The statusBadge representing the user's status.
     * @param identity    The identity of the user.
     */
    public profile(String username, String password, String orientation, statusBadge status, String identity) {
        this.username = username;
        this.password = password;
        this.orientation = orientation;
        this.identity = identity;
        this.status = status;
    }
    /**
     * Constructs a new profile with specified values for username, password, status, and identity.
     * The orientation is set to the default value "Questioning".
     *
     * @param username The username of the user.
     * @param password The password associated with the user's account.
     * @param status   The statusBadge representing the user's status.
     * @param identity The identity of the user.
     */
    public profile(String username, String password, statusBadge status, String identity) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = identity;
        this.status = status;
    }
    /**
     * Constructs a new profile with specified values for username, password, orientation, and status.
     * The identity is set to the default value "Questioning".
     *
     * @param username   The username of the user.
     * @param password   The password associated with the user's account.
     * @param orientation The orientation of the user.
     * @param status      The statusBadge representing the user's status.
     */
    public profile(String username, String password, String orientation, statusBadge status) {
        this.username = username;
        this.password = password;
        this.orientation = orientation;
        this.identity = "Questioning";
        this.status = status;
    }
    /**
     * Constructs a new profile with specified values for username, password, and status.
     * The orientation and identity are set to the default value "Questioning".
     *
     * @param username The username of the user.
     * @param password The password associated with the user's account.
     * @param status   The statusBadge representing the user's status.
     */
    public profile(String username, String password, statusBadge status) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = "Questioning";
        this.status = status;
    }
    
    /**
     * Return all info as a string.
     *
     * @return A string containing the username, password, orientation, identity, and status.
     */
    public String toString() {
		return username + "/" + password + "/" + orientation + "/" + identity + "/" + status.toString();
	}
    
}