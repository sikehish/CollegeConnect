package com.sikehish.collegeconnect.controller;

import com.sikehish.collegeconnect.model.AcademicInfo;
import com.sikehish.collegeconnect.model.CustomUserDetails;
import com.sikehish.collegeconnect.model.StudentProfile;
import com.sikehish.collegeconnect.service.AcademicInfoService;
import com.sikehish.collegeconnect.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/profile")
    public ResponseEntity<StudentProfile> getStudentProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername();
        String role = userDetails.getRole();
        // Fetch and return the student profile using the username
        StudentProfile profile = studentProfileService.getProfileByUsername(username);
        return ResponseEntity.ok(profile);
    }

    @Autowired
    private AcademicInfoService academicInfoService;

    @GetMapping("/academic")
    public List<AcademicInfo> getAcademicInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        String username = userDetails.getUsername(); // Adjust this based on your UserDetails implementation
        System.out.println("Fetching academic info for: " + username);
        return academicInfoService.getAcademicInfoByStudentUsername(username);
    }


    @GetMapping("/search")
    public List<StudentProfile> searchStudents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String year) {
        return studentProfileService.searchStudents(name, departmentName, year);
    }
}
