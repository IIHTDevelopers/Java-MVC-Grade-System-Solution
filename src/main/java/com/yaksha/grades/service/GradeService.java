package com.yaksha.grades.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yaksha.grades.entity.Grade;
import com.yaksha.grades.repository.GradeRepository;

import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Grade grade, Grade existing) {
        grade.setId(existing.getId());
        return gradeRepository.save(grade);
    }

    public Page<Grade> getGrades(String keyword, String gradeSearch, Pageable pageable) {
        if ((keyword == null || keyword.isEmpty()) && (gradeSearch == null || gradeSearch.isEmpty())) {
            return gradeRepository.findAll(pageable);
        } else {
            keyword = keyword == null || keyword.isEmpty() ? null : keyword;
            gradeSearch = gradeSearch == null || gradeSearch.isEmpty() ? null : gradeSearch;
            return gradeRepository.findGradeByNameSubjectAndGrade(keyword, gradeSearch, pageable);
        }
    }

    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).get();
    }

    public Grade submitGrade(Grade grade) {
        if (grade.getId() == null) {
            return addGrade(grade);
        } else {
            Optional<Grade> existingGrade = gradeRepository.findById(grade.getId());
            return updateGrade(grade, existingGrade.get());
        }
    }
}
