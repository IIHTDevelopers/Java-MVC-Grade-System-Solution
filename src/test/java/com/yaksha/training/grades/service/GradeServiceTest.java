package com.yaksha.training.grades.service;

import com.yaksha.training.grades.entity.Grade;
import com.yaksha.training.grades.repository.GradeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.yaksha.training.grades.utils.MasterData.asJsonString;
import static com.yaksha.training.grades.utils.MasterData.getGrade;
import static com.yaksha.training.grades.utils.MasterData.getGradeList;
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
    public void testGetGrades() throws Exception {
        List<Grade> actual = getGradeList(5);
        when(gradeRepository.findAll()).thenReturn(actual);
        List<Grade> expected = gradeService.getGrades();
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
}
