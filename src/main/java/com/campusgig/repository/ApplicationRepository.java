package com.campusgig.repository;

import com.campusgig.model.Application;
import com.campusgig.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStudentIdOrderByAppliedAtDesc(Long studentId);

    List<Application> findByGigIdOrderByAppliedAtDesc(Long gigId);

    List<Application> findByGigClientIdOrderByAppliedAtDesc(Long clientId);

    long countByStudentId(Long studentId);

    long countByStudentIdAndStatus(Long studentId, ApplicationStatus status);

    boolean existsByGigIdAndStudentId(Long gigId, Long studentId);

    Optional<Application> findByGigIdAndStudentId(Long gigId, Long studentId);
}
