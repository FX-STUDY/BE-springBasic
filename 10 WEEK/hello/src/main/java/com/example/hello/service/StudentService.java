package com.example.hello.service;

import com.example.hello.entity.Student;
import com.example.hello.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> showAllStudents() {
        return studentRepository.findAll();
    }
    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    public Student findStudent(Long studentCode) {
        Student foundStudent = studentRepository.findById(studentCode);
        return foundStudent;
    }

    public void updateStudent(Long studentCode, Student updateParam) {
        studentRepository.update(studentCode,updateParam);
    }
}
