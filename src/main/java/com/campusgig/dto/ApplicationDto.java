package com.campusgig.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ApplicationDto {

    @NotBlank(message = "Cover letter / proposal message cannot be empty")
    private String coverLetter;

    @NotNull(message = "Proposed budget is required")
    @Min(value = 5, message = "Proposed rate must be at least $5")
    private BigDecimal proposedBudget;

    @NotNull(message = "Estimated turnaround time in days is required")
    @Min(value = 1, message = "Estimated days must be at least 1")
    private Integer estimatedDays;

    private String portfolioLink;

    public ApplicationDto() {}

    // Getters and Setters
    public String getCoverLetter() { return coverLetter; }
    public void setCoverLetter(String coverLetter) { this.coverLetter = coverLetter; }

    public BigDecimal getProposedBudget() { return proposedBudget; }
    public void setProposedBudget(BigDecimal proposedBudget) { this.proposedBudget = proposedBudget; }

    public Integer getEstimatedDays() { return estimatedDays; }
    public void setEstimatedDays(Integer estimatedDays) { this.estimatedDays = estimatedDays; }

    public String getPortfolioLink() { return portfolioLink; }
    public void setPortfolioLink(String portfolioLink) { this.portfolioLink = portfolioLink; }
}
