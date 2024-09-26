package com.sikehish.collegeconnect.repository;

import com.sikehish.collegeconnect.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    Optional<StudentProfile> findByUserId(Long userId);
    Optional<StudentProfile> findByUser_Username(String username);
    @Query("SELECT sp FROM StudentProfile sp WHERE " +
            "(:name IS NULL OR LOWER(sp.user.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:departmentId IS NULL OR sp.department.id = :departmentId) AND " +
            "(:year IS NULL OR sp.year = :year)")
    List<StudentProfile> searchStudents(String name, Long departmentId, String year);
}
