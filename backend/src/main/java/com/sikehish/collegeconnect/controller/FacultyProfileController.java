package com.sikehish.collegeconnect.controller;
import com.sikehish.collegeconnect.model.FacultyProfile;
import com.sikehish.collegeconnect.model.FacultyProfileUpdateRequest;
import com.sikehish.collegeconnect.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyProfileController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @PutMapping("/profile/update")
    public ResponseEntity<FacultyProfile> updateProfile(@RequestBody FacultyProfileUpdateRequest updateRequest) {
        Optional<FacultyProfile> updatedProfile = facultyProfileService.updateProfile(
                updateRequest.getUserId(),
                updateRequest.getOfficeHours(),
                updateRequest.getContactEmail(),
                updateRequest.getPhoneNumber()
        );

        if (updatedProfile.isPresent()) {
            return ResponseEntity.ok(updatedProfile.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
