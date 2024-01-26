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

