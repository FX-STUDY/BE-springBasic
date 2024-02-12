package student.student.repository;

import org.springframework.stereotype.Repository;
import student.student.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {
    private static final Map<Long, Student> store = new HashMap<>();

    public Student save(Student student){
        store.put(student.getId(), student);
        return student;
    }

    public Student findById(Long studentId){
        return store.get(studentId);
    }

}

