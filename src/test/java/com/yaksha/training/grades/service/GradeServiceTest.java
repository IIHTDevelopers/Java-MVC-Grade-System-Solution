package com.yaksha.training.grades.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.yaksha.grades.entity.Grade;
import com.yaksha.grades.repository.GradeRepository;
import com.yaksha.grades.service.GradeService;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.grades.utils.MasterData.asJsonString;
import static com.yaksha.training.grades.utils.MasterData.getGrade;
import static com.yaksha.training.grades.utils.MasterData.getGradeList;
import static com.yaksha.training.grades.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.grades.utils.TestUtils.businessTestFile;
import static com.yaksha.training.grades.utils.TestUtils.currentTest;
import static com.yaksha.training.grades.utils.TestUtils.testReport;
import static com.yaksha.training.grades.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testAddGrade() throws Exception {
        Grade actual = getGrade();
        when(gradeRepository.save(actual)).thenReturn(actual);
        Grade expected = gradeService.addGrade(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testUpdateGrade() throws Exception {
        Grade actual = getGrade();
        Grade existing = getGrade();
        when(gradeRepository.save(actual)).thenReturn(actual);
        Grade expected = gradeService.updateGrade(actual, existing);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetGradeById() throws Exception {
        Grade actual = getGrade();
        when(gradeRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
        Grade expected = gradeService.getGradeById(actual.getId());
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testSubmitGradeIfIdIsNull() throws Exception {
        Grade actual = getGrade();
        actual.setId(null);
        when(gradeRepository.save(actual)).thenReturn(actual);
        Grade expected = gradeService.submitGrade(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testSubmitGradeIfIdIsNotNull() throws Exception {
        Grade actual = getGrade();
        Grade existing = getGrade();
        when(gradeRepository.findById(existing.getId())).thenReturn(Optional.of(existing));
        when(gradeRepository.save(actual)).thenReturn(actual);
        Grade expected = gradeService.submitGrade(actual);
        yakshaAssert(currentTest(),
                (asJsonString(expected).equals(asJsonString(actual))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetGradesWithNulKeys() throws Exception {
        List<Grade> grades = getGradeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Grade> expected = new PageImpl<>(grades);
        when(gradeRepository.findAll(pageable)).thenReturn(expected);
        Page<Grade> actual = gradeService.getGrades(null, null, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testGetGradesWithKeys() throws Exception {
        String keyword = randomStringWithSize(1);
        String grade = randomStringWithSize(1);
        List<Grade> grades = getGradeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Grade> expected = new PageImpl<>(grades);
        when(gradeRepository.findGradeByNameSubjectAndGrade(keyword, grade, pageable)).thenReturn(expected);
        Page<Grade> actual = gradeService.getGrades(keyword, grade, pageable);
        yakshaAssert(currentTest(),
                (asJsonString(expected.getContent()).equals(asJsonString(actual.getContent()))
                        ? "true"
                        : "false"),
                businessTestFile);
    }
}
