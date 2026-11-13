package com.campusgig.service;

import com.campusgig.dto.ApplicationDto;
import com.campusgig.model.*;
import com.campusgig.repository.ApplicationRepository;
import com.campusgig.repository.GigRepository;
import com.campusgig.repository.StudentProfileRepository;
import com.campusgig.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final GigRepository gigRepository;
    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;

    public ApplicationService(ApplicationRepository applicationRepository,
                              GigRepository gigRepository,
                              UserRepository userRepository,
                              StudentProfileRepository studentProfileRepository) {
        this.applicationRepository = applicationRepository;
        this.gigRepository = gigRepository;
        this.userRepository = userRepository;
        this.studentProfileRepository = studentProfileRepository;
    }

    public Application applyForGig(Long gigId, Long studentId, ApplicationDto dto) {
        if (applicationRepository.existsByGigIdAndStudentId(gigId, studentId)) {
            throw new IllegalStateException("You have already submitted a proposal for this gig!");
        }

        Gig gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found: " + gigId));

        if (gig.getStatus() != GigStatus.OPEN) {
            throw new IllegalStateException("This gig is currently closed for applications.");
        }

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));

        Application application = new Application();
        application.setGig(gig);
        application.setStudent(student);
        application.setCoverLetter(dto.getCoverLetter().trim());
        application.setProposedBudget(dto.getProposedBudget());
        application.setEstimatedDays(dto.getEstimatedDays());
        application.setPortfolioLink(dto.getPortfolioLink());
        application.setStatus(ApplicationStatus.PENDING);

        Application saved = applicationRepository.save(application);

        // Increment gig applicant counter
        gig.setApplicantsCount(gig.getApplicantsCount() + 1);
        gigRepository.save(gig);

        return saved;
    }

    public boolean hasStudentApplied(Long gigId, Long studentId) {
        return applicationRepository.existsByGigIdAndStudentId(gigId, studentId);
    }

    public Optional<Application> getApplication(Long gigId, Long studentId) {
        return applicationRepository.findByGigIdAndStudentId(gigId, studentId);
    }

    public List<Application> getApplicationsForStudent(Long studentId) {
        return applicationRepository.findByStudentIdOrderByAppliedAtDesc(studentId);
    }

    public List<Application> getApplicationsForGig(Long gigId, Long clientId) {
        Gig gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found: " + gigId));

        if (!gig.getClient().getId().equals(clientId)) {
            throw new SecurityException("Unauthorized access to view gig applicants");
        }

        return applicationRepository.findByGigIdOrderByAppliedAtDesc(gigId);
    }

    public List<Application> getAllApplicationsForClient(Long clientId) {
        return applicationRepository.findByGigClientIdOrderByAppliedAtDesc(clientId);
    }

    public Application updateApplicationStatus(Long applicationId, ApplicationStatus newStatus, String feedback, Long clientId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found: " + applicationId));

        Gig gig = application.getGig();
        if (!gig.getClient().getId().equals(clientId)) {
            throw new SecurityException("Unauthorized to modify this application");
        }

        application.setStatus(newStatus);
        application.setReviewedAt(LocalDateTime.now());
        if (feedback != null && !feedback.isBlank()) {
            application.setClientFeedback(feedback.trim());
        }

        // If accepted or completed, update gig status or student count
        if (newStatus == ApplicationStatus.ACCEPTED) {
            gig.setStatus(GigStatus.IN_PROGRESS);
            gigRepository.save(gig);
        } else if (newStatus == ApplicationStatus.COMPLETED) {
            gig.setStatus(GigStatus.CLOSED);
            gigRepository.save(gig);
            studentProfileRepository.findByUserId(application.getStudent().getId()).ifPresent(profile -> {
                profile.setCompletedGigsCount(profile.getCompletedGigsCount() + 1);
                studentProfileRepository.save(profile);
            });
        }

        return applicationRepository.save(application);
    }

    public long countStudentApplications(Long studentId) {
        return applicationRepository.countByStudentId(studentId);
    }

    public long countStudentApplicationsByStatus(Long studentId, ApplicationStatus status) {
        return applicationRepository.countByStudentIdAndStatus(studentId, status);
    }
}
