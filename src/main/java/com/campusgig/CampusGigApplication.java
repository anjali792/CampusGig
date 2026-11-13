package com.campusgig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampusGigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusGigApplication.class, args);
        System.out.println("=================================================");
        System.out.println("🚀 CampusGig Web Application Started Successfully!");
        System.out.println("📍 Local URL: http://localhost:8080");
        System.out.println("=================================================");
    }
}
