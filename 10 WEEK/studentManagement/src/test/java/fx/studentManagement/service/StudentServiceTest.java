package fx.studentManagement.service;

import fx.studentManagement.entity.Student;
import fx.studentManagement.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @BeforeEach
    public void BeforeEach() {
        Student stu1 = new Student();
        stu1.setStuNum(2100000L);
        stu1.setStuName("PARK");
        stu1.setStuGrade(2);
        stu1.setStuMajor("SoftwareDept");
        studentService.signUp(stu1);

        Student stu2 = new Student();
        stu2.setStuNum(1900000L);
        stu2.setStuName("KIM");
        stu2.setStuGrade(4);
        stu2.setStuMajor("SoftwareDept");
        studentService.signUp(stu2);
    }

    @AfterEach
    public void afterEach() {
        studentService.deleteAllStudent();
    }

    @Test
    void signUp() {
        //give
        Student stu = new Student();
        stu.setStuNum(2000000L);
        stu.setStuName("LEE");
        stu.setStuGrade(3);
        stu.setStuMajor("SoftwareDept");

        //when
        studentService.signUp(stu);

        //then
        Assertions.assertThat(stu.getStuNum()).isEqualTo(2000000L);
        Assertions.assertThat(stu.getStuName()).isEqualTo("LEE");
        Assertions.assertThat(stu.getStuGrade()).isEqualTo(3);
        Assertions.assertThat(stu.getStuMajor()).isEqualTo("SoftwareDept");
    }

    @Test
    void showStudent() {
        //give

        //when
        Student stu1 = studentService.showStudent(2100000L);
        Student stu2 = studentService.showStudent(1900000L);

        //then
        Assertions.assertThat(stu1.getStuNum()).isEqualTo(2100000L);
        Assertions.assertThat(stu1.getStuName()).isEqualTo("PARK");
        Assertions.assertThat(stu1.getStuGrade()).isEqualTo(2);
        Assertions.assertThat(stu1.getStuMajor()).isEqualTo("SoftwareDept");

        Assertions.assertThat(stu2.getStuNum()).isEqualTo(1900000L);
        Assertions.assertThat(stu2.getStuName()).isEqualTo("KIM");
        Assertions.assertThat(stu2.getStuGrade()).isEqualTo(4);
        Assertions.assertThat(stu2.getStuMajor()).isEqualTo("SoftwareDept");
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
        Student updateStu = new Student();
        updateStu.setStuName("PARK");
        updateStu.setStuGrade(1);
        updateStu.setStuMajor("ComputerDept");

        //when
        studentService.updateStudent(2100000L, updateStu);
        Student student = studentService.showStudent(2100000L);
        //then
        Assertions.assertThat(student.getStuName()).isEqualTo("PARK");
        Assertions.assertThat(student.getStuGrade()).isEqualTo(1);
        Assertions.assertThat(student.getStuMajor()).isEqualTo("ComputerDept");

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