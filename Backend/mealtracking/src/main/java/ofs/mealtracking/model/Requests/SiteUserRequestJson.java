package ofs.mealtracking.model.Requests;

public class SiteUserRequestJson {
    
    private String username;
    private String password;
    private String email;
    private String siteName;
    private String siteSupervisor;
    private String siteAddress;
    private String breakfastTime;

    public String getBreakfastEndTime() {
        return breakfastEndTime;
    }
    public void setBreakfastEndTime(String breakfastEndTime) {
        this.breakfastEndTime = breakfastEndTime;
    }
    public String getLunchEndTime() {
        return lunchEndTime;
    }
    public void setLunchEndTime(String lunchEndTime) {
        this.lunchEndTime = lunchEndTime;
    }
    public String getSupperEndTime() {
        return supperEndTime;
    }
    public void setSupperEndTime(String supperEndTime) {
        this.supperEndTime = supperEndTime;
    }
    public String getSnackEndTime() {
        return snackEndTime;
    }
    public void setSnackEndTime(String snackEndTime) {
        this.snackEndTime = snackEndTime;
    }
    private String breakfastEndTime;
    private String lunchTime;
    private String lunchEndTime;
    private String supperTime;
    private String supperEndTime;
    private String snackTime;
    private String snackEndTime;
    private String mealDays;
    private String adminid;
    

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getSiteSupervisor() {
        return siteSupervisor;
    }
    public void setSiteSupervisor(String siteSupervisor) {
        this.siteSupervisor = siteSupervisor;
    }
    public String getSiteAddress() {
        return siteAddress;
    }
    public void setSiteAddress(String siteAddress) {
        this.siteAddress = siteAddress;
    }
    public String getBreakfastTime() {
        return breakfastTime;
    }
    public void setBreakfastTime(String breakfastTime) {
        this.breakfastTime = breakfastTime;
    }
    public String getLunchTime() {
        return lunchTime;
    }
    public void setLunchTime(String lunchTime) {
        this.lunchTime = lunchTime;
    }
    public String getSupperTime() {
        return supperTime;
    }
    public void setSupperTime(String supperTime) {
        this.supperTime = supperTime;
    }
    public String getSnackTime() {
        return snackTime;
    }
    public void setSnackTime(String snackTime) {
        this.snackTime = snackTime;
    }
    public String getMealDays() {
        return mealDays;
    }
    public void setMealDays(String mealDays) {
        this.mealDays = mealDays;
    }
    public String getAdminid() {
        return adminid;
    }
    public void setAdminid(String adminid) {
        this.adminid = adminid;
    }
}
