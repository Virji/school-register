package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dto.StudentClassDTO;
import com.dev.makov.rl_system.entity.Class;
import com.dev.makov.rl_system.entity.User;

import java.util.List;

public interface ClassService {

    List<Class> findAll();

    Class findById(Long theId);

    Class save(Class theClass);

    void deleteById(Long theId);
    public void addStudentToClass(StudentClassDTO studentClassDTO);

    void updateStudentInClass(Long classId, User student);


}
