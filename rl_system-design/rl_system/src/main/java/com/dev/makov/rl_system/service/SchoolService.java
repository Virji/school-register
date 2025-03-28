package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.entity.School;
import com.dev.makov.rl_system.entity.User;

import java.util.List;

public interface SchoolService {
    List<School> findAll();
    School findById(Long id);
    School save(School school);
    void deleteById(Long id);

    void addTeacherToSchool(User teacher, School school);

    void removeTeacherFromSchool(Long teacherId, Long schoolId);

    void addStudentToSchool(Long studentId, Long schoolId);

    void updateStudentInSchool(Long studentId, Long schoolId);

    void removeStudentFromSchool(Long studentId, Long schoolId);
}
