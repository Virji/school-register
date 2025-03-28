package com.dev.makov.rl_system.dao;

import com.dev.makov.rl_system.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Optional<Grade> findByGrade(int grade);
}



