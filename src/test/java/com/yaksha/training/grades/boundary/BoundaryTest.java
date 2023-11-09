package com.yaksha.training.grades.boundary;


import com.yaksha.training.grades.entity.Grade;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.yaksha.training.grades.utils.MasterData.getGrade;
import static com.yaksha.training.grades.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.grades.utils.TestUtils.boundaryTestFile;
import static com.yaksha.training.grades.utils.TestUtils.currentTest;
import static com.yaksha.training.grades.utils.TestUtils.testReport;
import static com.yaksha.training.grades.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testNameNotBlank() throws Exception {
        Grade grade = getGrade();
        grade.setName("");
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameNotNull() throws Exception {
        Grade grade = getGrade();
        grade.setName(null);
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMinThree() throws Exception {
        Grade grade = getGrade();
        grade.setName(randomStringWithSize(2));
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMaxFifty() throws Exception {
        Grade grade = getGrade();
        grade.setName(randomStringWithSize(51));
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testSubjectNotBlank() throws Exception {
        Grade grade = getGrade();
        grade.setSubject("");
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testSubjectNotNull() throws Exception {
        Grade grade = getGrade();
        grade.setSubject(null);
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testSubjectMinThree() throws Exception {
        Grade grade = getGrade();
        grade.setSubject(randomStringWithSize(2));
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testSubjectMaxFifty() throws Exception {
        Grade grade = getGrade();
        grade.setSubject(randomStringWithSize(51));
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testScoreNotBlank() throws Exception {
        Grade grade = getGrade();
        grade.setScore("");
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testScoreNotBeExceptABCDEF() throws Exception {
        Grade grade = getGrade();
        grade.setScore("G");
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
}
