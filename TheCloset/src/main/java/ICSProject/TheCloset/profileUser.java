package ICSProject.TheCloset;

public class profileUser extends profile {
	
	int reports;
	int reportsMade;
	statusBadge selfStatus;
	
	public profileUser(String username, String password, statusBadge status) {
		super(username, password, status);
		// TODO Auto-generated constructor stub
	}
	
	public void toStatus(String statusName) {
		
	}
	
	@Override
	public String toString() {
		return username + "/" + password + "/" + orientation + "/" + identity + "/" + selfStatus.toString();
	}
}
