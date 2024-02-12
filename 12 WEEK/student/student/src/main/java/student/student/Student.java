package student.student;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import student.student.ENUM.Grade;
import student.student.ENUM.Major;
import student.student.ENUM.Semester;

@Getter
@Setter
public class Student {
    private Long id;
    private Grade grade;
    private String name;
    private String brith;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthMonthOfDay;
    private Major major;
    private Semester semester;
    private String address;

    public Student(Long id, Grade grade, String name, String brith, Integer birthYear, Integer birthMonth, Integer birthMonthOfDay, Major major, Semester semester, String address) {
        this.id = id;
        this.grade = grade;
        this.name = name;
        this.brith = brith;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthMonthOfDay = birthMonthOfDay;
        this.major = major;
        this.semester = semester;
        this.address = address;
    }

    public void signUpFormData(Long id, String name, String birth, Semester semester, String address, Grade grade){
        this.id = id;
        this.name = name;
        this.brith = birth;
        this.semester = semester;
        this.address = address;
        this.grade = grade;
    }

    public void updateFormData(String name, String address){
        this.name = name;
        this.address = address;
    }
}