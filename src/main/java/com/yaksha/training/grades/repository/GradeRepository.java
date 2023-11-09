package com.yaksha.training.grades.repository;

import com.yaksha.training.grades.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
