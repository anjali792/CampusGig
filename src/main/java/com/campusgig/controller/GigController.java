package com.campusgig.controller;

import com.campusgig.dto.ApplicationDto;
import com.campusgig.model.Application;
import com.campusgig.model.Gig;
import com.campusgig.model.Role;
import com.campusgig.model.User;
import com.campusgig.service.ApplicationService;
import com.campusgig.service.GigService;
import com.campusgig.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gigs")
public class GigController {

    private final GigService gigService;
    private final ApplicationService applicationService;
    private final SecurityUtils securityUtils;

    public GigController(GigService gigService,
                         ApplicationService applicationService,
                         SecurityUtils securityUtils) {
        this.gigService = gigService;
        this.applicationService = applicationService;
        this.securityUtils = securityUtils;
    }

    @GetMapping
    public String listGigs(@RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "q", required = false) String keyword,
                           Model model) {
        List<Gig> gigs = gigService.searchGigs(category, keyword);
        model.addAttribute("gigs", gigs);
        model.addAttribute("selectedCategory", category != null ? category : "ALL");
        model.addAttribute("searchQuery", keyword != null ? keyword : "");

        List<String> categories = Arrays.asList(
                "Web Development",
                "AI & Data Science",
                "Mobile Apps",
                "UI/UX Design",
                "Cybersecurity",
                "IoT & Hardware",
                "Content & Marketing",
                "College Club Support"
        );
        model.addAttribute("categories", categories);

        return "gigs/list";
    }

    @GetMapping("/{id}")
    public String gigDetail(@PathVariable("id") Long id,
                            @RequestParam(value = "applied", required = false) String applied,
                            Model model) {
        Gig gig = gigService.getGigById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gig not found with id: " + id));

        model.addAttribute("gig", gig);

        Optional<User> currentUserOpt = securityUtils.getCurrentUser();
        boolean hasApplied = false;
        boolean isOwner = false;
        boolean isStudent = false;

        if (currentUserOpt.isPresent()) {
            User user = currentUserOpt.get();
            if (user.getRole() == Role.ROLE_STUDENT) {
                isStudent = true;
                hasApplied = applicationService.hasStudentApplied(gig.getId(), user.getId());
                if (hasApplied) {
                    Optional<Application> existingApp = applicationService.getApplication(gig.getId(), user.getId());
                    existingApp.ifPresent(app -> model.addAttribute("existingApplication", app));
                }
            } else if (user.getRole() == Role.ROLE_CLIENT && gig.getClient().getId().equals(user.getId())) {
                isOwner = true;
            }
        }

        model.addAttribute("isStudent", isStudent);
        model.addAttribute("hasApplied", hasApplied);
        model.addAttribute("isOwner", isOwner);

        if (!model.containsAttribute("applicationDto")) {
            ApplicationDto dto = new ApplicationDto();
            dto.setProposedBudget(gig.getBudget());
            dto.setEstimatedDays(7);
            model.addAttribute("applicationDto", dto);
        }

        if (applied != null) {
            model.addAttribute("successMessage", "Your application & proposal have been successfully submitted!");
        }

        return "gigs/detail";
    }

    @PostMapping("/{id}/apply")
    public String applyForGig(@PathVariable("id") Long id,
                             @Valid @ModelAttribute("applicationDto") ApplicationDto applicationDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        Optional<User> currentUserOpt = securityUtils.getCurrentUser();
        if (currentUserOpt.isEmpty() || currentUserOpt.get().getRole() != Role.ROLE_STUDENT) {
            redirectAttributes.addFlashAttribute("errorMessage", "Only students can apply for gigs.");
            return "redirect:/gigs/" + id;
        }

        User student = currentUserOpt.get();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.applicationDto", bindingResult);
            redirectAttributes.addFlashAttribute("applicationDto", applicationDto);
            redirectAttributes.addFlashAttribute("errorMessage", "Please fix errors in your proposal submission.");
            return "redirect:/gigs/" + id;
        }

        try {
            applicationService.applyForGig(id, student.getId(), applicationDto);
            return "redirect:/gigs/" + id + "?applied=true";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/gigs/" + id;
        }
    }
}
