package com.yaksha.training.grades.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.yaksha.grades.entity.Grade;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MasterData {

    public static Grade getGrade() {
        Grade grade = new Grade();
        grade.setId(1L);
        grade.setName(randomStringWithSize(10));
        grade.setSubject(randomStringWithSize(10));
        grade.setScore("A");
        return grade;
    }

    public static List<Grade> getGradeList(int size) {
        Long id = 0L;
        List<Grade> grades = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Grade grade = new Grade();
            grade.setId(++id);
            grade.setName(randomStringWithSize(10));
            grade.setSubject(randomStringWithSize(10));
            grade.setScore("A+");
            grades.add(grade);
        }
        return grades;
    }

    private static Random rnd = new Random();

    public static String randomStringWithSize(int size) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        String s = "";
        for (int i = 0; i < size; i++) {
            s = s + (String.valueOf(alphabet.charAt(rnd.nextInt(alphabet.length()))));
        }
        return s;
    }

    public static boolean randomBoolean() {
        String alphabet = "1234567890";
        Random rnd = new Random();
        return rnd.nextInt(alphabet.length()) % 2 == 0;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
