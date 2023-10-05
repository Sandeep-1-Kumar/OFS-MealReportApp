package ofs.mealtracking.model.Responses;

public class ReviewMealCountResponse {
    
    private String mealId;
    public String getMealId() {
        return mealId;
    }
    public void setMealId(String mealId) {
        this.mealId = mealId;
    }
    private String date;
    private String deliveryStatus;
    private String mealtemperature;
    private String mealType;
    private String program;
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
    private String mealServiceTime;
    private String mealsAvailableFromPreviousDay;
    private String mealsDelivered;
    private String mealsAvailableToStartMealService;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    public String getMealtemperature() {
        return mealtemperature;
    }
    public void setMealtemperature(String mealtemperature) {
        this.mealtemperature = mealtemperature;
    }
    public String getMealType() {
        return mealType;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    public String getMealServiceTime() {
        return mealServiceTime;
    }
    public void setMealServiceTime(String mealServiceTime) {
        this.mealServiceTime = mealServiceTime;
    }
    public String getMealsAvailableFromPreviousDay() {
        return mealsAvailableFromPreviousDay;
    }
    public void setMealsAvailableFromPreviousDay(String mealsAvailableFromPreviousDay) {
        this.mealsAvailableFromPreviousDay = mealsAvailableFromPreviousDay;
    }
    public String getMealsDelivered() {
        return mealsDelivered;
    }
    public void setMealsDelivered(String mealsDelivered) {
        this.mealsDelivered = mealsDelivered;
    }
    public String getMealsAvailableToStartMealService() {
        return mealsAvailableToStartMealService;
    }
    public void setMealsAvailableToStartMealService(String mealsAvailableToStartMealService) {
        this.mealsAvailableToStartMealService = mealsAvailableToStartMealService;
    }
}
