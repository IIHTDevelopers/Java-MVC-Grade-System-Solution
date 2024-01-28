package com.yaksha.training.grades.functional;

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
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.yaksha.grades.controller.GradeController;
import com.yaksha.grades.entity.Grade;
import com.yaksha.grades.service.GradeService;

import java.util.List;

import static com.yaksha.training.grades.utils.MasterData.getGrade;
import static com.yaksha.training.grades.utils.MasterData.getGradeList;
import static com.yaksha.training.grades.utils.MasterData.randomStringWithSize;
import static com.yaksha.training.grades.utils.TestUtils.businessTestFile;
import static com.yaksha.training.grades.utils.TestUtils.currentTest;
import static com.yaksha.training.grades.utils.TestUtils.testReport;
import static com.yaksha.training.grades.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class GradeControllerTest {

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeController gradeController;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(gradeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @After
    public void afterAll() {
        testReport();
    }

    @Test
    public void testControllerGetForm() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/form")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form-add"), businessTestFile);
    }


    @Test
    public void testControllerGetFormById() throws Exception {
        Grade grade = getGrade();
        when(gradeService.getGradeById(grade.getId())).thenReturn(grade);
        MvcResult result = this.mockMvc.perform(get("/form")
                .param("id", grade.getId().toString())).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("form-add"), businessTestFile);
    }

    @Test
    public void testControllerGetGrades() throws Exception {
        List<Grade> grades = getGradeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Grade> gradePage = new PageImpl<>(grades);
        when(gradeService.getGrades(null, null, pageable)).thenReturn(gradePage);
        MvcResult result = this.mockMvc.perform(get("/grades")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("gradeview"), businessTestFile);

    }

    @Test
    public void testControllerSubmitForm() throws Exception {
        Grade grade = getGrade();
        MvcResult result = this.mockMvc.perform(post("/handleSubmit")
                .flashAttr("grade", grade)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("redirect:/grades"), businessTestFile);

    }

    @Test
    public void testControllerGetGradesByDefault() throws Exception {
        List<Grade> grades = getGradeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Grade> gradePage = new PageImpl<>(grades);
        when(gradeService.getGrades(null, null, pageable)).thenReturn(gradePage);
        MvcResult result = this.mockMvc.perform(get("/")).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("gradeview"), businessTestFile);

    }

    @Test
    public void testControllerGetGradesBySearch() throws Exception {
        String keyword = randomStringWithSize(1);
        String grade = randomStringWithSize(1);
        List<Grade> grades = getGradeList(5);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Grade> gradePage = new PageImpl<>(grades);
        when(gradeService.getGrades(keyword, grade, pageable)).thenReturn(gradePage);
        MvcResult result = this.mockMvc.perform(get("/search")
                .param("theSearchName", keyword)
                .param("searchGrade", grade)).andReturn();
        yakshaAssert(currentTest(), result.getModelAndView() != null
                && result.getModelAndView().getViewName() != null
                && result.getModelAndView().getViewName().contentEquals("gradeview"), businessTestFile);

    }


}
