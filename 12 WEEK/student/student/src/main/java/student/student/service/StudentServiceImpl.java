package student.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import student.student.Student;
import student.student.repository.StudentRepository;
import student.student.validation.ValidateStudent;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    private ValidateStudent validateStudent;
}
