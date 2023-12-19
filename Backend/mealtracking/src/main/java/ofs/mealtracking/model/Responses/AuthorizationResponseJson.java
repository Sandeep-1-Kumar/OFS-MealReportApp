package ofs.mealtracking.model.Responses;

public class AuthorizationResponseJson {
    
    private String username;
    private String statusCode;
    private String statusMessage;
    private long id;

    public String getUsername() {
        return username;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AuthorizationResponseJson(String statusCode,
    String statusMessage,String username,Long id){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.username = username;
        this.id = id;
    }

    public AuthorizationResponseJson(String statusCode,String statusMessage){
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    
}