package com.yaksha.training.grades.service;

import com.yaksha.training.grades.entity.Grade;
import com.yaksha.training.grades.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Grade> getGrades() {
        return gradeRepository.findAll();
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
