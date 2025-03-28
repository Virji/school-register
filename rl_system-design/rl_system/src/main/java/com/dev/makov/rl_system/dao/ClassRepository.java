package com.dev.makov.rl_system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dev.makov.rl_system.entity.Class;
public interface ClassRepository extends JpaRepository<Class, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO class_students (class_id, student_id) VALUES (:classId, :studentId)", nativeQuery = true)
    void saveClassStudent(Long classId, Long studentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM class_students WHERE class_id = :classId AND student_id = :studentId", nativeQuery = true)
    void removeStudentFromClass(Long classId, Long studentId);




}

