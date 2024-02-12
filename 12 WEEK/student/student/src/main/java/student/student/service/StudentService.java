package student.student.service;

import student.student.Student;

import java.util.List;

public interface StudentService {
    Object saveStudent(Student student);
    Student showStudentByStudentId(Long studentId);
    List<Student> showAllStudents();
    Object updateStudentByStudentId(Long studentId, Student updateStudent);
}
