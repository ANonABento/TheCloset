package ICSProject.TheCloset;

public class profile {
    String username;
    String password;
    String orientation;
    statusBadge status;
    String identity;

    public profile(String username, String password, String orientation, statusBadge status, String identity) {
        this.username = username;
        this.password = password;
        this.orientation = orientation;
        this.identity = identity;
        this.status = status;
    }
    public profile(String username, String password, statusBadge status, String identity) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = identity;
        this.status = status;
    }
    public profile(String username, String password, String orientation, statusBadge status) {
        this.username = username;
        this.password = password;
        this.orientation = orientation;
        this.identity = "Questioning";
        this.status = status;
    }
    public profile(String username, String password, statusBadge status) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = "Questioning";
        this.status = status;
    }
    
}