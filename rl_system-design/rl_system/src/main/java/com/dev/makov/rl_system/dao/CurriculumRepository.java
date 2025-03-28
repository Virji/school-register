package com.dev.makov.rl_system.dao;

import com.dev.makov.rl_system.entity.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    List<Curriculum> findBySchoolClassId(Long classId);
}



