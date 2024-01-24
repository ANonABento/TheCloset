package ICSProject.TheCloset;

public class profile {
    String username;
    String password;
    String orientation;
    int status;
    String identity;

    public profile(String username, String password, String orientation, int status, String identity) {
        this.username = username;
        this.password = password;
        this.orientation = orientation;
        this.identity = identity;
        this.status = status;
    }
    public profile(String username, String password, int status, String identity) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = identity;
        this.status = status;
    }
    public profile(String username, String password, String orientation, int status) {
        this.username = username;
        this.password = password;
        this.orientation = orientation;
        this.identity = "Questioning";
        this.status = status;
    }
    public profile(String username, String password, int status) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = "Questioning";
        this.status = status;
    }
    
}