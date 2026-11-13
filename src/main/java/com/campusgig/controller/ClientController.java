package com.campusgig.controller;

import com.campusgig.dto.GigDto;
import com.campusgig.model.*;
import com.campusgig.service.ApplicationService;
import com.campusgig.service.GigService;
import com.campusgig.service.UserService;
import com.campusgig.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final GigService gigService;
    private final ApplicationService applicationService;
    private final UserService userService;
    private final SecurityUtils securityUtils;

    public ClientController(GigService gigService,
                            ApplicationService applicationService,
                            UserService userService,
                            SecurityUtils securityUtils) {
        this.gigService = gigService;
        this.applicationService = applicationService;
        this.userService = userService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User client = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated client not found"));

        ClientProfile profile = userService.getClientProfile(client.getId())
                .orElseGet(() -> new ClientProfile(client, client.getFullName() + "'s Org"));

        List<Gig> postedGigs = gigService.getGigsByClient(client.getId());
        List<Application> allApplications = applicationService.getAllApplicationsForClient(client.getId());

        long activeGigsCount = postedGigs.stream()
                .filter(g -> g.getStatus() == GigStatus.OPEN)
                .count();

        long totalApplicantsCount = allApplications.size();

        long hiredCount = allApplications.stream()
                .filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED || a.getStatus() == ApplicationStatus.COMPLETED)
                .count();

        model.addAttribute("client", client);
        model.addAttribute("profile", profile);
        model.addAttribute("postedGigs", postedGigs);
        model.addAttribute("activeGigsCount", activeGigsCount);
        model.addAttribute("totalApplicantsCount", totalApplicantsCount);
        model.addAttribute("hiredCount", hiredCount);

        return "client/dashboard";
    }

    @GetMapping("/gigs/new")
    public String newGigForm(Model model) {
        if (!model.containsAttribute("gigDto")) {
            GigDto dto = new GigDto();
            dto.setLocationType(LocationType.REMOTE);
            dto.setBudgetType("FIXED");
            model.addAttribute("gigDto", dto);
        }
        return "client/post-gig";
    }

    @PostMapping("/gigs/new")
    public String createGig(@Valid @ModelAttribute("gigDto") GigDto gigDto,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        User client = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated client not found"));

        if (bindingResult.hasErrors()) {
            return "client/post-gig";
        }

        try {
            gigService.createGig(gigDto, client.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Your micro-internship gig has been posted successfully!");
            return "redirect:/client/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error posting gig: " + e.getMessage());
            return "redirect:/client/gigs/new";
        }
    }

    @GetMapping("/gigs/{id}/applicants")
    public String viewApplicants(@PathVariable("id") Long gigId, Model model) {
        User client = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated client not found"));

        Gig gig = gigService.getGigById(gigId)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found: " + gigId));

        if (!gig.getClient().getId().equals(client.getId())) {
            throw new SecurityException("You do not have permission to view applicants for this gig.");
        }

        List<Application> applications = applicationService.getApplicationsForGig(gigId, client.getId());

        model.addAttribute("gig", gig);
        model.addAttribute("applications", applications);

        return "client/applicants";
    }

    @PostMapping("/applications/{id}/status")
    public String updateApplicationStatus(@PathVariable("id") Long applicationId,
                                          @RequestParam("status") String statusStr,
                                          @RequestParam(value = "feedback", required = false) String feedback,
                                          @RequestParam("gigId") Long gigId,
                                          RedirectAttributes redirectAttributes) {
        User client = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated client not found"));

        try {
            ApplicationStatus status = ApplicationStatus.valueOf(statusStr);
            applicationService.updateApplicationStatus(applicationId, status, feedback, client.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Application status updated to " + status + "!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update status: " + e.getMessage());
        }

        return "redirect:/client/gigs/" + gigId + "/applicants";
    }

    @PostMapping("/gigs/{id}/status")
    public String toggleGigStatus(@PathVariable("id") Long gigId,
                                  @RequestParam("status") String statusStr,
                                  RedirectAttributes redirectAttributes) {
        User client = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated client not found"));

        try {
            GigStatus status = GigStatus.valueOf(statusStr);
            gigService.updateGigStatus(gigId, status, client.getId());
            redirectAttributes.addFlashAttribute("successMessage", "Gig status updated to " + status + ".");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update gig status: " + e.getMessage());
        }

        return "redirect:/client/dashboard";
    }
}
