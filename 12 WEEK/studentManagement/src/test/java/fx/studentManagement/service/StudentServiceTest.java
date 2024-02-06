package fx.studentManagement.service;

import fx.studentManagement.entity.Student;
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
        Student stu1 = Student.builder()
                .studentNumber(21000L)
                .studentName("PARK")
                .studentGrade(2)
                .studentMajor("SoftwareDept")
                .build();
        studentService.signUp(stu1);


        Student stu2 = Student.builder()
                .studentNumber(19000L)
                .studentName("KIM")
                .studentGrade(4)
                .studentMajor("SoftwareDept")
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
        Student stu = Student.builder()
                .studentNumber(20000L)
                .studentName("LEE")
                .studentGrade(3)
                .studentMajor("SoftwareDept")
                .build();


        //when
        studentService.signUp(stu);

        //then
        Assertions.assertThat(stu.getStudentNumber()).isEqualTo(20000L);
        Assertions.assertThat(stu.getStudentName()).isEqualTo("LEE");
        Assertions.assertThat(stu.getStudentGrade()).isEqualTo(3);
        Assertions.assertThat(stu.getStudentMajor()).isEqualTo("SoftwareDept");
    }

    @Test
    void showStudent() {
        //give

        //when
        Student stu1 = studentService.showStudent(21000L);
        Student stu2 = studentService.showStudent(19000L);

        //then
        Assertions.assertThat(stu1.getStudentNumber()).isEqualTo(21000L);
        Assertions.assertThat(stu1.getStudentName()).isEqualTo("PARK");
        Assertions.assertThat(stu1.getStudentGrade()).isEqualTo(2);
        Assertions.assertThat(stu1.getStudentMajor()).isEqualTo("SoftwareDept");

        Assertions.assertThat(stu2.getStudentNumber()).isEqualTo(19000L);
        Assertions.assertThat(stu2.getStudentName()).isEqualTo("KIM");
        Assertions.assertThat(stu2.getStudentGrade()).isEqualTo(4);
        Assertions.assertThat(stu2.getStudentMajor()).isEqualTo("SoftwareDept");
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
        studentService.deleteStudent(21000L);
        List<Student> students = studentService.showAllStudent();
        System.out.println("students = " + students);
        //then
        Assertions.assertThat(students.size()).isEqualTo(3);
        
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
        Student updateStu = Student.builder()
                .studentName("PARK")
                .studentGrade(1)
                .studentMajor("ComputerDept")
                .build();

        //when
        studentService.updateStudent(21000L, updateStu);
        Student student = studentService.showStudent(21000L);
        //then
        Assertions.assertThat(student.getStudentName()).isEqualTo("PARK");
        Assertions.assertThat(student.getStudentGrade()).isEqualTo(1);
        Assertions.assertThat(student.getStudentMajor()).isEqualTo("ComputerDept");

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