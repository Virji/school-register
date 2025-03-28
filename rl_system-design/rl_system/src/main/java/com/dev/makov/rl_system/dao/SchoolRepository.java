package com.dev.makov.rl_system.dao;

import com.dev.makov.rl_system.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
