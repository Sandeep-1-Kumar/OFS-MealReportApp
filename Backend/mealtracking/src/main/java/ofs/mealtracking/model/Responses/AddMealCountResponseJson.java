package ofs.mealtracking.model.Responses;

public class AddMealCountResponseJson {
    private String siteName;
    private String deliveryTime;
    private String address;
    private String mealtemperature;
    private String siteSupervisor;
    private String date;
    private String mealServiceTime;
    private String mealType;

    public String getMealType() {
        return mealType;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getDeliveryTime() {
        return deliveryTime;
    }
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getMealtemperature() {
        return mealtemperature;
    }
    public void setMealtemperature(String mealtemperature) {
        this.mealtemperature = mealtemperature;
    }
    public String getSiteSupervisor() {
        return siteSupervisor;
    }
    public void setSiteSupervisor(String siteSupervisor) {
        this.siteSupervisor = siteSupervisor;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getMealServiceTime() {
        return mealServiceTime;
    }
    public void setMealServiceTime(String mealServiceTime) {
        this.mealServiceTime = mealServiceTime;
    }
    public String getPreviousDayMeals() {
        return previousDayMeals;
    }
    public void setPreviousDayMeals(String previousDayMeals) {
        this.previousDayMeals = previousDayMeals;
    }
    public String getMealsDelivered() {
        return mealsDelivered;
    }
    public void setMealsDelivered(String mealsDelivered) {
        this.mealsDelivered = mealsDelivered;
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
    public String getDamagedMealsComment() {
        return damagedMealsComment;
    }
    public void setDamagedMealsComment(String damagedMealsComment) {
        this.damagedMealsComment = damagedMealsComment;
    }
    public String getExpiredMeals() {
        return expiredMeals;
    }
    public void setExpiredMeals(String expiredMeals) {
        this.expiredMeals = expiredMeals;
    }
    public String getExpiredMealsComment() {
        return expiredMealsComment;
    }
    public void setExpiredMealsComment(String expiredMealsComment) {
        this.expiredMealsComment = expiredMealsComment;
    }
    public byte[] getSignatureImage() {
        return signatureImage;
    }
    public void setSignatureImage(byte[] signatureImage) {
        this.signatureImage = signatureImage;
    }
    private String previousDayMeals;
    private String mealsDelivered;
    private String mealsServed;
    private String mealsAvailable;
    public String getCarryOverMeals() {
        return carryOverMeals;
    }
    public void setCarryOverMeals(String carryOverMeals) {
        this.carryOverMeals = carryOverMeals;
    }
    private String carryOverMeals;
    public String getMealsAvailable() {
        return mealsAvailable;
    }
    public void setMealsAvailable(String mealsAvailable) {
        this.mealsAvailable = mealsAvailable;
    }
    private String damagedMeals;
    private String damagedMealsComment;
    private String expiredMeals;
    private String expiredMealsComment;
    private byte[] signatureImage;
}
