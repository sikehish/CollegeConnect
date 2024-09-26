package com.sikehish.collegeconnect.service;

import com.sikehish.collegeconnect.model.AcademicInfo;
import com.sikehish.collegeconnect.model.StudentProfile;
import com.sikehish.collegeconnect.repository.AcademicInfoRepository;
import com.sikehish.collegeconnect.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    @Autowired
    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public Optional<StudentProfile> getStudentProfile(Long userId) {
        return studentProfileRepository.findByUserId(userId);
    }

    public StudentProfile getProfileByUsername(String username) {
        Optional<StudentProfile> studentProfileOptional = studentProfileRepository.findByUser_Username(username);
        if (studentProfileOptional.isPresent()) {
            return studentProfileOptional.get();
        } else {
            throw new RuntimeException("Student profile not found for username: " + username);
        }
    }

    @Autowired
    private AcademicInfoRepository academicInfoRepository;

    public List<AcademicInfo> getAcademicInfoByStudentUsername(String studentUsername) {
        return academicInfoRepository.findByStudentUsername(studentUsername);
    }

    public List<StudentProfile> searchStudents(String name, String departmentName, String year) {
        return studentProfileRepository.searchStudents(name, departmentName, year);
    }


    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    public Optional<StudentProfile> getStudentById(Long id) {
        return studentProfileRepository.findById(id);
    }

    public StudentProfile saveStudent(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

    public void deleteStudent(Long id) {
        studentProfileRepository.deleteById(id);
    }
}
