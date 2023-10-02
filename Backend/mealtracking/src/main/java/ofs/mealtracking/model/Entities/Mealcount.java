package ofs.mealtracking.model.Entities;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "mealcount")
public class Mealcount {

    public Integer getMealsDelivered() {
        return mealsDelivered;
    }

    public void setMealsDelivered(Integer mealsDelivered) {
        this.mealsDelivered = mealsDelivered;
    }

    public Time getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Time deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BigDecimal getTemperatureOfMeals() {
        return temperatureOfMeals;
    }

    public void setTemperatureOfMeals(BigDecimal temperatureOfMeals) {
        this.temperatureOfMeals = temperatureOfMeals;
    }


    public Time getMealServiceStartTime() {
        return mealServiceStartTime;
    }

    public void setMealServiceStartTime(Time mealServiceStartTime) {
        this.mealServiceStartTime = mealServiceStartTime;
    }

    public Time getMealServiceEndTime() {
        return mealServiceEndTime;
    }

    public void setMealServiceEndTime(Time mealServiceEndTime) {
        this.mealServiceEndTime = mealServiceEndTime;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "siteuserid", referencedColumnName = "id")
    private Siteusers siteuser;

    @Column(name = "mealdate", nullable = false)
    private java.sql.Date mealDate;

    @Column(name = "previousdaymeals")
    private int previousDayMeals;


    public int getPreviousDayMeals() {
        return previousDayMeals;
    }

    public void setPreviousDayMeals(int previousDayMeals) {
        this.previousDayMeals = previousDayMeals;
    }

    @Column(name = "personname", nullable = false)
    private String personName;
     
    @Lob
    @Column(name = "personsignature", columnDefinition = "LONGBLOB")
    private byte[] personSignature;

    public byte[] getPersonSignature() {
        return personSignature;
    }

    public void setPersonSignature(byte[] personSignature) {
        this.personSignature = personSignature;
    }

    public byte[] getExpiredMealsImage() {
        return expiredMealsImage;
    }

    public void setExpiredMealsImage(byte[] expiredMealsImage) {
        this.expiredMealsImage = expiredMealsImage;
    }

    public byte[] getDamagedMealsImage() {
        return damagedMealsImage;
    }

    public void setDamagedMealsImage(byte[] damagedMealsImage) {
        this.damagedMealsImage = damagedMealsImage;
    }

    @Lob
    @Column(name = "expiredmealsimage", columnDefinition = "LONGBLOB")
    private byte[] expiredMealsImage;

    @Lob
    @Column(name = "damagedmealsimage", columnDefinition = "LONGBLOB")
    private byte[] damagedMealsImage;


    @Column(name = "mealtype", nullable = false)
    private String mealType;

    @Column(name = "mealservicestatus", nullable = false)
    private String mealServiceStatus;

    @Column(name = "mealdeliverystatus", nullable = false)
    private String mealDeliveryStatus;
   
//    @Column(name = "program", nullable = false)
//    private String program;

//    @Column(name = "mealcount", nullable = false)
//    private int mealCount;

    public String getMealDeliveryStatus() {
        return mealDeliveryStatus;
    }

    public void setMealDeliveryStatus(String mealDeliveryStatus) {
        String mealDeliveryStatusUpperCase = mealDeliveryStatus.toUpperCase();
        if (mealDeliveryStatusUpperCase.equals("YES")) {
            this.mealDeliveryStatus = "YES";
        } else if (mealDeliveryStatusUpperCase.equals("NO")) {
            this.mealDeliveryStatus = "NO";
        }else {
            throw new IllegalArgumentException("Invalid mealDeliveryStatus : " + mealDeliveryStatus);
        }
    }

    @Column(name = "carryovermeals")
    private Integer carryOverMeals;

    @Column(name = "mealsdelivered")
    private Integer mealsDelivered;

    
    public Integer getDamagedMeals() {
        return damagedMeals;
    }

    public void setDamagedMeals(Integer damagedMeals) {
        this.damagedMeals = damagedMeals;
    }

    public Integer getExpiredMeals() {
        return expiredMeals;
    }

    public void setExpiredMeals(Integer expiredMeals) {
        this.expiredMeals = expiredMeals;
    }

    public Integer getMealsServed() {
        return mealsServed;
    }

    public void setMealsServed(Integer mealsServed) {
        this.mealsServed = mealsServed;
    }

    @Column(name = "damagedmeals")
    private Integer damagedMeals;
    
    @Column(name = "expiredmeals")
    private Integer expiredMeals;
    
     @Column(name = "mealsserved")
    private Integer mealsServed;

    @Column(name = "deliverytime")
    private Time deliveryTime;

    @Column(name = "temperatureofmeals", precision = 5, scale = 2, columnDefinition = "DECIMAL(5,2) default 0")
    private BigDecimal temperatureOfMeals;

    @Column(name = "mealservicestarttime")
    private Time mealServiceStartTime;

    
    public String getExpiredMealsComment() {
        return expiredMealsComment;
    }

    public void setExpiredMealsComment(String expiredMealsComment) {
        this.expiredMealsComment = expiredMealsComment;
    }

    public String getDamagedMealsComment() {
        return damagedMealsComment;
    }

    public void setDamagedMealsComment(String damagedMealsComment) {
        this.damagedMealsComment = damagedMealsComment;
    }

    @Column(name = "mealserviceendtime")
    private Time mealServiceEndTime;

    @Column(name = "expiredmealscomment", columnDefinition = "TEXT")
    private String expiredMealsComment;

    @Column(name = "damagedmealscomment", columnDefinition = "TEXT")
    private String damagedMealsComment;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Siteusers getSiteuser() {
        return siteuser;
    }

    public void setSiteuser(Siteusers siteuser) {
        this.siteuser = siteuser;
    }

    public java.sql.Date getMealDate() {
        return mealDate;
    }

    public void setMealDate(Date date) {
        this.mealDate = date;
    }

   
    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        String mealTypeUpperCase = mealType.toUpperCase();
        if (mealTypeUpperCase.equals("BREAKFAST")) {
            this.mealType = "BREAKFAST";
        } else if (mealTypeUpperCase.equals("LUNCH")) {
            this.mealType = "LUNCH";
        } else if (mealTypeUpperCase.equals("SUPPER")) {
            this.mealType = "SUPPER";
        } else if (mealTypeUpperCase.equals("SNACK")) {
            this.mealType = "SNACK";
        } else {
            throw new IllegalArgumentException("Invalid meal type: " + mealType);
        }
    }
/*  
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

    public int getMealCount() {
        return mealCount;
    }

    public void setMealCount(int mealCount) {
        this.mealCount = mealCount;
    }
*/
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCarryOverMeals() {
        return carryOverMeals;
    }

    public void setCarryOverMeals(Integer carryOverMeals) {
        this.carryOverMeals = carryOverMeals;
    }

    public String getMealServiceStatus() {
        return mealServiceStatus;
    }

    public void setMealServiceStatus(String mealServiceStatus) {
        String mealServiceStatusUpperCase = mealServiceStatus.toUpperCase();
        if (mealServiceStatusUpperCase.equals("IN PROGRESS")) {
            this.mealServiceStatus = "In PROGRESS";
        } else if (mealServiceStatusUpperCase.equals("COMPLETED")) {
            this.mealServiceStatus = "COMPLETED";
        }else {
            throw new IllegalArgumentException("Invalid status : " + mealServiceStatusUpperCase);
        }
    }

}
