package com.example.hello.repository;

import com.example.hello.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentRepository {

    private static final Map<Long, Student> store = new HashMap<>();
    private static Long sequence = 0L;

    public Student save(Student student) {
        student.setId(++sequence);
        store.put(student.getId(), student);
        return student;
    }

    public Student findById(Long id) {
        return store.get(id);
    }

    public List<Student> findAll() {
        return new ArrayList<>(store.values());
    }

    public int findStudentCount() {
        return store.size();
    }

    public void update(Long studentCode, Student updateParam) {
        Student findStudent = findById(studentCode);
        findStudent.setName(updateParam.getName());
        findStudent.setDept(updateParam.getDept());
        findStudent.setGrade(updateParam.getGrade());
        findStudent.setStudentCode(updateParam.getStudentCode());
    }
}
