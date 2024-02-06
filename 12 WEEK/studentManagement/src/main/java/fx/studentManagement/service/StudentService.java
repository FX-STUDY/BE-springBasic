package fx.studentManagement.service;

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
            throw new RuntimeException("이미 존재하는 학생입니다.");

        Student student = saveStudent(signUpForm);

        studentRepository.save(student);
    }


    public Student showStudent(Long studentNumber) { //단일 학생 조회
        if (studentRepository.findByStudentNumber(studentNumber) == null)
            throw new RuntimeException("존재하지 않는 학생입니다.");
        return studentRepository.findByStudentNumber(studentNumber);
    }

    public List<Student> showAllStudent() { //모든 학생 조회
        if(studentRepository.findAll() == null)
            throw new RuntimeException("존재하는 학생이 없습니다.");
        return studentRepository.findAll();
    }

    public void deleteStudent(Long studentNumber) { // 단일 학생 삭제
        if(studentRepository.findByStudentNumber(studentNumber) == null)
            throw new RuntimeException("존재하지 않는 학생입니다.");
        studentRepository.delteByStudentNumber(studentNumber);
    }

    public void deleteAllStudent() { // 다중 학생 삭제
        studentRepository.deleteAll();
    }

    public Student updateStudent(Long studentNumber, Student student) { //단일 학생 정보 수정
        if(studentRepository.findByStudentNumber(studentNumber) == null)
            throw new RuntimeException("존재하지 않는 학생입니다.");
        return studentRepository.updateStudent(studentNumber, student);
    }

    public int countAllStudent() {
        return studentRepository.countStudent();
    }






    private static Student saveStudent(SignUpForm signUpForm) {
        int year = signUpForm.getStudentBirth().getYear() + 1900;
        int month = signUpForm.getStudentBirth().getMonth() + 1;
        int day = signUpForm.getStudentBirth().getDay(); //여기서 약간 계산오류 발생. 변경하기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        Student student = Student.builder()
                .studentNumber(signUpForm.getStudentNumber())
                .studentGrade(signUpForm.getStudentGrade())
                .studentName(signUpForm.getStudentName())
                .studentBirthYear(year)
                .studentBirthMonth(month)
                .studentBirthDay(day)
                .studentMajor(signUpForm.getStudentMajor())
                .studentSemester(signUpForm.getStudentSemester())
                .studentAddress(signUpForm.getStudentAddress())
                .build();
        return student;
    }
}