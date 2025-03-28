package com.dev.makov.rl_system.service;

import com.dev.makov.rl_system.dao.SchoolSubjectRepository;
import com.dev.makov.rl_system.entity.SchoolSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolSubjectService {
    private SchoolSubjectRepository schoolSubjectRepository;

    @Autowired
    public SchoolSubjectService(SchoolSubjectRepository schoolSubjectRepository) {
        this.schoolSubjectRepository = schoolSubjectRepository;
    }

    public List<SchoolSubject> findAll() {
        return schoolSubjectRepository.findAll();
    }


    public List<SchoolSubject> findSubjectsByStudentId(Long studentId) {
        return schoolSubjectRepository.findByStudentId(studentId);
    }
}
