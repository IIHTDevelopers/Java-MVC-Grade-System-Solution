package com.yaksha.training.grades.exception;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.yaksha.grades.controller.GradeController;
import com.yaksha.grades.entity.Grade;

import static com.yaksha.training.grades.utils.MasterData.getGrade;
import static com.yaksha.training.grades.utils.TestUtils.currentTest;
import static com.yaksha.training.grades.utils.TestUtils.exceptionTestFile;
import static com.yaksha.training.grades.utils.TestUtils.testReport;
import static com.yaksha.training.grades.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class GradeExceptionTest {

    @InjectMocks
    private GradeController gradeController;

    private MockMvc mockMvc;

    BindingResult bindingResult = mock(BindingResult.class);


    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testExceptionSubmitForm() throws Exception {
        Grade grade = getGrade();
        grade.setName(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("grade", grade)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionSubmitFormAsSubjectAsNull() throws Exception {
        Grade grade = getGrade();
        grade.setSubject(null);
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("grade", grade)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form-add")), exceptionTestFile);
    }

    @Test
    public void testExceptionSubmitFormAsScoreAsInvalidValue() throws Exception {
        Grade grade = getGrade();
        grade.setScore("Z");
        when(bindingResult.hasErrors()).thenReturn(true);
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("grade", grade)).andReturn();
        yakshaAssert(currentTest(), (result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form-add")), exceptionTestFile);
    }


}
