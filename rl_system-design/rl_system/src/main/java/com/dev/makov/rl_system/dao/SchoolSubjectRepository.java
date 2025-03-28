package com.dev.makov.rl_system.dao;

import com.dev.makov.rl_system.entity.SchoolSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Long> {
    // Custom queries if needed

    @Query("SELECT ss FROM SchoolSubject ss JOIN ss.students s WHERE s.id = :studentId")
    List<SchoolSubject> findByStudentId(Long studentId);

    //yes it works :)
}