package ICSProject.TheCloset;

public class profileUser extends profile {
	
	int reports;
	int reportsMade;
	statusBadge selfStatus;
	
	public profileUser() {
		super("", "", null);
		selfStatus = new statusBadge();
	}
	
	public profileUser(String username, String password, statusBadge status) {
		super(username, password, status);
		selfStatus = new statusBadge();
	}
	
	public void toStatus(String statusName) {
		
	}
	
	@Override
	public String toString() {
		return username + "/" + password + "/" + orientation + "/" + identity + "/" + selfStatus.toString();
	}
}
