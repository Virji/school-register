package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dao.ClassRepository;
import com.dev.makov.rl_system.dao.UserRepository;
import com.dev.makov.rl_system.dto.StudentClassDTO;
import com.dev.makov.rl_system.entity.Class;
import com.dev.makov.rl_system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    private ClassRepository classRepository;
    private UserRepository userRepository;

    @Autowired
    public ClassServiceImpl(ClassRepository classRepository, UserRepository userRepository) {
        this.classRepository = classRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Class findById(Long theId) {
        Optional<Class> result = classRepository.findById(theId);
        Class theClass = null;
        if (result.isPresent()) {
            theClass = result.get();
        } else {
            throw new RuntimeException("Did not find class id - " + theId);
        }
        return theClass;
    }

    @Override
    public Class save(Class theClass) {
        return classRepository.save(theClass);
    }

    @Override
    public void deleteById(Long theId) {
        classRepository.deleteById(theId);
    }

    @Override
    public void addStudentToClass(StudentClassDTO studentClassDTO) {
        System.out.println("Adding student to class - Class ID: " + studentClassDTO.getClassId() + ", Student ID: " + studentClassDTO.getStudentId());
        Optional<Class> classResult = classRepository.findById(studentClassDTO.getClassId());
        if (classResult.isPresent()) {
            Class theClass = classResult.get();
            Optional<User> studentResult = userRepository.findById(studentClassDTO.getStudentId());
            if (studentResult.isPresent()) {
                User student = studentResult.get();
                // Add student to class
                classRepository.saveClassStudent(studentClassDTO.getClassId(), studentClassDTO.getStudentId());
            } else {
                throw new RuntimeException("Student not found - " + studentClassDTO.getStudentId());
            }
        } else {
            throw new RuntimeException("Class not found - " + studentClassDTO.getClassId());
        }
    }

    @Override
    public void updateStudentInClass(Long classId, User user) {
        Class theClass = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        theClass.updateUser(user);
        classRepository.save(theClass);
    }


}
