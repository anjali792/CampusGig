package com.campusgig.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "gigs")
public class Gig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(columnDefinition = "TEXT", length = 65535, nullable = false)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal budget;

    @Column(name = "budget_type", length = 20)
    private String budgetType = "FIXED"; // 'FIXED', 'HOURLY'

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(name = "required_skills", nullable = false, length = 500)
    private String requiredSkills;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type", length = 20)
    private LocationType locationType = LocationType.REMOTE;

    @Column(name = "campus_location", length = 150)
    private String campusLocation;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private GigStatus status = GigStatus.OPEN;

    @Column(name = "company_name", length = 150)
    private String companyName;

    @Column(name = "external_apply_url", length = 500)
    private String externalApplyUrl;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "applicants_count")
    private Integer applicantsCount = 0;

    @OneToMany(mappedBy = "gig", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Gig() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.applicantsCount == null) {
            this.applicantsCount = 0;
        }
        if (this.status == null) {
            this.status = GigStatus.OPEN;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public List<String> getSkillsList() {
        if (requiredSkills == null || requiredSkills.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(requiredSkills.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }

    public String getBudgetType() { return budgetType; }
    public void setBudgetType(String budgetType) { this.budgetType = budgetType; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    public String getRequiredSkills() { return requiredSkills; }
    public void setRequiredSkills(String requiredSkills) { this.requiredSkills = requiredSkills; }

    public LocationType getLocationType() { return locationType; }
    public void setLocationType(LocationType locationType) { this.locationType = locationType; }

    public String getCampusLocation() { return campusLocation; }
    public void setCampusLocation(String campusLocation) { this.campusLocation = campusLocation; }

    public GigStatus getStatus() { return status; }
    public void setStatus(GigStatus status) { this.status = status; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getExternalApplyUrl() { return externalApplyUrl; }
    public void setExternalApplyUrl(String externalApplyUrl) { this.externalApplyUrl = externalApplyUrl; }

    public Boolean getIsVerified() { return isVerified != null && isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }

    public Integer getApplicantsCount() { return applicantsCount; }
    public void setApplicantsCount(Integer applicantsCount) { this.applicantsCount = applicantsCount; }

    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
