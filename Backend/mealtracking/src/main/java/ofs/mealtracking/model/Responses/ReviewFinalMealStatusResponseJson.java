package ofs.mealtracking.model.Responses;

public class ReviewFinalMealStatusResponseJson {

    private String mealsAvailableFromPreviousDay;
    private String mealsDelivered;
    private String mealsAvailableToStartMealService;
    private String mealsServed;
    private String damagedMeals;
    private String expiredMeals;
    private String mealsElgibleToCarryOverToNextDay;
    public String getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
    private String statusCode;
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
    public String getMealsServed() {
        return mealsServed;
    }
    public void setMealsServed(String mealsServed) {
        this.mealsServed = mealsServed;
    }
    public String getDamagedMeals() {
        return damagedMeals;
    }
    public void setDamagedMeals(String damagedMeals) {
        this.damagedMeals = damagedMeals;
    }
    public String getExpiredMeals() {
        return expiredMeals;
    }
    public void setExpiredMeals(String expiredMeals) {
        this.expiredMeals = expiredMeals;
    }
    public String getMealsElgibleToCarryOverToNextDay() {
        return mealsElgibleToCarryOverToNextDay;
    }
    public void setMealsElgibleToCarryOverToNextDay(String mealsElgibleToCarryOverToNextDay) {
        this.mealsElgibleToCarryOverToNextDay = mealsElgibleToCarryOverToNextDay;
    }

}
