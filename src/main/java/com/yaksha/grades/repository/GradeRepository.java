package com.yaksha.grades.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yaksha.grades.entity.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Page<Grade> findAll(Pageable pageable);

    @Query(value = "Select g FROM Grade g "
            + "where (:keyword IS NULL "
            + "OR lower(g.name) like %:keyword% "
            + "OR lower(g.subject) like %:keyword%) "
            + "AND (:grade IS NULL OR g.score = :grade )")
    Page<Grade> findGradeByNameSubjectAndGrade(@Param("keyword") String keyword,
                                                   @Param("grade") String grade,
                                                   Pageable pageable);


}
