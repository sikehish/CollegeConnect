package com.sikehish.collegeconnect.service;

import com.sikehish.collegeconnect.model.FacultyProfile;
import com.sikehish.collegeconnect.model.StudentProfile;
import com.sikehish.collegeconnect.repository.EnrollmentRepository;
import com.sikehish.collegeconnect.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyProfileService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Fetch class list (students enrolled in the faculty member's courses)
    public List<StudentProfile> getClassList(Long facultyUserId) {
        return enrollmentRepository.findByFacultyUserId(facultyUserId);
    }

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    public Optional<FacultyProfile> updateProfile(Long userId, String officeHours, String contactEmail, String phoneNumber) {
        Optional<FacultyProfile> optionalProfile = facultyProfileRepository.findByUserId(userId);
        if (optionalProfile.isPresent()) {
            FacultyProfile profile = optionalProfile.get();
            profile.setOfficeHours(officeHours);
            profile.getUser().setEmail(contactEmail);
            profile.getUser().setPhone(phoneNumber);
            facultyProfileRepository.save(profile);
            return Optional.of(profile);
        }
        return Optional.empty();
    }
    public List<FacultyProfile> getAllFaculties() {
        return facultyProfileRepository.findAll();
    }

    public Optional<FacultyProfile> getFacultyById(Long id) {
        return facultyProfileRepository.findById(id);
    }

    public FacultyProfile saveFaculty(FacultyProfile facultyProfile) {
        return facultyProfileRepository.save(facultyProfile);
    }

    public void deleteFaculty(Long id) {
        facultyProfileRepository.deleteById(id);
    }
}
