package com.campusgig.service;

import com.campusgig.dto.GigDto;
import com.campusgig.model.ClientProfile;
import com.campusgig.model.Gig;
import com.campusgig.model.GigStatus;
import com.campusgig.model.User;
import com.campusgig.repository.ClientProfileRepository;
import com.campusgig.repository.GigRepository;
import com.campusgig.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GigService {

    private final GigRepository gigRepository;
    private final UserRepository userRepository;
    private final ClientProfileRepository clientProfileRepository;

    public GigService(GigRepository gigRepository,
                      UserRepository userRepository,
                      ClientProfileRepository clientProfileRepository) {
        this.gigRepository = gigRepository;
        this.userRepository = userRepository;
        this.clientProfileRepository = clientProfileRepository;
    }

    public Gig createGig(GigDto dto, Long clientId) {
        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client user not found: " + clientId));

        Gig gig = new Gig();
        gig.setClient(client);
        gig.setTitle(dto.getTitle().trim());
        gig.setCategory(dto.getCategory().trim());
        gig.setDescription(dto.getDescription().trim());
        gig.setBudget(dto.getBudget());
        gig.setBudgetType(dto.getBudgetType() != null ? dto.getBudgetType() : "FIXED");
        gig.setDeadline(dto.getDeadline());
        gig.setRequiredSkills(dto.getRequiredSkills().trim());
        gig.setLocationType(dto.getLocationType());
        gig.setCampusLocation(dto.getCampusLocation());
        gig.setExternalApplyUrl(dto.getExternalApplyUrl() != null && !dto.getExternalApplyUrl().isBlank() ? dto.getExternalApplyUrl().trim() : null);
        
        // Populate company name if provided or fetch from client profile
        if (dto.getCompanyName() != null && !dto.getCompanyName().isBlank()) {
            gig.setCompanyName(dto.getCompanyName().trim());
        } else {
            clientProfileRepository.findByUserId(clientId).ifPresent(p -> gig.setCompanyName(p.getCompanyName()));
        }
        gig.setIsVerified(false);
        gig.setStatus(GigStatus.OPEN);
        gig.setApplicantsCount(0);

        Gig saved = gigRepository.save(gig);

        // Update total gigs posted in client profile
        clientProfileRepository.findByUserId(clientId).ifPresent(profile -> {
            profile.setTotalGigsPosted(profile.getTotalGigsPosted() + 1);
            clientProfileRepository.save(profile);
        });

        return saved;
    }

    public Optional<Gig> getGigById(Long id) {
        return gigRepository.findById(id);
    }

    public List<Gig> getAllOpenGigs() {
        return gigRepository.findByStatusOrderByCreatedAtDesc(GigStatus.OPEN);
    }

    public List<Gig> getFeaturedGigs() {
        return gigRepository.findTop6ByStatusOrderByCreatedAtDesc(GigStatus.OPEN);
    }

    public List<Gig> searchGigs(String category, String keyword) {
        if ((category == null || category.isBlank() || "ALL".equalsIgnoreCase(category)) 
                && (keyword == null || keyword.isBlank())) {
            return getAllOpenGigs();
        }
        String cleanCat = (category != null && !category.isBlank() && !"ALL".equalsIgnoreCase(category)) ? category.trim() : null;
        String cleanKey = (keyword != null && !keyword.isBlank()) ? keyword.trim() : null;
        return gigRepository.searchGigs(GigStatus.OPEN, cleanCat, cleanKey);
    }

    public List<Gig> getGigsByClient(Long clientId) {
        return gigRepository.findByClientIdOrderByCreatedAtDesc(clientId);
    }

    public long countGigsByClient(Long clientId) {
        return gigRepository.countByClientId(clientId);
    }

    public long countActiveGigsByClient(Long clientId) {
        return gigRepository.countByClientIdAndStatus(clientId, GigStatus.OPEN);
    }

    public void updateGigStatus(Long gigId, GigStatus status, Long clientId) {
        Gig gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found: " + gigId));

        if (!gig.getClient().getId().equals(clientId)) {
            throw new SecurityException("Unauthorized access to modify gig status");
        }

        gig.setStatus(status);
        gigRepository.save(gig);
    }

    public void deleteGig(Long gigId, Long clientId) {
        Gig gig = gigRepository.findById(gigId)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found: " + gigId));

        if (!gig.getClient().getId().equals(clientId)) {
            throw new SecurityException("Unauthorized access to delete gig");
        }

        gigRepository.delete(gig);
    }
}
