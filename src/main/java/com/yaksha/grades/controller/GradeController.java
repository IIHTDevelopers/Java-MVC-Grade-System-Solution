package com.yaksha.grades.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaksha.grades.entity.Grade;
import com.yaksha.grades.service.GradeService;

import jakarta.validation.Valid;

import java.util.List;

@Controller
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping("/form")
    public String getForm(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("grade", new Grade());
        } else {
            model.addAttribute("grade", gradeService.getGradeById(id));
        }
        return "form-add";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        if (result.hasErrors()) return "form-add";
        gradeService.submitGrade(grade);
        return "redirect:/grades";
    }

    @RequestMapping(value = {"/", "/grades", "/search"})
    public String getGrades(@RequestParam(value = "theSearchName", required = false) String theSearchName,
                            @RequestParam(value = "searchGrade", required = false) String searchGrade,
                            @PageableDefault(size = 5) Pageable pageable,
                            Model model) {
        Page<Grade> grades = gradeService.getGrades(theSearchName, searchGrade, pageable);
        model.addAttribute("grades", grades.getContent());
        model.addAttribute("theSearchName", theSearchName != null ? theSearchName : "");
        model.addAttribute("searchGrade", searchGrade==null || searchGrade.isEmpty() ? "": searchGrade);
        model.addAttribute("availableGrades", List.of("", "A", "B", "C", "D", "E", "F"));
        model.addAttribute("totalPage", grades.getTotalPages());
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("sortBy", pageable.getSort().get().count() != 0 ?
                pageable.getSort().get().findFirst().get().getProperty() + "," + pageable.getSort().get().findFirst().get().getDirection() : "");
        return "gradeview";
    }
}