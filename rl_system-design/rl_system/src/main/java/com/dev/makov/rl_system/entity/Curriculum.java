package com.dev.makov.rl_system.entity;

import jakarta.persistence.*;

import javax.security.auth.Subject;
import java.util.List;

@Entity
@Table(name = "curriculum")
public class Curriculum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class schoolClass;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SchoolSubject schoolSubject;

    // Constructors, getters, and setters
    public Curriculum() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Class getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(Class schoolClass) {
        this.schoolClass = schoolClass;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public SchoolSubject getSchoolSubject() {
        return schoolSubject;
    }

    public void setSchoolSubject(SchoolSubject schoolSubject) {
        this.schoolSubject = schoolSubject;
    }
}
