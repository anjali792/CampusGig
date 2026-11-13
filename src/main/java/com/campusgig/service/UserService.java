package com.campusgig.service;

import com.campusgig.dto.StudentProfileDto;
import com.campusgig.dto.UserRegistrationDto;
import com.campusgig.model.ClientProfile;
import com.campusgig.model.Role;
import com.campusgig.model.StudentProfile;
import com.campusgig.model.User;
import com.campusgig.repository.ClientProfileRepository;
import com.campusgig.repository.StudentProfileRepository;
import com.campusgig.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final ClientProfileRepository clientProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       StudentProfileRepository studentProfileRepository,
                       ClientProfileRepository clientProfileRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentProfileRepository = studentProfileRepository;
        this.clientProfileRepository = clientProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User registerUser(UserRegistrationDto dto) {
        Role role;
        try {
            role = Role.valueOf(dto.getRole());
        } catch (Exception e) {
            role = Role.ROLE_STUDENT;
        }

        User user = new User();
        user.setEmail(dto.getEmail().toLowerCase().trim());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName().trim());
        user.setPhone(dto.getPhone());
        user.setRole(role);
        user.setIsActive(true);

        User savedUser = userRepository.save(user);

        if (role == Role.ROLE_STUDENT) {
            StudentProfile profile = new StudentProfile(savedUser);
            profile.setCollege(dto.getCollege());
            profile.setMajor(dto.getMajor());
            profile.setGraduationYear(dto.getGraduationYear());
            profile.setSkillsTags(dto.getSkillsTags() != null ? dto.getSkillsTags() : "Java, Python");
            profile.setBio("Enthusiastic student looking for exciting projects & micro-internships.");
            studentProfileRepository.save(profile);
            savedUser.setStudentProfile(profile);
        } else if (role == Role.ROLE_CLIENT) {
            ClientProfile profile = new ClientProfile(savedUser, 
                    dto.getCompanyName() != null ? dto.getCompanyName() : dto.getFullName() + "'s Team");
            profile.setOrganizationType(dto.getOrganizationType() != null ? dto.getOrganizationType() : "Startup");
            profile.setWebsiteUrl(dto.getWebsiteUrl());
            clientProfileRepository.save(profile);
            savedUser.setClientProfile(profile);
        }

        return savedUser;
    }

    public StudentProfile updateStudentProfile(Long userId, StudentProfileDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        if (dto.getFullName() != null && !dto.getFullName().isBlank()) {
            user.setFullName(dto.getFullName().trim());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone().trim());
        }
        userRepository.save(user);

        StudentProfile profile = studentProfileRepository.findByUserId(userId)
                .orElseGet(() -> new StudentProfile(user));

        profile.setBio(dto.getBio());
        profile.setCollege(dto.getCollege());
        profile.setMajor(dto.getMajor());
        profile.setGraduationYear(dto.getGraduationYear());
        profile.setSkillsTags(dto.getSkillsTags());
        profile.setGithubUrl(dto.getGithubUrl());
        profile.setLinkedinUrl(dto.getLinkedinUrl());
        profile.setPortfolioUrl(dto.getPortfolioUrl());
        if (dto.getHourlyRate() != null) {
            profile.setHourlyRate(dto.getHourlyRate());
        }

        return studentProfileRepository.save(profile);
    }

    public Optional<StudentProfile> getStudentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId);
    }

    public Optional<ClientProfile> getClientProfile(Long userId) {
        return clientProfileRepository.findByUserId(userId);
    }
}
