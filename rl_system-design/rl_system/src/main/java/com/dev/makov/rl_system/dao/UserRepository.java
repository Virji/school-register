package com.dev.makov.rl_system.dao;

import com.dev.makov.rl_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_roles SET role_name = :roleName WHERE user_id = :userId AND role_id = :roleId", nativeQuery = true)
    void updateUserRoleName(Long userId, Long roleId, String roleName);

    List<User> findBySchoolIdAndUserRole(Long schoolId, String userRole);

    @Query("SELECT u FROM User u WHERE u.aClass.id = :classId AND u.userRole = 'ROLE_STUDENT'")
    List<User> findStudentsByClassId(@Param("classId") Long classId);

    @Query("SELECT u FROM User u WHERE u.userRole = 'ROLE_TEACHER'")
    List<User> findAllTeachers();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_school_subjects (user_id, school_subject_id) VALUES (:userId, :subjectId)", nativeQuery = true)
    void addSchoolSubjectToStudent(Long userId, Long subjectId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO school_students (school_id, student_id) VALUES (:schoolId, :studentId)", nativeQuery = true)
    void addStudentToSchool(@Param("studentId") Long studentId, @Param("schoolId") Long schoolId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO parent_student (user_id, student_id) VALUES (:parentId, :studentId)", nativeQuery = true)
    void addParentToStudent(@Param("parentId") Long parentId, @Param("studentId") Long studentId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM parent_student WHERE user_id = :parentId AND student_id = :studentId", nativeQuery = true)
    void removeParentFromStudent(@Param("parentId") Long parentId, @Param("studentId") Long studentId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO student_grades (grade_id, student_id) VALUES (:gradeId, :studentId)", nativeQuery = true)
    void addGradeToStudent(@Param("gradeId") Long gradeId, @Param("studentId") Long studentId);
}