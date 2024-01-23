package fx.studentManagement.service;

import fx.studentManagement.entity.Student;
import fx.studentManagement.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void signUp(Student stu) { //학생 입력
        studentRepository.save(stu);
    }

    public Student showStudent(Long stuNum) { //단일 학생 조회
        return studentRepository.findByStuNum(stuNum);
    }

    public List<Student> showAllStudent() { //모든 학생 조회
        return studentRepository.findAll();
    }

    public void deleteStudent(Long stuNum) { // 단일 학생 삭제
        studentRepository.delteByStuNum(stuNum);
    }

    public void deleteAllStudent() { // 다중 학생 삭제
        studentRepository.deleteAll();
    }

    public Student updateStudent(Long stuNum, Student stu) { //단일 학생 정보 수정
        return studentRepository.updateStu(stuNum, stu);
    }

    public int countAllStudent() {
        return studentRepository.countStu();
    }

}
