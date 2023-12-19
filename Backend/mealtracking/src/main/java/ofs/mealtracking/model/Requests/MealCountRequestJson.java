package ofs.mealtracking.model.Requests;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class MealCountRequestJson {
    public int getMealsFromPreviousDay() {
        return mealsFromPreviousDay;
    }

    public void setMealsFromPreviousDay(int mealsFromPreviousDay) {
        this.mealsFromPreviousDay = mealsFromPreviousDay;
    }

    public int getDamagedMeals() {
        return damagedMeals;
    }

    public void setDamagedMeals(int damagedMeals) {
        this.damagedMeals = damagedMeals;
    }

    public int getExpiredMeals() {
        return expiredMeals;
    }

    public void setExpiredMeals(int expiredMeals) {
        this.expiredMeals = expiredMeals;
    }

    public int getMealsServed() {
        return mealsServed;
    }

    public void setMealsServed(int mealsServed) {
        this.mealsServed = mealsServed;
    }

    private String username;
    private Date mealDate;
    private String personName;
    private int mealsFromPreviousDay; 
    private int mealsDelivered;
    private int damagedMeals;
    private int expiredMeals;      
    private int mealsServed;
    private Time timeOfMealsDelivered;
    private Time timeOfMealService;
    private String comment;
    private BigDecimal temperatureOfMeals;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date mealDate) {
        this.mealDate = mealDate;
    }
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getMealsDelivered() {
        return mealsDelivered;
    }

    public void setMealsDelivered(int mealsDelivered) {
        this.mealsDelivered = mealsDelivered;
    }

    public Time getTimeOfMealService() {
        return timeOfMealService;
    }

    public void setTimeOfMealService(Time timeOfMealService) {
        this.timeOfMealService = timeOfMealService;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Time getTimeOfMealsDelivered() {
        return timeOfMealsDelivered;
    }

    public void setTimeOfMealsDelivered(Time timeOfMealsDelivered) {
        this.timeOfMealsDelivered = timeOfMealsDelivered;
    }

    public BigDecimal getTemperatureOfMeals() {
        return temperatureOfMeals;
    }

    public void setTemperatureOfMeals(BigDecimal temperatureOfMeals) {
        this.temperatureOfMeals = temperatureOfMeals;
    }

}
