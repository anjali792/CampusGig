package com.campusgig.repository;

import com.campusgig.model.Gig;
import com.campusgig.model.GigStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GigRepository extends JpaRepository<Gig, Long> {

    List<Gig> findByStatusOrderByCreatedAtDesc(GigStatus status);

    List<Gig> findByClientIdOrderByCreatedAtDesc(Long clientId);

    long countByClientId(Long clientId);

    long countByClientIdAndStatus(Long clientId, GigStatus status);

    @Query("SELECT g FROM Gig g WHERE g.status = :status AND " +
           "(:category IS NULL OR :category = '' OR LOWER(g.category) = LOWER(:category)) AND " +
           "(:keyword IS NULL OR :keyword = '' OR " +
           "LOWER(g.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(g.requiredSkills) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
           "ORDER BY g.createdAt DESC")
    List<Gig> searchGigs(@Param("status") GigStatus status,
                         @Param("category") String category,
                         @Param("keyword") String keyword);

    List<Gig> findTop6ByStatusOrderByCreatedAtDesc(GigStatus status);
}
