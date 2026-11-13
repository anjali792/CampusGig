package com.campusgig.controller;

import com.campusgig.dto.StudentProfileDto;
import com.campusgig.model.Application;
import com.campusgig.model.ApplicationStatus;
import com.campusgig.model.StudentProfile;
import com.campusgig.model.User;
import com.campusgig.service.ApplicationService;
import com.campusgig.service.UserService;
import com.campusgig.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final UserService userService;
    private final ApplicationService applicationService;
    private final SecurityUtils securityUtils;

    public StudentController(UserService userService,
                             ApplicationService applicationService,
                             SecurityUtils securityUtils) {
        this.userService = userService;
        this.applicationService = applicationService;
        this.securityUtils = securityUtils;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        User student = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated student not found"));

        StudentProfile profile = userService.getStudentProfile(student.getId())
                .orElseGet(() -> new StudentProfile(student));

        List<Application> applications = applicationService.getApplicationsForStudent(student.getId());

        long totalApplied = applications.size();
        long pendingCount = applications.stream()
                .filter(a -> a.getStatus() == ApplicationStatus.PENDING)
                .count();
        long acceptedCount = applications.stream()
                .filter(a -> a.getStatus() == ApplicationStatus.ACCEPTED)
                .count();
        long completedCount = applications.stream()
                .filter(a -> a.getStatus() == ApplicationStatus.COMPLETED)
                .count();

        model.addAttribute("student", student);
        model.addAttribute("profile", profile);
        model.addAttribute("applications", applications);
        model.addAttribute("totalApplied", totalApplied);
        model.addAttribute("pendingCount", pendingCount);
        model.addAttribute("acceptedCount", acceptedCount);
        model.addAttribute("completedCount", completedCount);

        return "student/dashboard";
    }

    @GetMapping("/profile/edit")
    public String editProfileForm(Model model) {
        User student = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated student not found"));

        StudentProfile profile = userService.getStudentProfile(student.getId())
                .orElseGet(() -> new StudentProfile(student));

        StudentProfileDto dto = new StudentProfileDto();
        dto.setFullName(student.getFullName());
        dto.setPhone(student.getPhone());
        dto.setCollege(profile.getCollege());
        dto.setMajor(profile.getMajor());
        dto.setGraduationYear(profile.getGraduationYear());
        dto.setBio(profile.getBio());
        dto.setSkillsTags(profile.getSkillsTags());
        dto.setGithubUrl(profile.getGithubUrl());
        dto.setLinkedinUrl(profile.getLinkedinUrl());
        dto.setPortfolioUrl(profile.getPortfolioUrl());
        dto.setHourlyRate(profile.getHourlyRate());

        model.addAttribute("profileDto", dto);
        model.addAttribute("student", student);
        return "student/profile-edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@Valid @ModelAttribute("profileDto") StudentProfileDto profileDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        User student = securityUtils.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated student not found"));

        if (bindingResult.hasErrors()) {
            model.addAttribute("student", student);
            return "student/profile-edit";
        }

        try {
            userService.updateStudentProfile(student.getId(), profileDto);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
            return "redirect:/student/dashboard";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating profile: " + e.getMessage());
            model.addAttribute("student", student);
            return "student/profile-edit";
        }
    }
}
