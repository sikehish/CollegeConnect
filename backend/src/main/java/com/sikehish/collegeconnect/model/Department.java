package com.sikehish.collegeconnect.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentProfile> students;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FacultyProfile> facultyMembers;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdministratorProfile> administrators;

    // Default constructor
    public Department() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<StudentProfile> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentProfile> students) {
        this.students = students;
    }

    public Set<FacultyProfile> getFacultyMembers() {
        return facultyMembers;
    }

    public void setFacultyMembers(Set<FacultyProfile> facultyMembers) {
        this.facultyMembers = facultyMembers;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<AdministratorProfile> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(Set<AdministratorProfile> administrators) {
        this.administrators = administrators;
    }
}
