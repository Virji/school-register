package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dao.GradeRepository;
import com.dev.makov.rl_system.dao.StudentGradeRepository;
import com.dev.makov.rl_system.dao.SchoolSubjectRepository;
import com.dev.makov.rl_system.dao.UserRepository;
import com.dev.makov.rl_system.entity.Grade;
import com.dev.makov.rl_system.entity.SchoolSubject;
import com.dev.makov.rl_system.entity.StudentGrade;
import com.dev.makov.rl_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    @Autowired
    private SchoolSubjectRepository schoolSubjectRepository;

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void addGradeToStudent(Long gradeId, Long studentId, Long subjectId) {
        Grade grade = gradeRepository.findById(gradeId).orElseThrow(() -> new RuntimeException("Grade not found with id: " + gradeId));
        User student = userRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        SchoolSubject subject = schoolSubjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setGrade(grade);
        studentGrade.setStudent(student);
        studentGrade.setSchoolSubject(subject);

        studentGradeRepository.save(studentGrade);
    }

    public Grade findGradeById(Long id) {
        Optional<Grade> result = gradeRepository.findById(id);
        return result.orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }

    public Grade findGradeByValue(int gradeValue) {
        return gradeRepository.findByGrade(gradeValue).orElseThrow(() -> new RuntimeException("Grade not found with value: " + gradeValue));
    }

    public List<StudentGrade> findGradesByStudentId(Long studentId) {
        return studentGradeRepository.findByStudentId(studentId);
    }

}
