package com.bracits.snowflake.entity;

import com.bracits.snowflake.entity.common.CommonColumn;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Shaiful Islam Palash | kuvic16@gmail.com
 * @CreatedAt: 9/24/2020
 */
@Entity
@Table(name = "profiles")
public class Profile extends CommonColumn{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private User user;

    @Column(name = "pin", length = 8, nullable = false)
    private String pin;

    @Column(name = "name", length = 150, nullable = false)
    private String name;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "grade")
    private int grade;

    @Column(name = "mobile", length = 50)
    private String mobile;

    @Column(name = "dob")
    private Calendar dob;

    @Column(name = "gender", length = 6)
    private String gender;

    @Column(name = "permanent_address", length = 255)
    private String permanentAddress;

    @Column(name = "project_program_department")
    private long projectProgramDepartment;

    @Column(name = "current_posting_place", length = 255)
    private String currentPostingPlace;

    @Column(name = "brac_joining_date")
    private Date bracJoiningDate;

    @Column(name = "brac_experience_year")
    private int bracExperienceYear;

    @Column(name = "total_experience_year")
    private int totalExperienceYear;

    @Column(name = "education_qualification_higher")
    private Date educationQualificationHigher;

    @Column(name = "name_of_district", length = 255)
    private String nameOfDistrict;

    public Profile() {
    }

    public Profile(String pin, String name, String email, String designation, int grade,
                   String mobile, Calendar dob, String gender, String permanentAddress,
                   long projectProgramDepartment, String currentPostingPlace, Date bracJoiningDate,
                   int bracExperienceYear, int totalExperienceYear, Date educationQualificationHigher,
                   String nameOfDistrict, User user) {
        this.pin = pin;
        this.name = name;
        this.email = email;
        this.designation = designation;
        this.grade = grade;
        this.mobile = mobile;
        this.dob = dob;
        this.gender = gender;
        this.permanentAddress = permanentAddress;
        this.projectProgramDepartment = projectProgramDepartment;
        this.currentPostingPlace = currentPostingPlace;
        this.bracJoiningDate = bracJoiningDate;
        this.bracExperienceYear = bracExperienceYear;
        this.totalExperienceYear = totalExperienceYear;
        this.educationQualificationHigher = educationQualificationHigher;
        this.nameOfDistrict = nameOfDistrict;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public long getProjectProgramDepartment() {
        return projectProgramDepartment;
    }

    public void setProjectProgramDepartment(long projectProgramDepartment) {
        this.projectProgramDepartment = projectProgramDepartment;
    }

    public String getCurrentPostingPlace() {
        return currentPostingPlace;
    }

    public void setCurrentPostingPlace(String currentPostingPlace) {
        this.currentPostingPlace = currentPostingPlace;
    }

    public Date getBracJoiningDate() {
        return bracJoiningDate;
    }

    public void setBracJoiningDate(Date bracJoiningDate) {
        this.bracJoiningDate = bracJoiningDate;
    }

    public int getBracExperienceYear() {
        return bracExperienceYear;
    }

    public void setBracExperienceYear(int bracExperienceYear) {
        this.bracExperienceYear = bracExperienceYear;
    }

    public int getTotalExperienceYear() {
        return totalExperienceYear;
    }

    public void setTotalExperienceYear(int totalExperienceYear) {
        this.totalExperienceYear = totalExperienceYear;
    }

    public Date getEducationQualificationHigher() {
        return educationQualificationHigher;
    }

    public void setEducationQualificationHigher(Date educationQualificationHigher) {
        this.educationQualificationHigher = educationQualificationHigher;
    }

    public String getNameOfDistrict() {
        return nameOfDistrict;
    }

    public void setNameOfDistrict(String nameOfDistrict) {
        this.nameOfDistrict = nameOfDistrict;
    }

    @Override
    public String toString() {
        return "Profile [id=" + id
                + ", pin=" + pin
                + ", name=" + name
                + ", email=" + email
                + "]";
    }
}
