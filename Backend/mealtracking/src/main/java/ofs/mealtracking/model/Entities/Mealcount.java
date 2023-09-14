package ofs.mealtracking.model.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "mealcount")
public class Mealcount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "siteuserid", referencedColumnName = "id")
    private Siteusers siteuser;

    @Column(name = "mealdate", nullable = false)
    private java.sql.Date mealDate;

    
    @Column(name = "mealtype", nullable = false)
    private String mealType;

   
    @Column(name = "program", nullable = false)
    private String program;

    @Column(name = "mealcount", nullable = false)
    private int mealCount;

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
        } else if (mealType.equals("LUNCH")) {
            this.mealType = "LUNCH";
        } else if (mealType.equals("SUPPER")) {
            this.mealType = "SUPPER";
        } else if (mealType.equals("SNACK")) {
            this.mealType = "SNACK";
        } else {
            throw new IllegalArgumentException("Invalid meal type: " + mealType);
        }
    }

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
