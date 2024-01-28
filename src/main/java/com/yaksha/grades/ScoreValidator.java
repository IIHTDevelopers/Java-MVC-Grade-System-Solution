package com.yaksha.grades;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ScoreValidator implements ConstraintValidator<Score, String> {

    List<String> scores = Arrays.asList(
            "A", "B", "C", "D", "F"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String string : scores) {
            if (value.equals(string)) return true;
        }
        return false;
    }

}
