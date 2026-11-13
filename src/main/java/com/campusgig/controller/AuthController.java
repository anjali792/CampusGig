package com.campusgig.controller;

import com.campusgig.dto.UserRegistrationDto;
import com.campusgig.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "registered", required = false) String registered,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "You have been successfully logged out.");
        }
        if (registered != null) {
            model.addAttribute("successMessage", "Registration successful! You can now log in.");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(@RequestParam(value = "role", defaultValue = "ROLE_STUDENT") String role,
                               Model model) {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setRole(role);
        model.addAttribute("userDto", dto);
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDto") UserRegistrationDto userDto,
                               BindingResult bindingResult,
                               Model model) {
        if (userDto.getPassword() != null && userDto.getConfirmPassword() != null
                && !userDto.getPassword().equals(userDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.userDto", "Passwords do not match");
        }

        if (userService.emailExists(userDto.getEmail())) {
            bindingResult.rejectValue("email", "error.userDto", "An account with this email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        try {
            userService.registerUser(userDto);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Registration failed: " + e.getMessage());
            return "auth/register";
        }
    }
}
