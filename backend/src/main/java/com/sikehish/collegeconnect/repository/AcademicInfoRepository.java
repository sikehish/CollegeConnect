package com.sikehish.collegeconnect.repository;

import com.sikehish.collegeconnect.model.AcademicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicInfoRepository extends JpaRepository<AcademicInfo, Long> {
    List<AcademicInfo> findByStudentUsername(String studentUsername);
}
