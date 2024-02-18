package fx.studentManagement.service;

import fx.studentManagement.controller.form.EditStudentForm;
import fx.studentManagement.controller.form.SignUpForm;
import fx.studentManagement.entity.Student;
import fx.studentManagement.entity.enums.Major;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @BeforeEach
    public void BeforeEach() {
        SignUpForm stu1 = SignUpForm.builder()
                .studentNumber(2100000L)
                .studentName("PARK")
                .studentBirth("2001_01_01")
                .studentMajor(Major.valueOf("SoftwareDept"))
                .studentSemester(2)
                .studentAddress("충주시")
                .studentGrade(1)
                .build();
        studentService.signUp(stu1);


        SignUpForm stu2 = SignUpForm.builder()
                .studentNumber(2200000L)
                .studentName("KIM")
                .studentBirth("2001_01_01")
                .studentMajor(Major.valueOf("SoftwareDept"))
                .studentSemester(2)
                .studentAddress("충주시")
                .studentGrade(1)
                .build();
        studentService.signUp(stu2);
    }

    @AfterEach
    public void afterEach() {
        studentService.deleteAllStudent();
    }

    @Test
    void signUp() {
        //give
        SignUpForm stu = SignUpForm.builder()
                .studentNumber(2300000L)
                .studentName("LEE")
                .studentBirth("2001_01_01")
                .studentMajor(Major.valueOf("SoftwareDept"))
                .studentSemester(2)
                .studentAddress("충주시")
                .studentGrade(3)
                .build();

        //when
        studentService.signUp(stu);

        //then
        Assertions.assertThat(stu.getStudentNumber()).isEqualTo(2300000L);
        Assertions.assertThat(stu.getStudentName()).isEqualTo("LEE");
        Assertions.assertThat(stu.getStudentGrade()).isEqualTo(3);
        Assertions.assertThat(stu.getStudentMajor()).isEqualTo(Major.SoftwareDept);
    }

    @Test
    void showStudent() {
        //give

        //when
        Student stu1 = studentService.showStudent(2100000L);
        Student stu2 = studentService.showStudent(2200000L);

        //then
        Assertions.assertThat(stu1.getStudentNumber()).isEqualTo(2100000L);
        Assertions.assertThat(stu1.getStudentName()).isEqualTo("PARK");
        Assertions.assertThat(stu1.getStudentGrade()).isEqualTo(1);
        Assertions.assertThat(stu1.getStudentMajor()).isEqualTo(Major.SoftwareDept);

        Assertions.assertThat(stu2.getStudentNumber()).isEqualTo(2200000L);
        Assertions.assertThat(stu2.getStudentName()).isEqualTo("KIM");
        Assertions.assertThat(stu2.getStudentGrade()).isEqualTo(1);
        Assertions.assertThat(stu2.getStudentMajor()).isEqualTo(Major.SoftwareDept);
    }

    @Test
    void showAllStudent() {
        //give

        //when
        List<Student> students = studentService.showAllStudent();
        //then
        Assertions.assertThat(students.size()).isEqualTo(2);
    }

    @Test
    void deleteStudent() {
        //give

        //when
        studentService.deleteStudent(2100000L);
        List<Student> students = studentService.showAllStudent();
        System.out.println("students = " + students);
        //then
        Assertions.assertThat(students.size()).isEqualTo(1);
        
    }

    @Test
    void deleteAllStudent() {
        //give

        //when
        studentService.deleteAllStudent();
        List<Student> students = studentService.showAllStudent();

        //then
        Assertions.assertThat(students.size()).isEqualTo(0);

    }

    @Test
    void updateStudent() {
        //give
        EditStudentForm updateStu = EditStudentForm.builder()
                .studentNumber(2100000L)
                .studentName("CHOI")
                .studentAddress("서울시")
                .build();

        //when
        studentService.editStudentInformation(updateStu);
        Student student = studentService.showStudent(2100000L);

        //then
        Assertions.assertThat(student.getStudentName()).isEqualTo("CHOI");
        Assertions.assertThat(student.getStudentAddress()).isEqualTo("서울시");

    }

    @Test
    void countAllStudent() {
        //give

        //when
        int cnt = studentService.countAllStudent();
        //then
        Assertions.assertThat(cnt).isEqualTo(2);
    }
}