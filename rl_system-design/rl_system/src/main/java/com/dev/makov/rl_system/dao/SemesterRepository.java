package com.dev.makov.rl_system.dao;
import com.dev.makov.rl_system.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    // Custom database queries can be defined here
}