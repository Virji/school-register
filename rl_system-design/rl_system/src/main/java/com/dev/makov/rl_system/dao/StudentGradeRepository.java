package com.dev.makov.rl_system.dao;


import com.dev.makov.rl_system.entity.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {
    List<StudentGrade> findByStudentId(Long studentId);
}
