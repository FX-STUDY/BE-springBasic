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

