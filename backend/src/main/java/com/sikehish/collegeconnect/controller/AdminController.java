package com.sikehish.collegeconnect.controller;

import com.sikehish.collegeconnect.model.FacultyProfile;
import com.sikehish.collegeconnect.model.StudentProfile;
import com.sikehish.collegeconnect.service.FacultyProfileService;
import com.sikehish.collegeconnect.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private StudentProfileService studentProfileService;

    @Autowired
    private FacultyProfileService facultyProfileService;

    // CRUD operations for Student Profiles
    @GetMapping("/students")
    public List<StudentProfile> getAllStudents() {
        return studentProfileService.getAllStudents();
    }

    @PostMapping("/students")
    public StudentProfile addStudent(@RequestBody StudentProfile studentProfile) {
        return studentProfileService.saveStudent(studentProfile);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<StudentProfile> updateStudent(@PathVariable Long id, @RequestBody StudentProfile studentProfile) {
        studentProfile.setUserId(id);
        return ResponseEntity.ok(studentProfileService.saveStudent(studentProfile));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentProfileService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    // CRUD operations for Faculty Profiles
    @GetMapping("/faculties")
    public List<FacultyProfile> getAllFaculties() {
        return facultyProfileService.getAllFaculties();
    }

    @PostMapping("/faculties")
    public FacultyProfile addFaculty(@RequestBody FacultyProfile facultyProfile) {
        return facultyProfileService.saveFaculty(facultyProfile);
    }

    @PutMapping("/faculties/{id}")
    public ResponseEntity<FacultyProfile> updateFaculty(@PathVariable Long id, @RequestBody FacultyProfile facultyProfile) {
        facultyProfile.setUserId(id);
        return ResponseEntity.ok(facultyProfileService.saveFaculty(facultyProfile));
    }

    @DeleteMapping("/faculties/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyProfileService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
