package ofs.mealtracking.model.Requests;


public class SignInRequestJson {

    private String username;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;

    public SignInRequestJson() {
    }
    
    public SignInRequestJson(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}