package com.example.hello.entity;

import lombok.Data;

@Data
public class Student {
    private String name;
    private String dept;
    private Long id;
    private int grade;
    private String studentCode;

    public Student(String name, String dept, int grade ,String studentCode) {
        this.name = name;
        this.dept = dept;
        this.grade = grade;
        this.studentCode = studentCode;
    }
}
