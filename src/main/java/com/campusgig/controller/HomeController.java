package com.campusgig.controller;

import com.campusgig.model.Gig;
import com.campusgig.service.GigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private final GigService gigService;

    public HomeController(GigService gigService) {
        this.gigService = gigService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Gig> featuredGigs = gigService.getFeaturedGigs();
        model.addAttribute("featuredGigs", featuredGigs);

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

        return "index";
    }
}
