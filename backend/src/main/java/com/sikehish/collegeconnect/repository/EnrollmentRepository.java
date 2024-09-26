package com.sikehish.collegeconnect.repository;

import com.sikehish.collegeconnect.model.Enrollment;
import com.sikehish.collegeconnect.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    // Fetch students enrolled in courses taught by a particular faculty member
    @Query("SELECT sp FROM StudentProfile sp " +
            "JOIN Enrollment e ON e.student.userId = sp.userId " +
            "JOIN e.course c ON c.id = e.course.id " +
            "WHERE c.faculty.user.d = :facultyUserId")
    List<StudentProfile> findByFacultyUserId(@Param("facultyUserId") Long facultyUserId);
}
