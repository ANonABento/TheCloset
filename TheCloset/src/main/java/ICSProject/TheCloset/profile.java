package ICSProject.TheCloset;

public class profile {
    String username;
    String password;
    String orientation;
    int status;
    String identity;

    public profile(String username, String password, String genderOrientation, int status, String genderIdentity) {
        this.username = username;
        this.password = password;
        this.orientation = genderOrientation;
        this.identity = genderIdentity;
        this.status = status;
    }
    public profile(String username, String password, int status, String genderIdentity) {
        this.username = username;
        this.password = password;
        this.orientation = "Questioning";
        this.identity = genderIdentity;
        this.status = status;
    }
    public profile(String username, String password, String genderOrientation, int status) {
        this.username = username;
        this.password = password;
        this.orientation = genderOrientation;
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