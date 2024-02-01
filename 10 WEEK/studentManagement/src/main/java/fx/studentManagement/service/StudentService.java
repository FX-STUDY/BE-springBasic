package fx.studentManagement.service;

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

    public void signUp(Student student) { //학생 입력

        if(studentRepository.findByStudentNumber(student.getStudentNumber()) != null)
            throw new RuntimeException("이미 존재하는 학생입니다.");

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
}