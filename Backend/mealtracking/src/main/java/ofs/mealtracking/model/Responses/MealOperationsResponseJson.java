package ofs.mealtracking.model.Responses;

public class MealOperationsResponseJson {
    private String statusCode;
    private String statusMessage;
    private String mealId;
    public String getMealId() {
        return mealId;
    }
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    public MealOperationsResponseJson(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
    public MealOperationsResponseJson() {
    }
    
}
