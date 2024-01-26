package yebin.students;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentRepositoryTest {
    StudentRepository studentRepository = new StudentRepository();

    @AfterEach
    void afterEach(){
        studentRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Students student = new Students("yebin", 4, "소프트웨어학과");
        //when
        Students saveStudent = studentRepository.save(student);
        //then
        Students findStudent = studentRepository.findById(student.getId());
        Assertions.assertThat(findStudent).isEqualTo(saveStudent);
    }

    @Test
    void findAll(){
        //given
        Students student1 = new Students("yebin", 4, "소프트웨어학과");
        Students student2 = new Students("son", 4, "소프트웨어학과");
        studentRepository.save(student1);
        studentRepository.save(student2);
        //when
        List<Students> students = studentRepository.findAll();
        //then
        Assertions.assertThat(students.size()).isEqualTo(2);
        Assertions.assertThat(students).contains(student1,student2);
    }

    @Test
    void update(){
        //given
        Students student = new Students("yebin", 4, "소프트웨어학과");
        Students savedStudent = studentRepository.save(student);
        Long studentId = savedStudent.getId();
        //when
        Students updateStudent = new Students("son", 2, "전자공학과");
        studentRepository.update(studentId,updateStudent);
        Students findStudent = studentRepository.findById(studentId);
        //then
        Assertions.assertThat(findStudent.getName()).isEqualTo(updateStudent.getName());
        Assertions.assertThat(findStudent.getGrade()).isEqualTo(updateStudent.getGrade());
        Assertions.assertThat(findStudent.getMajor()).isEqualTo(updateStudent.getMajor());
    }

