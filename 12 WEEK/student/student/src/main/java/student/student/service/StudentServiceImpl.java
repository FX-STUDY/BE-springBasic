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
    @Override
    public Object saveStudent(Student student) {
        Student findStudent = studentRepository.findById(student.getId());
        // 저장하려는 학생 정보가 이미 있다면 해당 학생 정보 반환
        if (!Objects.isNull(findStudent)){
            return findStudent;
        }
        // 저장하려는 학생의 학번이 요구사항을 충족하지 못했다면 학번 반환
        if(!validateStudent.validateStudentId(student.getId())){
            return student.getId();
        }
        // 저장하려는 학생의 이름이 요구사항을 충족하지 못했다면 이름 반환
        if(!validateStudent.validateStudentName(student.getName())){
            return student.getName();
        }
        // 저장하려는 학생의 학과가 요구사항을 충족하지 못했다면 학과 반환
        if(!validateStudent.validateMajor(student.getMajor())){
            return student.getMajor();
        }
        // 저장하려는 학생의 학기가 요구사항을 충족하지 못했다면 학기 반환
        if(!validateStudent.validateSemester(student.getSemester())){
            return student.getSemester();
        }
        // 저장하려는 학생의 학년이 요구사항을 충족하지 못했다면 학년 반환
        if(!validateStudent.validateGrade(student.getGrade())){
            return student.getGrade();
        }

        return studentRepository.save(student);
    }

    @Override
    public Student showStudentByStudentId(Long studentId) {
        Student findStudent = studentRepository.findById(studentId);
        // 찾으려는 학생의 정보가 없다면 null 반환?
        if(Objects.isNull(findStudent)){
            return null;
        }

        return findStudent;
    }

}
