package ofs.mealtracking.model.Entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Siteusers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSitesupervisor() {
        return sitesupervisor;
    }

    public void setSitesupervisor(String sitesupervisor) {
        this.sitesupervisor = sitesupervisor;
    }

    public String getSiteaddress() {
        return siteaddress;
    }

    public void setSiteaddress(String siteaddress) {
        this.siteaddress = siteaddress;
    }

    public String getBreakfasttime() {
        return breakfasttime;
    }

    public void setBreakfasttime(String breakfasttime) {
        this.breakfasttime = breakfasttime;
    }

    public String getLunchtime() {
        return lunchtime;
    }

    public void setLunchtime(String lunchtime) {
        this.lunchtime = lunchtime;
    }

    public String getSuppertime() {
        return suppertime;
    }

    public void setSuppertime(String suppertime) {
        this.suppertime = suppertime;
    }

    public String getSnacktime() {
        return snacktime;
    }

    public void setSnacktime(String snackTime) {
        this.snacktime = snackTime;
    }

    public String getMealdays() {
        return mealdays;
    }

    public void setMealdays(String mealdays) {
        this.mealdays = mealdays;
    }

    public Admins getAdmins() {
        return admins;
    }

    public void setAdmins(Admins admins) {
        this.admins = admins;
    }

    private String sitename;
    private String sitesupervisor;
    private String siteaddress;

    @Column(columnDefinition = "TIME")
    private String breakfasttime;
    @Column(columnDefinition = "TIME")
    private String breakfastendtime;

    @Column(columnDefinition = "TIME")
    private String lunchtime;
    @Column(columnDefinition = "TIME")
    private String lunchendtime;

    @Column(name = "program", nullable = false)
    private String program;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        String programUpperCase = program.toUpperCase();
        if (programUpperCase.equals("SFSP")) {
            this.program = "SFSP";
        } else if (programUpperCase.equals("CACFP")) {
            this.program = "CACFP";
        } else {
            throw new IllegalArgumentException("Invalid program: " + program);
        }
    }

    public String getBreakfastendtime() {
        return breakfastendtime;
    }

    public void setBreakfastendtime(String breakfastendtime) {
        this.breakfastendtime = breakfastendtime;
    }

    public String getLunchendtime() {
        return lunchendtime;
    }

    public void setLunchendtime(String lunchendtime) {
        this.lunchendtime = lunchendtime;
    }

    public String getSupperendtime() {
        return supperendtime;
    }

    public void setSupperendtime(String supperendtime) {
        this.supperendtime = supperendtime;
    }

    public String getSnackendtime() {
        return snackendtime;
    }

    public void setSnackendtime(String snackendtime) {
        this.snackendtime = snackendtime;
    }

    @Column(columnDefinition = "TIME")
    private String suppertime;
    @Column(columnDefinition = "TIME")
    private String supperendtime;
    
    @Column(columnDefinition = "TIME")
    private String snacktime;
    @Column(columnDefinition = "TIME")
    private String snackendtime;

    private String mealdays;

    @ManyToOne
    @JoinColumn(name = "adminid", referencedColumnName = "id")
    private Admins admins;


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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}       


