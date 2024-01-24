package ICSProject.TheCloset;

/**
 * This class represents a status badge for user status in the application.
 */
public class statusBadge {

    //variables
    private String status;
    private static int reported;

    /**
     * Constructor to create a status badge with the given status.
     *
     * @param status The status to be displayed on the badge.
     */
    public statusBadge(String status) {
        this.status = status;
    }
    /**
     * Default constructor with no values
     *
     */
    public statusBadge() {
        this.status = "member";
    }
    
    /**
     * Increases the reported count for the user status badge.
     * If the user is reported more than 10 times, sets the status to "banned".
     */
    public void statusAddCount() {
    	//if reported
    	reported++;
    	//if reported more than like 10 times
    	if (reported > 10) {
    		setStatus("banned");
    	}
    }

    /**
     * Method to get the status displayed on the badge.
     *
     * @return The status displayed on the badge.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to set the status displayed on the badge.
     *
     * @param status The new status to be displayed on the badge.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the status badge.
     *
     * @return A string representation of the status badge.
     */
    @Override
    public String toString() {
        return status;
    }
}

