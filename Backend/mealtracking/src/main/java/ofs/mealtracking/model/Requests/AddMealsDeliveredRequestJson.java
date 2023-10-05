package ofs.mealtracking.model.Requests;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class AddMealsDeliveredRequestJson {
    private String username;
    private Date mealDate;
    private String personName;
    private String mealsDeliveryStatus;
    private int mealsDeliveredCount;
    private Time timeOfMealsDelivered;
    private BigDecimal temperatureOfMeals;
    private String mealType;
    private String program;
    private Time mealServiceStartTime;
   
   
    public String getProgram() {
        return program;
    }
    public void setProgram(String program) {
        this.program = program;
    }
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
    public String getMealsDeliveryStatus() {
        return mealsDeliveryStatus;
    }
    public void setMealsDeliveryStatus(String mealsDeliveryStatus) {
        this.mealsDeliveryStatus = mealsDeliveryStatus;
    }
    public int getMealsDeliveredCount() {
        return mealsDeliveredCount;
    }
    public void setMealsDeliveredCount(int mealsDeliveredCount) {
        this.mealsDeliveredCount = mealsDeliveredCount;
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
    public String getMealType() {
        return mealType;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    public Time getMealServiceStartTime() {
        return mealServiceStartTime;
    }
    public void setMealServiceStartTime(Time mealServiceStartTime) {
        this.mealServiceStartTime = mealServiceStartTime;
    }

}

