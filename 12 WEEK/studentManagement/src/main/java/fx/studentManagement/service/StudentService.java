package fx.studentManagement.service;

import fx.studentManagement.common.exception.DuplicateStudentException;
import fx.studentManagement.common.exception.NotFoundStudentException;
import fx.studentManagement.controller.form.EditStudentForm;
import fx.studentManagement.controller.form.SignUpForm;
import fx.studentManagement.entity.Student;
import fx.studentManagement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public void signUp(SignUpForm signUpForm) { //학생 입력

        if(studentRepository.findByStudentNumber(signUpForm.getStudentNumber()) != null)
            throw new DuplicateStudentException();

        Student student = saveStudent(signUpForm);

        studentRepository.save(student);
    }


    public Student showStudent(Long studentNumber) { //단일 학생 조회
        if (studentRepository.findByStudentNumber(studentNumber) == null)
            throw new NotFoundStudentException();
        return studentRepository.findByStudentNumber(studentNumber);
    }

    public List<Student> showAllStudent() { //모든 학생 조회
        if(studentRepository.findAll() == null)
            throw new NotFoundStudentException();
        return studentRepository.findAll();
    }

    public void deleteStudent(Long studentNumber) { // 단일 학생 삭제
        if(studentRepository.findByStudentNumber(studentNumber) == null)
            throw new NotFoundStudentException();
        studentRepository.delteByStudentNumber(studentNumber);
    }

    public void deleteAllStudent() { // 다중 학생 삭제
        studentRepository.deleteAll();
    }

    public Student editStudentInformation(EditStudentForm editStudentForm) { //단일 학생 정보 수정
        Student existsStudent = studentRepository.findByStudentNumber(editStudentForm.getStudentNumber());
        if(existsStudent == null)
            throw new NotFoundStudentException();

        Student student = convertEditFormToStudent(editStudentForm, existsStudent);

        return studentRepository.updateStudent(student);
    }

    public int countAllStudent() {
        return studentRepository.countStudent();
    }






    private static Student saveStudent(SignUpForm signUpForm) {

        String[] studentBirth = signUpForm.getStudentBirth().split("_");

        Student student = Student.builder()
                .studentNumber(signUpForm.getStudentNumber())
                .studentGrade(signUpForm.getStudentGrade())
                .studentName(signUpForm.getStudentName())
                .studentBirthYear(Integer.parseInt(studentBirth[0]))
                .studentBirthMonth(Integer.parseInt(studentBirth[1]))
                .studentBirthDay(Integer.parseInt(studentBirth[2]))
                .studentMajor(signUpForm.getStudentMajor())
                .studentSemester(signUpForm.getStudentSemester())
                .studentAddress(signUpForm.getStudentAddress())
                .build();
        return student;
    }


    private static Student convertEditFormToStudent(EditStudentForm editStudentForm, Student existsStudent) {
        Student student = Student.builder()
                .studentNumber(existsStudent.getStudentNumber())
                .studentGrade(existsStudent.getStudentGrade())
                .studentName(editStudentForm.getStudentName())
                .studentBirthYear(existsStudent.getStudentBirthYear())
                .studentBirthMonth(existsStudent.getStudentBirthMonth())
                .studentBirthDay(existsStudent.getStudentBirthDay())
                .studentMajor(existsStudent.getStudentMajor())
                .studentSemester(existsStudent.getStudentSemester())
                .studentAddress(editStudentForm.getStudentAddress())
                .build();
        return student;
    }
}