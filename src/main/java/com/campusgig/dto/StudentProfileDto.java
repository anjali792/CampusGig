package com.campusgig.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class StudentProfileDto {

    private String fullName;
    private String phone;
    private String college;
    private String major;
    private Integer graduationYear;

    @NotBlank(message = "Bio cannot be blank")
    private String bio;

    @NotBlank(message = "Skills tags cannot be blank")
    private String skillsTags;

    private String githubUrl;
    private String linkedinUrl;
    private String portfolioUrl;
    private BigDecimal hourlyRate;

    public StudentProfileDto() {}

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public Integer getGraduationYear() { return graduationYear; }
    public void setGraduationYear(Integer graduationYear) { this.graduationYear = graduationYear; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getSkillsTags() { return skillsTags; }
    public void setSkillsTags(String skillsTags) { this.skillsTags = skillsTags; }

    public String getGithubUrl() { return githubUrl; }
    public void setGithubUrl(String githubUrl) { this.githubUrl = githubUrl; }

    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }

    public String getPortfolioUrl() { return portfolioUrl; }
    public void setPortfolioUrl(String portfolioUrl) { this.portfolioUrl = portfolioUrl; }

    public BigDecimal getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(BigDecimal hourlyRate) { this.hourlyRate = hourlyRate; }
}
