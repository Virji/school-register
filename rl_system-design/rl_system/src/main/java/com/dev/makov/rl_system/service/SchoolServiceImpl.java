package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dao.SchoolRepository;
import com.dev.makov.rl_system.dao.UserRepository;
import com.dev.makov.rl_system.entity.School;
import com.dev.makov.rl_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;
    private UserRepository userRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, UserRepository userRepository) {
        this.schoolRepository = schoolRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    @Override
    public School findById(Long id) {
        Optional<School> result = schoolRepository.findById(id);
        School school = null;
        if (result.isPresent()) {
            school = result.get();
        } else {
            throw new RuntimeException("Did not find school id - " + id);
        }
        return school;
    }

    @Override
    public School save(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public void deleteById(Long id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public void addTeacherToSchool(User teacher, School school) {
        school.getTeachers().add(teacher);
        schoolRepository.save(school);
    }

    @Override
    public void removeTeacherFromSchool(Long teacherId, Long schoolId) {
        School school = schoolRepository.findById(schoolId).orElseThrow(() -> new RuntimeException("School not found"));
        User teacher = userRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        school.getTeachers().remove(teacher);
        schoolRepository.save(school);
    }

    @Override
    public void addStudentToSchool(Long studentId, Long schoolId) {
        School school = findById(schoolId);
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        school.getStudents().add(student);
        schoolRepository.save(school);
    }

    @Override
    public void updateStudentInSchool(Long studentId, Long schoolId) {
        // Similar logic to addStudentToSchool if needed
    }


    @Override
    public void removeStudentFromSchool(Long studentId, Long schoolId) {
        School school = findById(schoolId);
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        school.getStudents().remove(student);
        schoolRepository.save(school);
    }
}
